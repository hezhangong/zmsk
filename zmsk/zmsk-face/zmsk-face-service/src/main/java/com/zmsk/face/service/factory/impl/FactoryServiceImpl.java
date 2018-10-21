package com.zmsk.face.service.factory.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zmsk.face.mapper.FaceFactoryMapper;
import com.zmsk.face.pojo.FaceFactory;
import com.zmsk.face.pojo.FaceFactoryExample;
import com.zmsk.face.service.factory.FactoryService;

/****
 * 工厂操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class FactoryServiceImpl implements FactoryService {

	private static final Logger logger = LoggerFactory.getLogger(FactoryServiceImpl.class);

	@Autowired
	private FaceFactoryMapper factoryMapper;

	@Override
	public boolean createFactory(String name, String shortName, String linkman, String phone) {

		FaceFactory factory = new FaceFactory();

		factory.setName(name);

		factory.setShortName(shortName);
		
		factory.setLinkman(linkman);
		
		factory.setPhone(phone);

		factory.setCtime(System.currentTimeMillis() / 1000);

		return factoryMapper.insert(factory) > 0;
	}

	@Override
	public boolean updateFactory(int id, String name, String shortName, String linkman, String phone) {

		FaceFactory factory = factoryMapper.selectByPrimaryKey(id);

		if (factory == null) {
			logger.warn("Invalid factory id {} when update factory can not fund factory ", id);
			return false;
		}

		if (!name.equals(factory.getName())) {
			factory.setName(name);
		}

		if (!shortName.equals(factory.getShortName())) {
			factory.setShortName(shortName);
		}
		
		if (!linkman.equals(factory.getLinkman())) {
			factory.setLinkman(linkman);
		}
		
		if (!phone.equals(factory.getPhone())) {
			factory.setPhone(phone);
		}

		return factoryMapper.updateByPrimaryKey(factory) > 0;
	}

	@Override
	public boolean deleteFactory(int id) {
		return factoryMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<FaceFactory> queryFactoryList(String search) {

		FaceFactoryExample example = new FaceFactoryExample();

		if (!StringUtils.isEmpty(search)) {
			example.or().andNameLike("%" + search + "%");
		}

		return factoryMapper.selectByExample(example);
	}

	@Override
	public FaceFactory queryFactoryById(int id) {
		return factoryMapper.selectByPrimaryKey(id);
	}

}
