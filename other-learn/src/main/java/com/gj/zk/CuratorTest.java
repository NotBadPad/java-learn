package com.gj.zk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by guojing on 2017/5/29.
 */
public class CuratorTest {

    private static final Map<String, String> configMap = Maps.newConcurrentMap();
    private static final CuratorFramework client = CuratorFrameworkFactory.newClient(
            "localhost:2181",
            new RetryNTimes(10, 5000)
    );

    private static final String patch = "/configserver/dev/com/goodtemper";

    public static void main(String[] avgs) {
        client.start();

        //启动watch
        watch();

        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        //启动轮询刷新
//        new RefreshDataClass().run();
        service.scheduleAtFixedRate(new RefreshDataClass(), 5, 4, TimeUnit.SECONDS);

        //启动轮询打印
        service.scheduleAtFixedRate(new PrintClass(), 10, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void create(CuratorFramework client){
        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("page.size", 10);
            map.put("degrade.cache", 1);
            client.create().creatingParentsIfNeeded().forPath(patch, JSON.toJSONString(map).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void watch(){
        PathChildrenCache watcher = new PathChildrenCache(
                client,
                patch,
                true    // if cache data
        );

        watcher.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                ChildData data = event.getData();
                if (data == null) {
                    System.out.println("No data in event[" + event + "]");
                } else {
                    System.out.println("Receive event: "
                            + "type=[" + event.getType() + "]"
                            + ", path=[" + data.getPath() + "]"
                            + ", data=[" + new String(data.getData()) + "]"
                            + ", stat=[" + data.getStat() + "]");

                    refreshData(new String(data.getData()));
                }
            }
        });

        try {
            watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            System.out.println("Register zk watcher successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印配置数据
     */
    static class PrintClass implements Runnable{
        public void run() {
            System.out.println(JSON.toJSONString(configMap));
        }
    }

    static class RefreshDataClass implements Runnable{
        public void run() {
            try {
                String value = new String(client.getData().forPath(patch));
                refreshData(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void refreshData(String value){
        Map map = JSON.parseObject(value, new TypeReference<Map<String, Object>>(){});
        if (map !=null){
            configMap.putAll(map);
        }
    }
}
