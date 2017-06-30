package com.ssm.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.redis.RedisServiceUtil;
import com.ssm.service.Cache;

public class TestCacheImpl implements Cache<RedisServiceUtil>, BeanNameAware{
	
	Logger logger = LoggerFactory.getLogger(TestCacheImpl.class);
	
	private String name;
    private Class<?> type;
    private String[] keyField;
    private String category = "redis:cache";
    
    /**
     * 是否正在加载数据:初次加载,或者 重载.
     * <p>
     * 如果正在重载过程中,则忽略一些步骤.
     */
    private boolean loading = false;
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 加载数据用的 sql 的 id.
     * <p>
     * 这是一个完整的名称:FunctionMapper.queryAll
     */
    private String sqlId;
    protected RedisSerializer<String> strSerializer;
    
	private boolean loadOnStartUp =false;
	
	public boolean isLoadOnStartUp() {
		return loadOnStartUp;
	}
	public void setLoadOnStartUp(boolean loadOnStartUp) {
		this.loadOnStartUp = loadOnStartUp;
	}

	@Override
	public void init(){
		strSerializer = redisTemplate.getStringSerializer();
		
		System.out.println("loadOnStart = "+this.loadOnStartUp);
		this.redisTemplate("admin");
		initLoad();
	}
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	public void redisTemplate(String key) {
		try {
			redisTemplate.opsForValue().set("db", "dbTest");
			redisTemplate.opsForValue().set("admin", "admin");
			
			String captchaCodeInRedis = redisTemplate.opsForValue().get(key);
			
			redisTemplate.execute((RedisCallback<Object>) connection -> {
	            connection.del(key.getBytes());
	            return null;
	        });
			System.out.println("key = "+key);
			
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled())
				logger.debug("redis 未启动");
		}
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
    
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String[] getKeyField() {
        return keyField;
    }

    /**
     * 指定查询结果中 哪一的字段作为 cache 的 key.
     * <p>
     * 多个字段时,用','分割
     *
     * @param keyField
     *            bean 的属性名
     */
    public void setKeyField(String[] keyField) {
        this.keyField = keyField;
    }
	
	public RedisSerializer<String> getStrSerializer() {
		return strSerializer;
	}
	public void setStrSerializer(RedisSerializer<String> strSerializer) {
		this.strSerializer = strSerializer;
	}
	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}
	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public RedisServiceUtil getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setValue(String key, RedisServiceUtil value) {
		// TODO Auto-generated method stub
	}
	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
	}
	@Override
	public String getCacheKey(RedisServiceUtil value) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void reload() {
		setLoading(true);
        try {
            clear();
            initLoad();
        } finally {
            setLoading(false);
        }
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}
	@Override
    public void setBeanName(String name) {
        if (getName() == null) {
            setName(name);
        }
    }
	
	/**
     * this method will called when cache first init, and reload cache.
     * <p>
     * pls make sure NOT cause side effect when reload
     */
    protected void initLoad() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.select(sqlId, (resultContext) -> {
                Object row = resultContext.getResultObject();
                handleRow(row);
            });
        } catch (Throwable e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
        }
    }
	
    /**
     * when loadOnStartUp is true,every row in resultset will be passed to this.
     * method
     *
     */
    protected void handleRow(Object row) {
        try {
            Map<String, Object> rowMap = convertToMap(row);
            String key = getKeyOfBean(row, keyField);
            setValue(key, rowMap);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, Object> convertToMap(Object obj)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        }
        Map<String, Object> map = PropertyUtils.describe(obj);
        map.remove("class"); // describe会包含 class 属性,此处无用
        return map;
    }
    
    private void setValue(String key, Map<String, Object> value) {
        byte[] keyBytes = strSerializer.serialize(getFullKey(key));
        Map<byte[], byte[]> data = new HashMap<>();
        value.forEach((k, v) -> {
            // 排除特殊字段
            if (k.charAt(0) == '_') {
                return;
            }
            if (v instanceof java.util.Date) {
                v = ((Date) v).getTime();
            }
            if (v != null) {
                data.put(strSerializer.serialize(k), strSerializer.serialize(v.toString()));
            }
        });

        redisTemplate.execute((RedisCallback<Object>) (connection) -> {
            connection.hMSet(keyBytes, data);
            return null;
        });
    }
    
    public String getCategory() {
        return category;
    }
    
    protected String getFullKey(String key) {
        return new StringBuilder(getCategory()).append(":").append(getName()).append(":").append(key).toString();
    }
    
    /**
     * 从 bean 中取出指定的 field 值,拼接.
     *
     * @param bean
     *            bean
     * @param properties
     *            属性
     * @return 拼接后的
     * @throws IllegalAccessException
     *             {@link BeanUtils#getProperty(Object, String)}
     * @throws NoSuchMethodException
     *             {@link BeanUtils#getProperty(Object, String)}
     * @throws InvocationTargetException
     *             {@link BeanUtils#getProperty(Object, String)}
     */
    public static String getKeyOfBean(Object bean, String[] properties)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String key = BeanUtils.getProperty(bean, properties[0]);
        if (properties.length > 1) {
            StringBuilder sb = new StringBuilder(key);
            for (int i = 1; i < properties.length; ++i) {
                sb.append('.').append(BeanUtils.getProperty(bean, properties[i]));
            }
            key = sb.toString();
        }
        return key;
    }
	public boolean isLoading() {
		return loading;
	}
	public void setLoading(boolean loading) {
		this.loading = loading;
	}

}
