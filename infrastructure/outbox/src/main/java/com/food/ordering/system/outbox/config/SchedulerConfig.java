package com.food.ordering.system.outbox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Enables scheduling in the application
 */
@Configuration
@EnableScheduling
public class SchedulerConfig { }
