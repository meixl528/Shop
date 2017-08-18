package com.ssm.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import com.ssm.common.service.AppContextInitListener;
import com.ssm.common.service.Cache;
import com.ssm.common.service.InitService;

/**
 * 调用接口方法, 实际是执行 实现类中的 方法
 */
//@Service("initService")
public class InitServiceImpl implements InitService, AppContextInitListener{
	
	private HashMap<String, Cache<?>> cacheMap = new HashMap<>();
	private List<Cache<?>> caches;
	private boolean init;

	public void setCaches(List<Cache<?>> caches) {
		this.caches = caches;
		if(caches!=null){
			for(Cache<?> c: caches){
				cacheMap.put(c.getName(), c);
				/**
				 * 调用接口方法, 实际是执行 实现类中的 方法
				 */
				c.init();
			}
		}
	}

	public List<Cache<?>> getCaches() {
        return caches;
    }

    public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
		System.out.println("init = "+init);
	}

	@Override
    public <T> Cache<?> getCache(String name) {
        return cacheMap.get(name);
    }

    @Override
    public void addCache(Cache<?> cache) {
        if (caches == null) {
            caches = new ArrayList<>();
        }
        if (!caches.contains(cache)) {
            caches.add(cache);
        }
        cacheMap.put(cache.getName(), cache);
    }
    
    //@Autowired
    //private ApplicationContext app;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		//System.out.println("ApplicationContext = "+app.getBeansOfType(Cache.class));
		if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            Map<String, Cache> cacheBeans = applicationContext.getBeansOfType(Cache.class);
            if (cacheBeans != null) {
                cacheBeans.forEach((k, v) -> {
                    if (!caches.contains(v)) {
                        if (StringUtils.isEmpty(v.getName())) {
                            throw new RuntimeException(v + " cacheName is empty");
                        }
                        addCache(v);
                        v.init();
                    }
                });
            }
        }
	}

}
