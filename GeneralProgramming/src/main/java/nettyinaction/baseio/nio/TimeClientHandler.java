package nettyinaction.baseio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : yuanqi
 * @since : 2020/7/17
 */
public class TimeClientHandler implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandler(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            // 先进行连接操作
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    try {
                        key = iterator.next();
                        iterator.remove();
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {

            }
        }
    }

    public void handleInput(SelectionKey key) throws IOException{
        if (key.isValid()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            // isConnectable表示可以建立连接
            if (key.isConnectable()) {
                // 完成连接操作
                if (socketChannel.finishConnect()) {
                    // 连接完成后同样注册到selector并期待读操作
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 发起请求
                    doWrite(socketChannel);
                } else {
                    System.exit(1);
                }
            }
            // 可以读数据代表服务端返回了相应结果
            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Now is : " + body);
                    this.stop = true;
                } else if (readBytes < 0) {
                    key.cancel();
                    socketChannel.close();
                } else {

                }
            }
        }

    }

    private void doConnect() throws IOException {
        // 连接服务端
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            // 连接成功后，将当前channel注册到selector上，等待读取数据
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            // 连接失败，则将自己注册为等待连接事件
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] request = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(request.length);
        writeBuffer.put(request);
        writeBuffer.flip();
        // 向客户端channel中写入请求，服务端selector将会读取这个请求
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("Send order to server succeed");
        }
    }
}
