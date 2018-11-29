package com.jeecms.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDK自带的动态代理
 * 使用Java反射机制，它的好处理时可以为我们生成任何一个接口的代理类，并将需要增强的方法织入到任意目标函数。
 * 但它仍然具有一个局限性，就是只有实现了接口的类，才能为其实现代理。
 * @author jinlei
 *
 */
public class PersonProxyjdk implements InvocationHandler{

    private Object obj;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        Object result = null;
        try {
            logger.info("Before Proxy");
            result = method.invoke(obj, args);
            logger.info("After Proxy");
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static void main(String[] args) {
        PersonProxyjdk personProxy = new PersonProxyjdk();
        Iperson iperson = (Iperson) personProxy.bind(new Person());
        iperson.getSomething();
        iperson.doSomething();
    }

}
