package com.project.daox.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DaoContext implements ApplicationContextAware {

	private static ApplicationContext ctx;

	private DaoContext() {
	}

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		DaoContext.ctx = ctx;
	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public static <T> T getBean(String beanName, Class<T> beanClass) {
		T bean = ctx.getBean(beanName, beanClass);
		return bean;
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}

}
