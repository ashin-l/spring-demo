// package com.example.springdemo.pkg.socket;

// import java.io.InputStream;
// import java.io.OutputStream;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.util.Arrays;
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

// import com.example.springdemo.util.HexStringUtil;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;

// import io.jsonwebtoken.io.IOException;
// import lombok.extern.slf4j.Slf4j;

// @Component
// @Slf4j
// public class SocketServer implements ApplicationRunner {
//     private static Map<String, Socket> soMap = new ConcurrentHashMap<String, Socket>();
//     private static String NET_NUM = "5AA506FFFFFFFF00504C";

//     // @Value("${netty.port}")
//     private Integer port = 20001;
//     private boolean started;
//     private ServerSocket serverSocket;
//     // 使用多线程，需要线程池，防止并发过高时创建过多线程耗尽资源
//     private ExecutorService threadPool = Executors.newCachedThreadPool();

//     @Override
//     public void run(ApplicationArguments args) throws Exception {
//         start();
//     }

//     private void start() {
//         try {
//             serverSocket = new ServerSocket(port);
//             started = true;
//             log.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());
//         } catch (IOException | java.io.IOException e) {
//             log.error("端口异常信息", e);
//             System.exit(0);
//         }
//         while (started) {
//             try {
//                 Socket socket = serverSocket.accept();
//                 socket.setSoTimeout(3000);
//                 log.info("socket 已连接");
//                 soMap.put("lamp001", socket);
//                 // Runnable runnable = () -> {
//                 // try {
//                 // // 接收客户端数据
//                 // StringBuilder xmlString = onMessage(socket);
//                 // // 处理逻辑：xmlStringToEsb为处理结果
//                 // // 返回给客户端
//                 // sendMessage(socket, xmlStringToEsb);
//                 // socket.close();
//                 // } catch (IOException e) {
//                 // e.printStackTrace();
//                 // }
//                 // };
//                 // 接收线程返回结果
//                 // Future future = threadPool.submit(runnable);
//                 // logger.info(future.isDone() + "--------");
//             } catch (IOException | java.io.IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     // private static StringBuilder onMessage(Socket socket) {
//     // byte[] bytes = new byte[1024];
//     // int len;
//     // try {
//     // // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
//     // InputStream inputStream = socket.getInputStream();
//     // StringBuilder sb = new StringBuilder();
//     // while ((len = inputStream.read(bytes)) != -1) {
//     // // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//     // sb.append(new String(bytes, 0, len, "UTF-8"));
//     // }
//     // // 此处，需要关闭服务器的输出流，但不能使用inputStream.close().
//     // socket.shutdownInput();
//     // return sb;
//     // } catch (IOException e) {
//     // e.printStackTrace();
//     // }
//     // return null;
//     // }

//     public static void sendMessage(String deviceUuid, String message) {
//         var socket = soMap.get(deviceUuid);
//         if (socket == null) {
//             log.warn("not find uuid");
//             return;
//         }
//         try {
//             // 向客户端返回数据
//             OutputStream outputStream = socket.getOutputStream();
//             InputStream inputStream = socket.getInputStream();
//             // 首先需要计算得知消息的长度
//             // byte[] sendBytes = message.getBytes("UTF-8");
//             // 然后将消息的长度优先发送出去
//             // outputStream.write(sendBytes.length >> 8);
//             // outputStream.write(sendBytes.length);
//             // 然后将消息再次发送出去
//             // outputStream.write(sendBytes);
//             outputStream.write(HexStringUtil.hexToBytes(NET_NUM));
//             outputStream.flush();
//             outputStream.close();
//             log.info(NET_NUM);
//             byte[] bytes = new byte[1024];
//             int len;
//             log.info(">>>>>>>>>>");
//             len = inputStream.read(bytes);
//             // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//             var msg = new byte[len];
//             System.arraycopy(bytes, 0, msg, 0, len);
//             log.info(HexStringUtil.bytesToHex(msg));
//             // 此处，需要关闭服务器的输出流，但不能使用inputStream.close().
//             // socket.shutdownInput();
//             log.info("<<<<<<<<<<");
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
