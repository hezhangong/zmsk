package com.zmsk.picture.process.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/****
 * 图片工具类
 * 
 * @author warrior
 *
 *         2018年4月2日
 */
public class PictureUtils {

	private static final Logger logger = LoggerFactory.getLogger(PictureUtils.class);

	private static final int BUFFER_SIZE = 1024 * 1024;

	/***
	 * 获取原始图片的字节数
	 * 
	 * @param is
	 *            图片输入流
	 */
	public static ByteBuffer getPictureByteBuffer(FileInputStream fis) {
		// 从InputStream中获取文件通道
		FileChannel channel = fis.getChannel();
		try {
			int fileSize = (int) channel.size();
			// 从通道中读取文件
			ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);
			channel.read(byteBuffer);
			// 注意先调用flip方法反转Buffer,再从Buffer读取数据
			byteBuffer.flip();
			byteBuffer.clear();
			return byteBuffer;
		} catch (IOException e) {
			logger.error("read byte from file error ", e);
			throw new RuntimeException("read byte from file error ", e);
		} finally {
			// 关闭流通道
			try {
				channel.close();
				fis.close();
			} catch (IOException e) {
				logger.error("close FileInputStream error ", e);
			}
		}
	}

	public static byte[] getPictureBytes(InputStream is) {
		byte[] buffer = new byte[BUFFER_SIZE];
		int len = -1;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			while ((len = is.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			logger.error("read byte from file error ", e);
			throw new RuntimeException("read byte from file error ", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				logger.error("close FileInputStream error ", e);
			}
		}
		return outputStream.toByteArray();
	}

	/***
	 * 保存图片到磁盘中
	 * 
	 * @param bbf
	 * @param savePath
	 */
	public static void savePicture(ByteBuffer bbf, String savePath) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(savePath));
			FileChannel channel = fos.getChannel();
			channel.write(bbf);
			channel.close();
			fos.close();
		} catch (IOException e) {
			logger.error("save picture to disk error ", e);
		}
	}

}
