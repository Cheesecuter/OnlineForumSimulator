package com.j2eeprac.Utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class JLogger implements MethodInterceptor {
	private Object target;
	private String localTime;

	@SuppressWarnings("unused")
	private static final Log logger2 = LogFactory.getLog(JLogger.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getLocalTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String localTime = formatter.format(calendar.getTime());
		return localTime;
	}

	public Object bind(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object result;
		localTime = getLocalTime();
		System.out.println("[" + localTime + "]: " + "正在登录验证");
		logger.info(localTime + "正在登录验证");
		result = proxy.invokeSuper(obj, args);
		System.out.println("[" + localTime + "]: " + "登陆验证完成");
		logger.info(localTime + "登陆验证完成");
		return result;
	}

}
