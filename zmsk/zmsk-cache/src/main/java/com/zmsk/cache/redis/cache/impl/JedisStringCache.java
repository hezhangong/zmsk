package com.zmsk.cache.redis.cache.impl;

import java.io.Serializable;

import com.zmsk.cache.common.ReusableObjectPool;
import com.zmsk.cache.redis.StringCache;
import com.zmsk.cache.redis.cache.JedisCacheImpl;
import com.zmsk.cache.redis.cache.RedisOp.RedisOperation;
import com.zmsk.cache.redis.connection.JedisConnectionFactory;
import com.zmsk.cache.redis.lock.JedisLock;

/***
 * 字符串类型缓存操作redis实现
 *
 * @author majun@12301.cc
 *
 */
public class JedisStringCache<T extends Serializable> extends JedisCacheImpl implements StringCache<T> {

	public JedisStringCache(String namePrefix, String categoryName, JedisConnectionFactory connectionFactory) {
		this(namePrefix, categoryName, connectionFactory, null);
		reinit(categoryName);
	}

	public JedisStringCache(String namePrefix, String categoryName, JedisConnectionFactory connectionFactory, ReusableObjectPool<JedisStringCache<T>> objectPool) {
		super(namePrefix, categoryName, connectionFactory, objectPool);
	}

	@Override
	public T get() {
		try {
			return toObject(functionHandlerWithOp(2, jedis -> jedis.get(getCacheFullNameBytes()), RedisOperation.get));
		} finally {
			this.autoRecycle();
		}
	}

	@Override
	public void set(T value) {
		try {
			functionHandlerWithOp(2, jedis -> jedis.set(getCacheFullNameBytes(), objectToBytes(value)), RedisOperation.set);
		} finally {
			this.autoRecycle();
		}
	}

	@Override
	public long setNx(T value) {
		try {
			return functionHandlerWithOp(2, jedis -> jedis.setnx(getCacheFullNameBytes(), objectToBytes(value)), RedisOperation.set);
		} finally {
			this.autoRecycle();
		}
	}

	@Override
	public boolean setExpire(T value, int expireSecond) {
		try {
			return JedisLock.OK.equals(functionHandlerWithOp(2, jedis -> jedis.setex(getCacheFullNameBytes(), expireSecond, objectToBytes(value)), RedisOperation.setex));
		} finally {
			this.autoRecycle();
		}
	}

	@Override
	public long incr() {
		try {
			return functionHandlerWithOp(2, jedis -> jedis.incr(getCacheFullNameBytes()), RedisOperation.incr);
		} finally {
			this.autoRecycle();
		}
	}

	@Override
	public long incrby(int increment) {
		try {
			return functionHandlerWithOp(2, jedis -> jedis.incrBy(getCacheFullNameBytes(), increment), RedisOperation.incrBy);
		} finally {
			this.autoRecycle();
		}
	}
	
	@Override
	public long decr() {
		try {
			return functionHandlerWithOp(2, jedis -> jedis.decr(getCacheFullNameBytes()), RedisOperation.decr);
		} finally {
			this.autoRecycle();
		}
	}
	
	@Override
	public long decrby(int decrement) {
		try {
			return functionHandlerWithOp(2, jedis -> jedis.decrBy(getCacheFullNameBytes(), decrement), RedisOperation.decrBy);
		} finally {
			this.autoRecycle();
		}
	}

	@Override
	public void del() {
		try {
			functionHandlerWithOp(2, jedis -> jedis.del(getCacheFullNameBytes()), RedisOperation.del);
		} finally {
			this.autoRecycle();
		}
	}

}
