package com.jeecms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.jeecms.annotation.ValidUserName;

@Component
@Aspect
public class ValidUserNameAspect {
    

    //切入点（这个切点是针对于注解的）
	@Pointcut("@annotation(com.jeecms.annotation.ValidUserName)")
	public void validUserName() {
	
	}
	//切入点（这个切点需要使用execution表达式了）
	@Pointcut(value = "execution(* com.jeecms.controller.*.*(..))")
    public void excServerPonit() {
    }
	
	
    @Before(value="@annotation(validUserName)")
	public Object ValidUser(JoinPoint joinPoint,ValidUserName validUserName) {
		
		//获取方法上的所有参数
		Object[] arguments = joinPoint.getArgs();
		    
		for (Object object : arguments) {
            System.out.println(object);
        }		
		String value = validUserName.value();
		System.out.println(value);	
		return true;		
	}
}
