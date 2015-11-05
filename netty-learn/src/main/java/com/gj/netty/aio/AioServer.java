package com.gj.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-5
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class AioServer {

    public AioServer(int port) throws IOException {
        final AsynchronousServerSocketChannel socketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));

            socketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {

                    socketChannel.accept(null, this);

                    handle(result);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
    }

    public void handle(AsynchronousSocketChannel asc) {
        ByteBuffer buffer = ByteBuffer.allocate(32);

        try {
            asc.read(buffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        buffer.flip();
        System.out.println(new String(buffer.array()));
    }
}
