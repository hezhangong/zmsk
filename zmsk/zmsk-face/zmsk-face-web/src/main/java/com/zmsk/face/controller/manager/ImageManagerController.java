package com.zmsk.face.controller.manager;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.image.ImageService;

/****
 * 图片操作Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/image/")
public class ImageManagerController {

	@Autowired
	private ImageService imageService;

	/***
	 * 上传图片到OSS
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "oss/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ServiceResultDTO uploadImage2OSS(MultipartFile uploadFile) throws IOException {

		InputStream inputStream = uploadFile.getInputStream();

		String path = imageService.uploadImage2OSS(inputStream);

		return ServiceResultDTO.success(path);
	}

	/***
	 * 上传图片到七牛中
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "qiniu/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ServiceResultDTO uploadImage2Qiniu(MultipartFile uploadFile) throws IOException {

		InputStream inputStream = uploadFile.getInputStream();

		String path = imageService.uploadImage2Qiniu(inputStream);

		return ServiceResultDTO.success(path);
	}

}
