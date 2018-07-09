package com.zmsk.face.controller.device;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.image.ImageService;

@Controller
@RequestMapping("/device/upload/")
public class UploadController {

	@Autowired
	private ImageService imageService;

	/****
	 * 文件上传
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "image", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO deviceUploadImage2Qiniu(@RequestParam(value = "file") MultipartFile file) throws IOException {

		InputStream inputStream = file.getInputStream();

		String path = imageService.uploadImage2Qiniu(inputStream);

		return ServiceResultDTO.success(path);
	}

}
