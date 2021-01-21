package org.aknife.connection.thread;

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
    private static final int CORE_POOL_SIZE = 20;

    private static final int MAX_MUN_POOL_SIZE = 50;

    private static ExecutorService executor;

    static {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_MUN_POOL_SIZE,
                60,
                TimeUnit.SECONDS,
                queue
        );
    }

    public static void runTask(Runnable task){
        executor.execute(task);
    }
}
