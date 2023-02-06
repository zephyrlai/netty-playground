package cn.zephyr.ch2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName BioServer
 * @Author laizonghao
 * @CreateTime 2023/1/6 21:52
 * @Description
 **/
public class BioServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket socket = serverSocket.accept();
            // 每接收到一个新连接，都新起一个线程处理
            new Thread(() -> {
                try {
                    int len;
                    byte[] data = new byte[1024];
                    InputStream inputStream = socket.getInputStream();
                    while ((len = inputStream.read(data))!=-1) {
                        String msg = new String(data, 0, len);
                        System.err.println("server receive msg:"+msg);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        }


    }
}
