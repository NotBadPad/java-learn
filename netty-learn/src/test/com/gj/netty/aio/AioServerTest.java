package com.gj.netty.aio;


import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-5
 * Time: 下午5:24
 * To change this template use File | Settings | File Templates.
 */
public class AioServerTest {

    @Test
    public void testServer(){
        try {
            AioServer as = new AioServer(8686);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public  void testClient(){
        AioClient ac = null;
        try {
            ac = new AioClient("localhost",8686);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ac.write("aa".getBytes());
    }
}
