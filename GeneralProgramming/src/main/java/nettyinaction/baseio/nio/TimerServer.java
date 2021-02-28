package nettyinaction.baseio.nio;

/**
 * @ClassName TimerServer
 * @Description:
 * @Author: YuanQi
 * @Date: 2020/5/15
 */
public class TimerServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        MultiplexerTimerServer timerServer = new MultiplexerTimerServer(port);
        new Thread(timerServer, "NIO-MultiplexerTimeServer-001").start();
    }


}
