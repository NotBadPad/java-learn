package com.gj.netty.aio;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-20
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
public class Display implements Runnable {
    //显示类线程，用来打印Content对象的值
    private Contents con=null;
    public Display(Contents con) {
        // TODO Auto-generated constructor stub
        this.con = con;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true)
        {
            con.getContent();
        }
    }
}
