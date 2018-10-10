package com.zmsk.common.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/****
 * 两个对象间的属性值复制，比较，转为map方法实现
 * 
 * @author majun@12301.cc
 *
 */
public class BeanUtils {

	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/****
	 * 两对象之间的拷贝,仅拷贝名及返回类型完全一样的属性值,不管值是否为null都拷贝
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 */
	public static void copyPropertiesNotForce(Object desc, Object orig) {
		copyPropertiesNotForce(desc, orig, null);
	}

	/***
	 * 两对象之间的拷贝,仅拷贝名及返回类型完全一样的属性值,不拷贝为null的值
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 */
	public static void copyPropertiesNotNull(Object desc, Object orig) {
		copyPropertiesNotForce(desc, orig, null, false);
	}

	/***
	 * 两对象之间的拷贝,仅拷贝名及返回类型完全一样的属性值,不拷贝为null的值
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @param excludeFields
	 *            源对象不拷贝的字段值，多个值用逗号隔开
	 */
	public static void copyPropertiesNotNull(Object desc, Object orig, String excludeFields) {
		copyPropertiesNotForce(desc, orig, excludeFields, false);
	}

	/***
	 * 两对象之间的拷贝,仅拷贝名及返回类型完全一样的属性值,不管值是否为null都拷贝
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @param excludeFields
	 *            源对象不拷贝的字段值，多个值用逗号隔开
	 */
	public static void copyPropertiesNotForce(Object desc, Object orig, String excludeFields) {
		copyPropertiesNotForce(desc, orig, excludeFields, true);
	}

	/****
	 * 两对象之间的拷贝,仅拷贝名及返回类型完全一样的属性值
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @param excludeFields
	 *            源对象不拷贝的字段值，多个值用逗号隔开
	 * @param isCopyNull
	 *            为null的属性是否拷贝(true拷贝null属性;false不拷贝null属性)
	 */
	public static void copyPropertiesNotForce(Object desc, Object orig, String excludeFields, boolean isCopyNull) {

		Class<?> origClass = orig.getClass();
		Class<?> descClass = desc.getClass();

		Method[] descMethods = descClass.getMethods();
		Method[] origMethods = origClass.getMethods();

		// 是否需要过滤部分字段
		boolean needExclude = false;

		if (!StringUtils.isEmpty(excludeFields)) {
			needExclude = true;
			excludeFields = "," + excludeFields.toLowerCase() + ",";
		}

		Map<String, Method> methodMap = new HashMap<String, Method>();
		for (int i = 0; i < origMethods.length; i++) {
			Method method = origMethods[i];
			String methodName = method.getName();
			if (!methodName.equals("getClass") && methodName.startsWith("get")
					&& (method.getParameterTypes() == null || method.getParameterTypes().length == 0)) {
				if (needExclude && excludeFields.indexOf(methodName.substring(3).toLowerCase()) > -1) {
					continue;
				}
				methodMap.put(methodName, method);
			}
		}

		for (int i = 0; i < descMethods.length; i++) {
			Method method = descMethods[i];
			String methodName = method.getName();
			if (!methodName.equals("getClass") && methodName.startsWith("get")
					&& (method.getParameterTypes() == null || method.getParameterTypes().length == 0)) {
				if (methodMap.containsKey(methodName)) {
					Method origMethod = (Method) methodMap.get(methodName);
					try {
						if (method.getReturnType().equals(origMethod.getReturnType())) {
							Object returnObj = origMethod.invoke(orig);
							if (!isCopyNull && returnObj == null) {
								continue;
							}
							String field = methodName.substring(3);
							String setMethodName = "set" + field;
							Method setMethod = descClass.getMethod(setMethodName,
									new Class[] { method.getReturnType() });
							setMethod.invoke(desc, new Object[] { returnObj });
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}
	}

	/*****
	 * 判断两个对象的所有相同属性值是否相等,注意尽是比较相同的属性,对于A对象属性比B对象属性多，如果相同属性值相同，则返回为true
	 * 
	 * @param desc
	 * @param orig
	 * @return 相等返回true，否则返回false
	 */
	public static boolean isEqualBeanProperties(Object desc, Object orig) {

		return false;
	}

	/***
	 * 比较两个Bean相同字段的值（以源对象的值为基准，json串中显示目标对象中的值）
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @return String 不一致的字段json串
	 */
	public static String compareBeanProperties(Object desc, Object orig) {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> origClass = orig.getClass();
		Class<?> descClass = desc.getClass();

		Method[] descMethods = descClass.getMethods();
		Method[] origMethods = origClass.getMethods();

		Map<String, Method> methodMap = new HashMap<String, Method>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < origMethods.length; i++) {
			Method method = origMethods[i];
			String methodName = method.getName();
			if (!methodName.equals("getClass") && methodName.startsWith("get")
					&& (method.getParameterTypes() == null || method.getParameterTypes().length == 0)) {
				methodMap.put(methodName, method);
			}
		}

		for (int i = 0; i < descMethods.length; i++) {
			Method method = descMethods[i];
			String methodName = method.getName();
			if (!methodName.equals("getClass") && methodName.startsWith("get")
					&& (method.getParameterTypes() == null || method.getParameterTypes().length == 0)) {
				if (methodMap.containsKey(methodName)) {
					Method origMethod = (Method) methodMap.get(methodName);
					try {
						if (method.getReturnType().equals(origMethod.getReturnType())) {
							Object origObj = origMethod.invoke(orig);
							origObj = origObj == null ? "" : origObj;

							Method descMethod = descClass.getMethod(methodName);
							Object descObj = descMethod.invoke(desc);
							descObj = descObj == null ? "" : descObj;
							if (!origObj.equals(descObj)) {
								map.put(methodName.substring(3), descObj);
								sb.append(",{'field':'");
								sb.append(methodName.substring(3));
								sb.append("','newValue':'");
								sb.append(descObj.toString().replaceAll("\'", ""));
								sb.append("','origValue':'");
								sb.append(origObj.toString().replaceAll("\'", ""));
								sb.append("'}");
							}
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}
		String str = "[";
		if (sb.length() > 0) {
			str += sb.substring(1);
		}
		return str + "]";
	}

	/****
	 * 将Map对象转换成Bean
	 * 
	 * @param bean
	 * @param properties
	 */
	public static void convertMap2Bean(Object bean, Map<String, ? extends Object> properties) {
		try {
			org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
