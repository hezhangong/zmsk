package com.zmsk.cache.common;

/**
 * 可以重新初始化的对象
 *
 * @author warrior
 *
 */
public interface Reusable {

	public void reinit(String id);

	public void recyle();

}
