package com.gj.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by guojing on 2015/6/7.
 */
public class MultiplexerTimerServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;

    public MultiplexerTimerServer(int port) {
        try {
            selector = Selector.open(); //新建多路复用selector
            servChannel = ServerSocketChannel.open();   //新建channel
            servChannel.configureBlocking(false);  //设置非阻塞
            servChannel.socket().bind(new InetSocketAddress(port),1024); //端口、块大小
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("TimeServer is start, port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> ketIt = keys.iterator();
                SelectionKey key = null;
                while (ketIt.hasNext()){
                    key = ketIt.next();
                    ketIt.remove();
                    //处理对应key时间
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
