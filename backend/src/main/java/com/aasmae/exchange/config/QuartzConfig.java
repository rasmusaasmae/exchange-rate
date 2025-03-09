package com.aasmae.exchange.config;

import com.aasmae.exchange.service.DailyCurrencyUpdateJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail dailyJobDetail() {
        return JobBuilder.newJob(DailyCurrencyUpdateJob.class)
                .withIdentity("dailyCurrencyUpdateJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger dailyJobTrigger(JobDetail dailyJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(dailyJobDetail)
                .withIdentity("dailyCurrencyUpdateJob")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
                .build();
    }

}