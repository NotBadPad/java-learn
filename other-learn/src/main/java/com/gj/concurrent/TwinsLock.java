package com.gj.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by guojing on 16/9/16.
 */
public class TwinsLock extends Lock {

    public void lock() {

    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {

    }

    public Condition newCondition() {
        return null;
    }

    private static final class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            if (count <= 0){
                throw new IllegalArgumentException("count must large than 0.");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reductCount) {
            for (;;){
                int current = getState();
                int newCount = current - reductCount;
                if (newCount < 0 || compareAndSetState(current, newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;){
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)){
                    return true;
                }
            }
        }
    }
}
