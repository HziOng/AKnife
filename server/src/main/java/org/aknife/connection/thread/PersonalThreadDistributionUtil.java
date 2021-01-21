package org.aknife.connection.thread;

import java.util.concurrent.*;

/**
 * 个人用户操作线程执行者，该用户的所有请求分发到一个线程上
 * @ClassName PersonalThreadDistribution
 * @Author HeZiLong
 * @Data 2021/1/21 14:53
 */
public class PersonalThreadDistributionUtil {

    private static final int CORE_POOL_SIZE = 40;

    private static ExecutorService[] executors = new ExecutorService[CORE_POOL_SIZE];

    private static final int MAX_MUM_POOL_SIZE = 100;


    static {
        for (int i=0;i<executors.length;i++){
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    /**
     * 给指定userID用户的绑定线程执行线程
     * @param userID
     * @param task
     */
    public static void runTask(Integer userID, Runnable task) {
        int threadIndex = userID % CORE_POOL_SIZE;
        executors[threadIndex].execute(task);
    }
}
