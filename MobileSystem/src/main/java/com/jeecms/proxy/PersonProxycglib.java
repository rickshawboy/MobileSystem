package com.jeecms.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * CGLIB解决了动态代理的难题，它通过生成目标类子类的方式来实现来实现代理，而不是接口，规避了接口的局限性。
 * CGLIB是一个强大的高性能代码生成包（生成原理还没研究过），其在运行时期（非编译时期）生成被 代理对象的子类，并重写了被代理对象的所有方法，从而作为代理对象。
当然CGLIB也具有局限性，对于无法生成子类的类（final类），肯定是没有办法生成代理子类的
 * @author jinlei
 *
 */
public class PersonProxycglib implements MethodInterceptor{

    private static Object target;//维护一个目标对象
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public PersonProxycglib(Object target) {
        this.target = target;
    }
    
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        logger.info("Before Proxy");
        Object result = methodProxy.invokeSuper(proxy, args);
        logger.info("After Proxy");
        return result;
    }
    
  //为目标对象生成代理对象
    public  Object getProxyInstance() {
        //工具类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        return enhancer.create();
    }
    
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.getClass());
        PersonProxycglib sf = new PersonProxycglib(person);
        Person sd = (Person)sf.getProxyInstance();
        System.out.println(sd.getClass());
        sd.getSomething();
    }
}
