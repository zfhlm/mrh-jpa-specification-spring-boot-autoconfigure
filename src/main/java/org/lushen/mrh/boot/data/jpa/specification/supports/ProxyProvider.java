package org.lushen.mrh.boot.data.jpa.specification.supports;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.ClassUtils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理对象生成器
 * 
 * @author helm
 */
public interface ProxyProvider {

	/**
	 * 生成代理对象
	 * 
	 * @param proxyClass
	 * @param proxyHandler
	 * @return
	 * @throws Exception
	 */
	public <T> T proxy(Class<T> proxyClass, ProxyHandler proxyHandler) throws Exception;

	/**
	 * cglib动态代理对象生成器
	 * 
	 * @author helm
	 */
	@SuppressWarnings("unchecked")
	public class CglibProxyProvider implements ProxyProvider {

		@Override
		public <T> T proxy(Class<T> proxyClass, ProxyHandler proxyHandler) throws Exception {
			if(proxyClass == null) {
				throw new IllegalArgumentException("proxyClass is null.");
			}
			if(proxyHandler == null) {
				throw new IllegalArgumentException("proxyHandler is null.");
			}
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(proxyClass);
			enhancer.setCallback( new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxyHandler.invoke(proxyClass, obj, method, args);
				}
			});
			return (T)enhancer.create();
		}

	}

	/**
	 * jdk动态代理对象生成器
	 * 
	 * @author helm
	 */
	@SuppressWarnings("unchecked")
	public class JdkProxyProvider implements ProxyProvider {

		@Override
		public <T> T proxy(Class<T> proxyClass, ProxyHandler proxyHandler) throws Exception {
			if(proxyClass == null) {
				throw new IllegalArgumentException("proxyClass is null.");
			}
			if(proxyHandler == null) {
				throw new IllegalArgumentException("proxyHandler is null.");
			}
			Class<?>[] interfaces = ClassUtils.getAllInterfaces(proxyClass).stream().toArray(len -> new Class<?>[len]);
			return (T) Proxy.newProxyInstance(proxyClass.getClassLoader(), interfaces, new java.lang.reflect.InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					return proxyHandler.invoke(proxyClass, proxy, method, args);
				}
			});
		}

	}

}
