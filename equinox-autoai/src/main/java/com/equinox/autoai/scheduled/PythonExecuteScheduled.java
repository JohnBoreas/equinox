package com.equinox.autoai.scheduled;

import com.equinox.autoai.util.PythonExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author boreas
 * @create 2023-07-03 13:15
 */
@Slf4j
@Service
public class PythonExecuteScheduled {

    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduleExecuteProcess(){
        log.info("定时执行上架...");
        PythonExecutor.execPythonFileSync("test_python.py", "");
    }
}
