package org.aknife.connection.thread;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * 公共操作线程执行者，执行对象都是需要操作多个用户数据的任务，保证多账号的并发问题
 * @ClassName CommonOperationThreadUtil
 * @Author HeZiLong
 * @Data 2021/1/21 15:57
 */
public class CommonOperationThreadUtil {

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 8;

    private static final int MAX_MUN_POOL_SIZE = 20;

    private static final ExecutorService[] executors = new ExecutorService[CORE_POOL_SIZE];

    static {
        for (int i=0;i<CORE_POOL_SIZE;i++){
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    public static void runTask(int id, Runnable task){
        ExecutorService executor = executors[id % CORE_POOL_SIZE];
        executor.execute(task);
    }
}
