package org.aknife.connection.thread;

import java.util.concurrent.*;

/**
 * 个人用户操作线程执行者，该用户的所有请求分发到一个线程上
 * @ClassName PersonalThreadDistribution
 * @Author HeZiLong
 * @Data 2021/1/21 14:53
 */
public class PersonalThreadDistributionUtil {

    private static final int CORE_POOL_SIZE = 20;

    private static ExecutorService[] executors = new ExecutorService[CORE_POOL_SIZE];

    static {
        for (int i=0;i<executors.length;i++){
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    /**
     * 给指定userID用户的绑定线程执行线程
     * @param id
     * @param task
     */
    public static void runTask(Integer id, Runnable task) {
        int threadIndex = id % CORE_POOL_SIZE;
        executors[threadIndex].execute(task);
    }
}
