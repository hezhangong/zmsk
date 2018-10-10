package com.zmsk.face.service.app;

import com.zmsk.face.pojo.FaceAppInfo;

/****
 * 应用版本信息操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface AppInfoService {

	/****
	 * 获取最新版本的应用下载地址信息
	 * 
	 * @param version
	 *            当前版本号
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	FaceAppInfo queryLatestVersionAppInfo(int version, int deviceId);
}
