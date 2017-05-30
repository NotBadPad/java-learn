package com.gj;

/**
 * Created by guojing on 2017/5/30.
 */
public interface ConfigServerClient {

    /**
     * 获取配置值
     * @param key
     * @return
     */
    String get(String key);
}
