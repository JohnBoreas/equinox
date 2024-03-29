package com.equinox.autoai.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author boreas
 * @create 2023-07-03 16:16
 */
@Slf4j
public class PythonExecutor {

    private static final String OS = System.getProperty("os.name");

    private static final String WINDOWS_PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1) + "python/";  // windows为获取项目根路径即可
    private static final String LINUX_PATH = "/ai/spider";// linux为python文件所在目录

    private static ExecutorService taskPool = new ThreadPoolExecutor(8, 16
            , 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(600)
            , new ThreadFactoryBuilder()
            .setNameFormat("thread-自定义线程名-runner-%d").build());

    /**
     * 执行python文件【异步 无需等待py文件执行完毕】
     *
     * @param fileName python文件地址
     * @param params   参数
     * @throws IOException
     */
    public static void execPythonFile(String fileName, String params) {
        taskPool.submit(() -> {
            try {
                exec(fileName, params);
            } catch (IOException e) {
                log.error("读取python文件 fileName=" + fileName + " 异常", e);
            }
        });

    }

    /**
     * 执行python文件 【同步 会等待py执行完毕】
     *
     * @param fileName python文件地址
     * @param params   参数
     * @throws IOException
     */
    public static void execPythonFileSync(String fileName, String params) {
        try {
            execSync(fileName, params);
        } catch (IOException e) {
            log.error("读取python文件 fileName=" + fileName + " 异常", e);
        }
    }

    private static void exec(String fileName, String params) throws IOException {
        log.info("读取python文件 init fileName={}&path={}", fileName, WINDOWS_PATH);
        Process process;
        if (OS.startsWith("Windows")) {
            // windows执行脚本需要使用 cmd.exe /c 才能正确执行脚本
            process = new ProcessBuilder("cmd.exe", "/c", "python", WINDOWS_PATH + fileName, params).start();
        } else {
            // linux执行脚本一般是使用python3 + 文件所在路径
            process = new ProcessBuilder("python3", LINUX_PATH + fileName, params).start();
        }

        new Thread(() -> {
            log.info("读取python文件 开始 fileName={}", fileName);
            BufferedReader errorReader = null;
            // 脚本执行异常时的输出信息
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            List<String> errorString = read(fileName, errorReader);
            log.info("读取python文件 异常 fileName={}&errorString={}", fileName, errorString);
        }).start();

        new Thread(() -> {
            // 脚本执行正常时的输出信息
            BufferedReader inputReader = null;
            inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> returnString = read(fileName, inputReader);
            log.info("读取python文件 fileName={}&returnString={}", fileName, returnString);
        }).start();

        try {
            log.info("读取python文件 wait fileName={}", fileName);
            process.waitFor();
        } catch (InterruptedException e) {
            log.error("读取python文件 fileName=" + fileName + " 等待结果返回异常", e);
        }
        log.info("读取python文件 fileName={} == 结束 ==", fileName);
    }

    /**
     * ProcessBuilder死锁：
     * 主进程中调用pb.start会创建一个子进程，用于执行shell /exe 脚本。子进程创建后会和主进程分别独立运行。
     * 主进程调用Process.waitfor等待子进程完成。
     * 进程中可以通过Process.getInputStream和Process.getErrorStream获取并处理。
     * 子进程不断向主进程发生数据，而主进程调用Process.waitfor后已挂起。
     * 互相等待
     * @param fileName
     * @param params
     * @throws IOException
     */
    private static void execSync(String fileName, String params) throws IOException {
        log.info("同步读取python文件 init fileName={}&path={}", fileName, WINDOWS_PATH);
        Process process;
        if (OS.startsWith("Windows")) {
            // windows执行脚本需要使用 cmd.exe /c 才能正确执行脚本
            process = new ProcessBuilder("cmd.exe", "/c", "python", WINDOWS_PATH + fileName, params).start();
        } else {
            // linux执行脚本一般是使用python3 + 文件所在路径
            process = new ProcessBuilder("python3", LINUX_PATH + fileName, params).start();
        }

        taskPool.submit(() -> {
            log.info("读取python文件 开始 fileName={}", fileName);
            BufferedReader errorReader = null;
            // 脚本执行异常时的输出信息
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            List<String> errorString = read(fileName, errorReader);
            log.info("读取python文件 异常 fileName={}&errorString={}", fileName, errorString);
        });

        taskPool.submit(() -> {
            // 脚本执行正常时的输出信息
            BufferedReader inputReader = null;
            inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> returnString = read(fileName, inputReader);
            log.info("读取python文件 fileName={}&returnString={}", fileName, returnString);
        });

        try {
            log.info("同步读取python文件 wait fileName={}", fileName);
            process.waitFor();
        } catch (InterruptedException e) {
            log.error("同步读取python文件 fileName=" + fileName + " 等待结果返回异常", e);
        }
        log.info("同步读取python文件 fileName={} == 结束 ==", fileName);
    }

    private static List<String> read(String fileName, BufferedReader reader) {
        List<String> resultList = Lists.newArrayList();
        String res = "";
        while (true) {
            try {
                if (!((res = reader.readLine()) != null)) break;
            } catch (IOException e) {
                log.error("读取python文件 fileName=" + fileName + " 读取结果异常", e);
            }
            resultList.add(res);
        }
        return resultList;
    }
}
