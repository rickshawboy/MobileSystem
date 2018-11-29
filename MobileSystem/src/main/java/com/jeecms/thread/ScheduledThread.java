package com.jeecms.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * springboot启动时，创建线程池
 * @author jinlei
 *
 */
@Component
public class ScheduledThread implements CommandLineRunner {
	
	
	@Value("${token}")
	private String token;
	
	@Value("${time}")
	private Long time;

	@Override
	public void run(String... args) throws Exception {
				
		//使用定长线程池，可支持定时及周期性任务执行；
		ScheduledExecutorService  executor=Executors.newScheduledThreadPool(1);
		
		executor.scheduleWithFixedDelay(
				new EchoServer(),
				0,//初始初始化延时
				1,//前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
				TimeUnit.MINUTES);//计时单位
		
		
	}
	
	class EchoServer implements Runnable {
	@Override
	public void run() {

		/********这里执行插入数据库的步骤*********/
		
		}
	}
}
