package com.ssm.redis;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.mysql.fabric.xmlrpc.base.Array;
import com.ssm.dto.SysMenus;
import com.ssm.util.SerializeUtil;

public class MethodCacheInterceptor implements MethodInterceptor {
	private Logger logger = Logger.getLogger(MethodCacheInterceptor.class);

	// @Autowired
	// private RedisUtil redisUtil;

	@Autowired
	private RedisServiceUtil redisService;
	
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	private List<String> targetNamesList; // 不加入缓存的service名称
	private List<String> methodNamesList; // 不加入缓存的方法名称
	private Long defaultCacheExpireTime; // 缓存默认的过期时间
	private Long xxxRecordManagerTime; //
	private Long xxxSetRecordManagerTime; //

	/**
	 * 初始化读取不需要加入缓存的类名和方法名称
	 */
	public MethodCacheInterceptor() {
		try {
			File f = new File(this.getClass().getClassLoader().getResource("db.properties").getPath());
			// 配置文件位置直接被写死，有需要自己修改下
			InputStream in = new FileInputStream(f);
			Properties p = new Properties();
			p.load(in);
			// 分割字符串
			String[] targetNames = p.getProperty("targetNames").split(",");
			String[] methodNames = p.getProperty("methodNames").split(",");

			// 加载过期时间设置
			defaultCacheExpireTime = Long.valueOf(p.getProperty("defaultCacheExpireTime"));
			xxxRecordManagerTime = Long.valueOf(p.getProperty("com.service.impl.xxxRecordManager"));
			xxxSetRecordManagerTime = Long.valueOf(p.getProperty("com.service.impl.xxxSetRecordManager"));
			// 创建list
			targetNamesList = new ArrayList<String>(targetNames.length);
			methodNamesList = new ArrayList<String>(methodNames.length);
			Integer maxLen = targetNames.length > methodNames.length ? targetNames.length : methodNames.length;
			// 将不需要缓存的类名和方法名添加到list中
			for (int i = 0; i < maxLen; i++) {
				if (i < targetNames.length) {
					targetNamesList.add(targetNames[i]);
				}
				if (i < methodNames.length) {
					methodNamesList.add(methodNames[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object value = null;

		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();

		Class<?> clazz = invocation.getMethod().getReturnType();
		if (!isAddCache(targetName, methodName)) {
			// 执行方法返回结果
			return invocation.proceed();
		}
		Object[] arguments = invocation.getArguments();
		String key = getCacheKey(targetName, methodName, arguments);
		System.out.println("key = " + key);

		try {
			// 判断是否有缓存
/*			if (redisService.exists(SerializeUtil.serialize(key))) {
				System.out.println("result = "+SerializeUtil.unserialize(redisService.get(SerializeUtil.serialize(key))).getClass());
				//return returnType(clazz, key);
				//return returnType(clazz, SerializeUtil.unserialize(redisService.get(SerializeUtil.serialize(key))));
				return returnType(clazz,redisService.get(key));
			}*/
			
			// 写入缓存
			value = invocation.proceed();
/*			for (Object object : (List)value) {
				System.out.println("object = "+object.getClass()+" -> "+object+"  -> "+object);
			}
			if (value != null) {
				final String tkey = key;
				final Object tvalue = value;
				new Thread(new Runnable() {
					@Override
					public void run() {
						if (tkey.startsWith("com.service.impl.xxxRecordManager")) {
							redisService.set(SerializeUtil.serialize(tkey), SerializeUtil.serialize(tvalue.toString()),
									xxxRecordManagerTime.intValue());
						} else if (tkey.startsWith("com.service.impl.xxxSetRecordManager")) {
							redisService.set(SerializeUtil.serialize(tkey), SerializeUtil.serialize(tvalue.toString()),
									xxxSetRecordManagerTime.intValue());
						} else {
							//redisService.set(SerializeUtil.serialize(tkey), SerializeUtil.serialize(tvalue.toString()),defaultCacheExpireTime.intValue());
							redisService.lpush(SerializeUtil.serialize(tkey), SerializeUtil.serialize(tvalue));
						}
					}
				}).start();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			if (value == null) {
				return invocation.proceed();
			}
		}
		return value;
	}

	/**
	 * 是否加入缓存
	 * 
	 * @return
	 */
	private boolean isAddCache(String targetName, String methodName) {
		boolean flag = true;
		if (targetNamesList.contains(targetName) || methodNamesList.contains(methodName)) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 创建缓存key
	 * 
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 */
	private String getCacheKey(String targetName, String methodName, Object[] arguments) {
		StringBuffer sbu = new StringBuffer();
		sbu.append(targetName).append("_").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sbu.append("_").append(arguments[i]);
			}
		}
		return sbu.toString();
	}

	/**
	 * 转换返回类型
	 * 
	 * @param clazz
	 * @param obj
	 * @return
	 * @throws Exception
	 */
/*	public Object returnType(Class<?> clazz, Object obj) throws Exception {
		if (String.class.equals(clazz)) {
			return obj;
		} else if (byte.class.equals(clazz)) {
			return Byte.parseByte((String) obj);
		} else if (Byte.class.equals(clazz)) {
			return Byte.valueOf((byte) obj);
		} else if (boolean.class.equals(clazz)) {
			return Boolean.parseBoolean((String) obj);
		} else if (Boolean.class.equals(clazz)) {
			return Boolean.valueOf((boolean) obj);
		} else if (short.class.equals(clazz)) {
			return Short.parseShort((String) obj);
		} else if (Short.class.equals(clazz)) {
			return Short.valueOf((String) obj);
		} else if (int.class.equals(clazz)) {
			return Integer.parseInt((String) obj);
		} else if (Integer.class.equals(clazz)) {
			return Integer.valueOf((String) obj);
		} else if (long.class.equals(clazz)) {
			return Long.parseLong((String) obj);
		} else if (Long.class.equals(clazz)) {
			return Long.valueOf((String) obj);
		} else if (float.class.equals(clazz)) {
			return Float.parseFloat((String) obj);
		} else if (Float.class.equals(clazz)) {
			return Float.valueOf((String) obj);
		} else if (double.class.equals(clazz)) {
			return Double.parseDouble((String) obj);
		} else if (Double.class.equals(clazz)) {
			return Double.valueOf((String) obj);
		} else if (List.class.equals(clazz)) {
			List list = new ArrayList<>();
			for (Object object : JSONArray) {
				list.add(object);
			}
			return list;
		} else if (Map.class.equals(clazz)) {
			Map map = (Map)JSONObject.fromObject(obj);
			return map;
		} else if (Date.class.equals(clazz)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return sdf.parse((String) obj);
		}else if(obj==null){
			return null;
		}
		return JSONObject.fromObject(obj);
	}*/

	public Object returnType(Class<?> clazz, String value) throws Exception {
		if (String.class.equals(clazz)) {
			return value;
		} else if (List.class.equals(clazz)) {
			return JsonUtils.jsonToList(value, List.class);
		} else if (Map.class.equals(clazz)) {
			return JsonUtils.jsonToList(value, Map.class);
		} else if (Date.class.equals(clazz)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return sdf.parse(value);
		}
		return null;
	}
	
}
