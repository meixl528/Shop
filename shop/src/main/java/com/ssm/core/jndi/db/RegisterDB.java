package com.ssm.core.jndi.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ssm.core.jndi.JndiObjectFactoryBean;
import com.ssm.core.jndi.RegisterDBService;

@Service
public class RegisterDB implements RegisterDBService{
	
	@Autowired
	private ApplicationContext ctx;
	
	private Map<Object,Object> map = new HashMap<>();
	
	private String writeDB;
	
	private String readDB;
	
	public String getWriteDB() {
		return writeDB;
	}
	public void setWriteDB(String writeDB) {
		this.writeDB = writeDB;
		List<String> list = registBean(writeDB,"readWriteDataSource");
		System.out.println("readWriteDataSource list = "+list);
		DynamicDataSourceHolder.DATA_SOURCE_RW = list.toArray(new String[list.size()]);
	}

	public String getReadDB() {
		return readDB;
	}
	public void setReadDB(String readDB) {
		this.readDB = readDB;
		List<String> list = registBean(readDB,"readOnlyDataSource");
		System.out.println("readOnlyDataSource list = "+list);
		DynamicDataSourceHolder.DATA_SOURCE_RW = list.toArray(new String[list.size()]);
	}

	
	private List<String> registBean(String db,String dataSource){
		List<String> list = new ArrayList<>();
		if(!StringUtils.isEmpty(db)){
			String[] dbs = db.split(",");
			for (int i = 0; i < dbs.length; i++) {
				String beanName = dataSource;
				if(!ctx.containsBean(dataSource+(i==0?"":i))){
					DefaultListableBeanFactory acf = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
			        
			        JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
			        beanName = dataSource+(i==0?"":i);
			        //BeanDefinition beanDefinition = new ChildBeanDefinition("dataSource");
			        
			        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(jndiBean.getClass());
		            beanDefinitionBuilder.addPropertyValue("jndiName", dbs[i]);
			        
		        	acf.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
		        }
				if(map.get(beanName)==null){
					map.put(beanName, ctx.getBean(beanName));
				}
				if(!list.contains(beanName)){
					list.add(beanName);
				}
			}
		}
		return list;
	}
	public Map<Object, Object> getMap() {
		return map;
	}
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
	
}
