package com.moxuanran.learning.javaio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author wutao
 * @date 2023/3/9 17:14
 */
public class PlainOioServer {
    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (; ;){
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from" + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try{
                            out = clientSocket.getOutputStream();
                            out.write("Hi!\r\n".getBytes(Charset.defaultCharset()));
                            out.flush();
                            clientSocket.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                            try {
                                clientSocket.close();
                            }catch (IOException ex){}
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.serve(9090);
    }
}
