package io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean isRun = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (isRun) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty() && isRun) {
                        String answer = "";
                        if (str.contains("msg=Hello")) {
                            answer = "Hello, dear friend.";
                        }
                        if (str.contains("msg=Exit")) {
                            answer = "Goodbye.";
                            isRun = false;
                        }
                        if (str.contains("msg=What")) {
                            answer = "What?";
                        }
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(answer.getBytes());
                    }
                }
            }
        }
    }
}