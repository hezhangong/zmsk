package com.zmsk.face.service.image.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oss.OSSClient;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.zmsk.common.utils.IDMaker;
import com.zmsk.face.service.image.ImageService;
import com.zmsk.picture.process.compress.CompressPicture;

/****
 * 图片操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

	private static final String SPLIT = "/";

	private static final String DOT = ".";

	private static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";

	private static final String ACCESSKEYID = "vOZs72GEfVJnmhrM";

	private static final String ACCESSKEYSECRET = "3QRpUkn7snlE2Tj86wZpxmU8bgtBl7";

	private static final String bucketName = "doinggo";

	// 七牛accessKey
	private static final String QINIU_ACCESSKEY = "BBJIQ464q2BkpdHHaSrcZi4V5VAP6VTbNh2JmzFP";

	// 七牛secretKey
	private static final String QINIU_SECRETKEY = "YtuEF-SnjSR5zo0c7-Xe_cQZtAX6Gi9PhPezUCE9";

	// 七牛bucket
	private static final String QINIU_BUCKET = "zmsk-face";

	private static final String QINIU_DOMAIN = "http://paag0kq15.bkt.clouddn.com";

	/** 默认压缩宽度 **/
	private static final int DEFAULTCOMPRESSWITH = 240;

	/** 默认压缩高度 **/
	private static final int DEFAULTCOMPRESSHEIGHT = 135;

	@Override
	public String uploadImage2OSS(InputStream inputStream) {

		OSSClient ossClient = null;

		try {

			byte[] originalBytes = readStream(inputStream);

			// 压缩图片
			byte[] compressBytes = CompressPicture.compressPictureTobytes(originalBytes, DEFAULTCOMPRESSWITH, DEFAULTCOMPRESSHEIGHT);

			// 创建OSSClient实例
			ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);

			// 原始图片key
			String originalkey = buildOriginalKey();

			// 压缩图片key
			String compressKey = buildCompressKey(originalkey);

			// 上传压缩图片
			ossClient.putObject(bucketName, compressKey, new ByteArrayInputStream(compressBytes));

			return buildImagePath(originalkey);
		} catch (Exception e) {
			logger.error(" upload image to oss fail ", e);
		} finally {
			// 关闭client
			ossClient.shutdown();
		}
		return "";
	}

	@Override
	public String uploadImage2Qiniu(InputStream inputStream) {

		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());

		UploadManager uploadManager = new UploadManager(cfg);

		Auth auth = Auth.create(QINIU_ACCESSKEY, QINIU_SECRETKEY);

		String upToken = auth.uploadToken(QINIU_BUCKET);

		String key = buildOriginalKey();

		try {
			Response response = uploadManager.put(inputStream, key, upToken, null, null);

			if (response.statusCode == 200) {
				return QINIU_DOMAIN + SPLIT + key;
			}
		} catch (QiniuException e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	/**
	 * 得到图片字节流 数组大小
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	/***
	 * 构建原始图片OSSkey
	 * 
	 * @return
	 */
	private String buildOriginalKey() {
		String autoCreatedDateDirByParttern = "yyyyMMdd/HHmm";
		String autoCreatedDateDir = DateFormatUtils.format(new Date(), autoCreatedDateDirByParttern);
		StringBuilder sb = new StringBuilder();
		sb.append(autoCreatedDateDir).append(SPLIT).append(IDMaker.makeId()).append(DOT).append("jpg");
		return sb.toString();
	}

	/****
	 * 构建压缩图片OSSkey
	 * 
	 * @param originalKey
	 *            原始图片key
	 * @return
	 */
	private String buildCompressKey(String originalKey) {

		String[] keys = originalKey.split("\\.");

		StringBuilder commpressKey = new StringBuilder(keys[0]);

		commpressKey.append("-c").append(DOT).append("jpg");

		return commpressKey.toString();
	}

	/****
	 * 构建图片返回路径
	 * 
	 * @param key
	 *            OSSkey
	 * @return
	 */
	private String buildImagePath(String key) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(bucketName).append(DOT).append(ENDPOINT).append(SPLIT).append(key);
		return sb.toString();
	}

}
