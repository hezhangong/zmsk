package com.zmsk.picture.process.compress;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zmsk.picture.process.convert.CMYK2RGB;

/***
 * 图片压缩工具类
 * 
 * @author warrior
 *
 *         2018年4月2日
 */
public class CompressPicture {

	private static final Logger logger = LoggerFactory.getLogger(CompressPicture.class);

	private static final String FORMATNAME = "jpg";

	/****
	 * 图片字节数组进行质量压缩
	 * 
	 * @param bytes
	 *            图片字节数组
	 * @param quality
	 *            压缩质量
	 * @return
	 */
	public static byte[] compressPictureTobytes(byte[] bytes, float quality) {
		// 存储压缩图片的byte数组
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		BufferedImage srcImage;
		try {
			srcImage = ImageIO.read(bis);
		} catch (IIOException e) {
			srcImage = CMYK2RGB.cmyk2rgb(bytes);
		} catch (IOException e) {
			throw new RuntimeException("read image bytes error", e);
		}

		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		return compressPictureToBytes(srcImage, width, height, quality);
	}

	/****
	 * 图片流进行质量压缩
	 * 
	 * @param bytes
	 *            图片字节数组
	 * @param quality
	 *            压缩质量
	 * @return
	 */
	public static byte[] compressPictureTobytes(BufferedImage srcImage, float quality) {
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		return compressPictureToBytes(srcImage, width, height, quality);
	}

	/***
	 * 图片字节数组尺寸压缩
	 * 
	 * @param bytes
	 *            图片字节数组
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @return
	 */
	public static byte[] compressPictureTobytes(byte[] bytes, int width, int height) {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		BufferedImage srcImage;
		try {
			srcImage = ImageIO.read(bis);
		} catch (IIOException e) {
			srcImage = CMYK2RGB.cmyk2rgb(bytes);
		} catch (IOException e) {
			throw new RuntimeException("read image bytes error", e);
		}
		return compressPictureToBytes(srcImage, width, height, 1.0f);
	}

	/***
	 * 图片流尺寸压缩
	 * 
	 * @param bytes
	 *            图片字节数组
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @return
	 */
	public static byte[] compressPictureTobytes(BufferedImage srcImage, int width, int height) {
		return compressPictureToBytes(srcImage, width, height, 1.0f);
	}

	/****
	 * 图片字节数组尺寸和质量压缩
	 * 
	 * @param bytes
	 *            图片字节数组
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param quality
	 *            压缩质量
	 * @return
	 */
	public static byte[] compressPictureToBytes(byte[] bytes, int width, int height, float quality) {
		// 存储压缩图片的byte数组
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		BufferedImage srcImage;
		try {
			srcImage = ImageIO.read(bis);
		} catch (IIOException e) {
			srcImage = CMYK2RGB.cmyk2rgb(bytes);
		} catch (IOException e) {
			throw new RuntimeException("read image bytes error", e);
		}
		return compressPictureToBytes(srcImage, width, height, quality);
	}

	/****
	 * 图片质量和框高等比压缩
	 * 
	 * @param srcImage
	 *            图片流
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param quality
	 *            质量
	 * @return
	 */
	public static byte[] compressPictureToBytes(BufferedImage srcImage, int width, int height, float quality) {

		// 存储压缩图片的byte数组
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int srcWidth = srcImage.getWidth();

		int srcHeight = srcImage.getHeight();

		// 根据原图的大小生成空白画布
		BufferedImage tempImg = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);

		// 在新的画布上生成原图的缩略图
		Graphics2D g = tempImg.createGraphics();

		g.setColor(Color.white);

		g.fillRect(0, 0, srcWidth, srcHeight);

		g.drawImage(srcImage, 0, 0, srcWidth, srcHeight, Color.white, null);

		g.dispose();

		double scalex = (double) width / (double) srcWidth;

		double scaley = height / (double) srcHeight;

		if (!(scalex >= 1.0 || scaley >= 1.0)) {
			if (scalex > scaley) {
				width = (int) (scaley * srcWidth);
			} else {
				height = (int) (scalex * srcHeight);
			}
		} else {
			width = srcWidth;
			height = srcHeight;
		}

		BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		newImg.getGraphics().drawImage(tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

		ImageWriter writer = ImageIO.getImageWritersByFormatName(FORMATNAME).next();

		ImageWriteParam writeParam = writer.getDefaultWriteParam();

		// 设置压缩模式
		writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

		// 获取图片的原始压缩质量
		float srcQuality = writeParam.getCompressionQuality();

		if ((srcQuality * quality) > 0) {
			quality = srcQuality * quality;
		}

		// 设置压缩质量
		writeParam.setCompressionQuality(quality);

		writeParam.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

		ColorModel colorModel = ColorModel.getRGBdefault();

		// 指定压缩时使用的色彩模式
		writeParam.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		// 获取内存输出流
		IIOImage iioImage = new IIOImage(newImg, null, null);

		try {
			writer.reset();
			writer.setOutput(ImageIO.createImageOutputStream(out));
			writer.write(null, iioImage, writeParam);
		} catch (IOException e) {
			logger.error("write compress image to ByteArrayOutputStream error ", e);
			throw new RuntimeException("write compress image to ByteArrayOutputStream error ", e);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error("close ByteArrayOutputStream error ", e);
				throw new RuntimeException("close ByteArrayOutputStream error", e);
			}
		}
		return out.toByteArray();
	}

}
