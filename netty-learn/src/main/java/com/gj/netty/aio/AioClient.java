package com.gj.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-5
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 */
public class AioClient {
    private AsynchronousSocketChannel client;

    public AioClient(String host, int port) throws IOException, ExecutionException, InterruptedException {
        this.client = AsynchronousSocketChannel.open();
        Future<?> future = client.connect(new InetSocketAddress(host, port));

        future.get();
    }

    public void write(byte[] b){
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put(b);
        byteBuffer.flip();
        client.write(byteBuffer);
    }
}
