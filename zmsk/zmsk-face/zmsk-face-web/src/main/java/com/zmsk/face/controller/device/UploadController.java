package com.zmsk.face.controller.device;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zmsk.common.dto.BaseResultCode;
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

	/****
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "multiple/image", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO deviceUploadMultipleImage2Qiniu(@RequestParam(value = "file") MultipartFile[] files) throws IOException {

		if (files.length != 2) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid files size is not 2");
		}

		Map<String, String> map = new ConcurrentHashMap<>(2);

		MultipartFile firstFile = files[0];

		MultipartFile secondFile = files[1];

		InputStream firstInputStream = firstFile.getInputStream();

		InputStream secodeInputStream = secondFile.getInputStream();

		String firstImagePath = imageService.uploadImage2Qiniu(firstInputStream);

		String secondImagePath = imageService.uploadImage2Qiniu(secodeInputStream);

		map.put("firstImagePath", firstImagePath);

		map.put("secondImagePath", secondImagePath);

		return ServiceResultDTO.success(map);
	}

}
