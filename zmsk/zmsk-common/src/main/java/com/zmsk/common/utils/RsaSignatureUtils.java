package com.zmsk.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

/****
 * RSA签名工具类
 * 
 * @author warrior
 *
 */
public class RsaSignatureUtils {

	private static final String RSA_ALGORITHM = "RSA";

	private static final String ALGORITHM = "SHA1withRSA";

	private static final String CHARSET = "UTF-8";

	// 公钥
	private static final String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDF2PQbdYtmsifN4n+RoCYAxW73494vkhDcs9oXipdwRovmySRgQKtA8NAVzRIb/R3YAn1XnyizNV2/RBUkbrHLNIlZwBIpNLfpvqlmdqzpLkmVkL7JM3a0mayE4InLH6vjmpBDTYjIFy1MyTEhe77B3wH8zldeLRpITw55feQUMwIDAQAB";

	private static final String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMXY9Bt1i2ayJ83if5GgJgDFbvfj3i+SENyz2heKl3BGi+bJJGBAq0Dw0BXNEhv9HdgCfVefKLM1Xb9EFSRuscs0iVnAEik0t+m+qWZ2rOkuSZWQvskzdrSZrITgicsfq+OakENNiMgXLUzJMSF7vsHfAfzOV14tGkhPDnl95BQzAgMBAAECgYEAjifJlaVKqVpe0UbqJEdupuU1S3X14jZQWVP84ydYknqN8SAbO/GbWwjsao2zclrXQ6reRsP8KW+x0Ujo7AmBQt4uSqRTdu5s9GpufFncEWjECPsZdikVgFwxnI4hoVUhnsPVw2mLjw7e8emeLl4tobTDaU4WX6XOZOJe1E0qTLkCQQDhoPUwmkLZIWNZEQ600JBWhnDjEyRheZ7B8XaB+tW9FRMH+QJRyteCbHy+/JUjFXAT7NW/h+UOtIe1TZnZ5XY1AkEA4HqrKqA5+5n9KSyZA+rjZK4DTu/TwZPevAKTKFmV9C9FfkWO2VAK9OoJ9n3w9TumOyfdW5Yt3C+Z1YeJVgKNxwJAKjDuCJOgEngy2rHc3STvCK8FJwEqWWvjqwKbFX0xQLVTJLeEnoTevc3JmfEjdjcJCUDNS45+37wUcGu9bEiDTQJAKg/SMtKDCtn3zddFdK52nU7d39SgYQ1MFv6EhHME3hRdeSOfeKi+5NVVuJIwrELZCwyVNawWO8PPl2smGK+x0QJAc7kvFWBKfyBeesyHgFoucZQDZBe5kl4EpOM94pI1buz9dIAjXQ27HZGlkDh96sTICkdIhWK8sTMfzIXjYq5+Bg==";

	/***
	 * 字符串RSA算法签名
	 *
	 * @param content
	 *            需要签名的字符串
	 * @param privateKey
	 *            私钥
	 * @return RSA签名后的字符串
	 * @throws SignatureException
	 */
	public static String rsaSign(String content, String privateKey) throws SignatureException {

		try {
			PrivateKey priKey = getPrivateKeyFromPKCS8(RSA_ALGORITHM, new ByteArrayInputStream(privateKey.getBytes(CHARSET)));

			Signature signature = Signature.getInstance(ALGORITHM);

			signature.initSign(priKey);

			signature.update(content.getBytes(CHARSET));

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));

		} catch (Exception e) {
			throw new SignatureException("RSAcontent = " + content + "; charset = " + CHARSET, e);
		}
	}

	/***
	 * rsa签名验证
	 *
	 * @param content
	 *            被签名的内容
	 * @param sign
	 *            签名结果
	 * @param publicKey
	 *            rsa公钥
	 * @return
	 * @throws SignatureException
	 */
	public static boolean rsaCheck(String content, String sign, String publicKey) throws SignatureException {

		try {
			PublicKey pubKey = getPublicKeyFromX509(RSA_ALGORITHM, new ByteArrayInputStream(publicKey.getBytes()));

			Signature signature = Signature.getInstance(ALGORITHM);

			signature.initVerify(pubKey);

			signature.update(getContentBytes(content, CHARSET));

			return signature.verify(Base64.decodeBase64(sign.getBytes()));
		} catch (Exception e) {
			throw new SignatureException("RSA验证签名[content = " + content + "; charset = " + CHARSET + "; signature = " + sign + "]发生异常!", e);
		}

	}

	public static boolean rsaCheck(String content, String sign) throws SignatureException {
		return rsaCheck(content, sign, PUBLICKEY);
	}

	public static <T extends Serializable> boolean rsaCheck(T t, String sign) throws SignatureException {
		String content = JSON.toJSONString(t);
		return rsaCheck(content, sign, PUBLICKEY);
	}

	/***
	 * 获取私钥key
	 *
	 * @param algorithm
	 *            签名算法
	 * @param ins
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {

		if (ins == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}

		KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);

		byte[] encodedKey = inputStrem2byte(ins);

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}

	/****
	 * 获取公钥key
	 *
	 * @param algorithm
	 *            签名算法
	 * @param ins
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		byte[] encodedKey = inputStrem2byte(ins);

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
	}

	/***
	 * 将InputStrem转换成byte数组
	 *
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	private static byte[] inputStrem2byte(InputStream inStream) throws IOException {

		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();

		byte[] buff = new byte[1024];

		int rc = 0;

		while ((rc = inStream.read(buff, 0, 1024)) > 0) {
			swapStream.write(buff, 0, rc);
		}

		byte[] in2b = swapStream.toByteArray();

		return in2b;
	}

	/****
	 * 将字符串转换成byte数组
	 *
	 * @param content
	 *            字符串
	 * @param charset
	 *            编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(charset)) {
			return content.getBytes();
		}
		return content.getBytes(charset);
	}
	
	public static void main(String[] args) {
		String content = "12";
		try {
			String sign = rsaSign(content, PRIVATEKEY);
			System.out.println("sign:"+sign);
			boolean result = rsaCheck(content, sign);
			System.out.println("result:"+result);
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}
}
