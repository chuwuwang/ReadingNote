package com.loader.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Created by Lee64 on 2018/2/24.
 */

public class ThreadPollExecutorUtil {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POLL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POLL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE = 10L;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {

        private final AtomicInteger mCount = new AtomicInteger();

        @Override
        public Thread newThread(Runnable runnable) {
            int andIncrement = mCount.getAndIncrement();
            return new Thread(runnable, "ImageLoader##" + andIncrement);
        }

    };

    public static final Executor THREAD_POLL_EXECUTOR = new ThreadPoolExecutor(CORE_POLL_SIZE,
            MAXIMUM_POLL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), sThreadFactory);

}
