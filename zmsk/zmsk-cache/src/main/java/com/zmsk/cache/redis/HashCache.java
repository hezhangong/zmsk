package com.zmsk.cache.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 哈希类型缓存接口操作声明
 * 
 * @author majun@12301.cc
 *
 * @param <K>
 * @param <V>
 */
public interface HashCache<K, V> extends Cache {

	/**
	 * 缓存是否存在
	 *
	 * @param key
	 * @return
	 */
	boolean exists(final K key);

	V get(final K key);

	Map<K, V> getAll();

	Set<K> getKeys();

	List<V> getValues();

	Set<V> getValueSet();

	/**
	 *
	 * @param keyfinal
	 * @param value
	 * @return true 表示已经存在, false 表示新创建, 之前不存在
	 */
	boolean put(final K key, final V value);

	boolean remove(@SuppressWarnings("unchecked") final K... key);

	/**
	 * 支持批量向缓存中添加数据
	 * 
	 * @param map
	 * @return
	 */
	boolean putAll(final Map<K, V> map);

	Map<K, V> getMulti(Collection<K> collect, Class<K> cla);

	/**
	 * "SET if Not eXists"
	 *
	 * @param key
	 * @param value
	 * @return true if the key was set, false if the key was not set
	 */
	boolean putNx(final K key, final V value);

	long size();

	long clear();

	/***
	 * 支持批量向缓存中添加数据，添加过期时间
	 * 
	 * @param map
	 * @param seconds
	 */
	void putAllEx(final Map<K, V> map, int seconds);
	
	/**
	 * 获取缓存所有键值
	 * @author lijian@12301.cc
	 * @return
	 */
	Set<String> getCacheKeys();
}
