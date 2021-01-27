package org.aknife.business.map.service;

import lombok.SneakyThrows;
import org.aknife.connection.thread.CommonOperationThreadUtil;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @ClassName MapTask
 * @Author HeZiLong
 * @Data 2021/1/27 12:10
 */
public class CallbackTask<T> implements Runnable{

    private Callable<T> callable;

    private Consumer<T> consumer;

    private int backTaskKey;

    public CallbackTask(Callable<T> callable, Consumer<T> consumer){
        this.callable = callable;
        this.consumer = consumer;
    }

    @SneakyThrows
    @Override
    public void run() {
        T call = callable.call();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                consumer.accept(call);
            }
        };
        CommonOperationThreadUtil.runTask(backTaskKey, runnable);
    }
}
