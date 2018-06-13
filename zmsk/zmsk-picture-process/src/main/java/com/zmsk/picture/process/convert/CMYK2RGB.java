package com.zmsk.picture.process.convert;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CMYK2RGB {

	private static final Logger logger = LoggerFactory.getLogger(CMYK2RGB.class);

	/***
	 * cmyk格式图片转换成rgb格式图片
	 * 
	 * @param is
	 *            图片输入流
	 * @return 图片的字节数组
	 */
	public static BufferedImage cmyk2rgb(InputStream is) {
		ImageInputStream iis;
		BufferedImage image = null;
		String format;
		try {
			iis = ImageIO.createImageInputStream(is);
		} catch (IOException e) {
			logger.error("create ImageInputStream error ", e);
			throw new RuntimeException("create ImageInputStream error", e);
		}
		Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
		if (readers == null || !readers.hasNext()) {
			logger.error("No ImageReader found");
			throw new RuntimeException("No ImageReaders found");
		}
		ImageReader reader = readers.next();
		reader.setInput(iis);
		try {
			format = reader.getFormatName();
		} catch (IOException e) {
			logger.error("get picture formatName error ", e);
			throw new RuntimeException("get picture formatName error", e);
		}
		if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
			try {
				// 尝试读取图片 (包括颜色的转换).
				image = reader.read(0); // RGB
			} catch (IIOException e) {
				// 读取Raster (没有颜色的转换).
				Raster raster;
				try {
					raster = reader.readRaster(0, null);// CMYK
				} catch (IOException e1) {
					logger.error("read image error ", e1.getMessage());
					throw new RuntimeException("read image error", e1);
				}
				image = createJPEG4(raster);
			} catch (IOException e) {
				logger.error("read image error ", e);
				throw new RuntimeException("read image error ", e);
			}
		}
		return image;
	}

	/***
	 * cmyk格式图片转换成rgb格式图片
	 * 
	 * @param buff
	 *            图片字节数组
	 * @return 转换后的图片字节数组
	 */
	public static BufferedImage cmyk2rgb(byte[] buff) {
		ByteArrayInputStream bis = new ByteArrayInputStream(buff);
		return cmyk2rgb(bis);
	}

	private static BufferedImage createJPEG4(Raster raster) {
		// 随意选择一个BufferedImage类型.
		int imageType;
		switch (raster.getNumBands()) {
		case 1:
			imageType = BufferedImage.TYPE_BYTE_GRAY;
			break;
		case 3:
			imageType = BufferedImage.TYPE_3BYTE_BGR;
			break;
		case 4:
			imageType = BufferedImage.TYPE_4BYTE_ABGR;
			break;
		default:
			throw new UnsupportedOperationException();
		}

		BufferedImage image = new BufferedImage(raster.getWidth(), raster.getHeight(), imageType);
		// 设置图片数据.
		image.getRaster().setRect(raster);
		return image;
	}
}
