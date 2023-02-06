package cn.zephyr.ch2;

import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName BioClient
 * @Author laizonghao
 * @CreateTime 2023/1/6 21:52
 * @Description
 **/
public class BioClient {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1",8000);
        while (true) {
            String msg="hello socket";
            System.err.println("client send msg: "+msg);
            socket.getOutputStream().write(msg.getBytes());
            Thread.sleep(2000);
        }
    }
}
