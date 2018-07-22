package com.zmsk.face.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceAppInfoMapper;
import com.zmsk.face.pojo.FaceAppInfo;
import com.zmsk.face.pojo.FaceAppInfoExample;
import com.zmsk.face.pojo.FaceAppInfoExample.Criteria;
import com.zmsk.face.service.app.AppInfoService;

@Service
@Transactional
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private FaceAppInfoMapper appInfoMapper;

	@Override
	public FaceAppInfo queryLatestVersionAppInfo(int version, int deviceId) {

		FaceAppInfoExample example = new FaceAppInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andAppVersionGreaterThan(version);

		if (deviceId > 0) {
			criteria.andDeviceIdEqualTo(deviceId);
		}

		List<FaceAppInfo> list = appInfoMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return null;
		}

		return list.get(0);
	}

}
