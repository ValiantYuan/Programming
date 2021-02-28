package nettyinaction.baseio.pseudoaio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import nettyinaction.baseio.bio.TimeServerHandler;

/**
 * @author : yuanqi
 * @since : 2020/7/20
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length != 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            TimeServerHandlerExecutorPool taskExecutor
                = new TimeServerHandlerExecutorPool(50, 10000);
            while (true) {
                socket = server.accept();
                taskExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }

    }
}
