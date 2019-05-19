package manu.cab.planetas.config;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import manu.cab.planetas.service.scheduleTasks.PopulateDB;

@Configuration
@EnableScheduling
public class SchedulerConfiguration implements SchedulingConfigurer {
	
	@Autowired
	private PopulateDB populateDB;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		taskRegistrar.addTriggerTask(new PopulateDBRunner(), new CustomTrigger());
		
	}
	
    private class PopulateDBRunner implements Runnable {

        @Override
        public void run() {
        	populateDB.populateDB();
            System.out.println("Este es un testing de Quartz");
        }

    }
    
    private class CustomTrigger implements Trigger {

        private int runRate = 24;

        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            Calendar nextExecutionTime = new GregorianCalendar();
            Date lastActualExecutionTime = (triggerContext != null) ? triggerContext.lastActualExecutionTime() : null;
            
            if(lastActualExecutionTime == null) {
            	nextExecutionTime.setTime(new Date());
            } else {
            	nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                nextExecutionTime.add(Calendar.HOUR, runRate);
            }
            
            return nextExecutionTime.getTime();
        }
    }

}
