package org.aknife.connection.thread;

import org.aknife.business.base.exception.GlobalException;

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

    /**
     * 无返回值的线程任务运行方法
     * @param id
     * @param task
     */
    public static void runTask(int id, Runnable task){
        if (id < 0 || id >= CORE_POOL_SIZE){
//            throw new GlobalException("线程池异常，接收id无法映射到线程池"+ id);
            return;
        }
        ExecutorService executor = executors[id % CORE_POOL_SIZE];
        executor.submit(task);
    }

    /**
     * 有返回值的线程运行方法
     * @param id
     * @param task
     * @return
     */
    public static Future runTask(int id, Callable task){
        if (id < 0 || id >= CORE_POOL_SIZE){
//            throw new GlobalException("线程池异常，接收id无法映射到线程池"+ id);
            return null;
        }
        ExecutorService executor = executors[id % CORE_POOL_SIZE];
        return executor.submit(task);
    }
}
