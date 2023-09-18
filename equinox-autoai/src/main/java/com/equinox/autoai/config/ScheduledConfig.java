package com.equinox.autoai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置 {@link ScheduledConfig}
 *
 * @author Kevin
 * @email: 178676392@qq.com
 */
@EnableAsync
@EnableScheduling
@Configuration
public class ScheduledConfig {
}
