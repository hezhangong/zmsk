package com.zmsk.face.service.image;

import java.io.InputStream;

/****
 * 图片操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface ImageService {

	/***
	 * 图片上传到OSS中
	 * 
	 * @param inputStream
	 *            输入流
	 * @return
	 */
	public String uploadImage2OSS(InputStream inputStream);

}
