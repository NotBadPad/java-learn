package com.gj.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by guojing on 16/6/12.
 */
public class ZkClientTest {
    public static void main(String[] avgs) {
        try {
            ZooKeeper zk = new ZooKeeper("localhost:2181", 4000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("已经触发了" + watchedEvent.getType() + "时间!");
                }
            });
            zk.create("/javatest","testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
