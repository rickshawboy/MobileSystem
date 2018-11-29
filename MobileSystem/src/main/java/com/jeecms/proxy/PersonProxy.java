package com.jeecms.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用静态代理类(局限性：需要固定的类编写接口（或许还可以接受，毕竟有提倡面向接口编程），需要实现接口的每一个函数（不可接受），同样会造成代码的大量重复，将会使代码更加混乱。)
 * @author jinlei
 *
 */
public class PersonProxy implements Iperson{

    private Iperson iPerson;
    private final static Logger logger = LoggerFactory.getLogger(PersonProxy.class);

    public PersonProxy(Iperson iPerson) {
        this.iPerson = iPerson;
    }
    
    @Override
    public void doSomething() {
        logger.info("Before Proxy");
        iPerson.doSomething();
        logger.info("After Proxy");
    }
    
   
    public void getSomething() {
        logger.info("Before Proxy");
        iPerson.getSomething();
        logger.info("After Proxy");
        
    }

    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy(new Person());
        personProxy.doSomething();
    }
   

}
