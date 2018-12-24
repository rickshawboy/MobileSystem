package com.jeecms.task;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
/**
 * Spring Schedule是Spring自带的任务框架，简单说他就是一个简化版本的Quartz;
 * @author jinlei
 *
 */
@Component
public class ScheduledTask implements SchedulingConfigurer{

	public static String cron; 
	
	@Scheduled(cron = "0 * * * * ?")
    public void reportCurrentTimeCron() throws InterruptedException {
		
        System.out.println("任务执行！");
    }

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		   //项目部署时，会在这里执行一次，从数据库拿到cron表达式
        cron = "0 * * * * ?";

       Runnable task = new Runnable() {
           @Override
           public void run() {
              //任务逻辑代码部分.
              System.out.println("I am going:" + LocalDateTime.now());
           }
       };
       Trigger trigger = new Trigger() {
           @Override
           public Date nextExecutionTime(TriggerContext triggerContext) {
              //任务触发，可修改任务的执行周期.
              //每一次任务触发，都会执行这里的方法一次，重新获取下一次的执行时间        
              cron = "*/5 * * * * ?";
              CronTrigger trigger = new CronTrigger(cron);
              Date nextExec = trigger.nextExecutionTime(triggerContext);
              return nextExec;
           }
       };
       taskRegistrar.addTriggerTask(task, trigger);
		
	}
}
