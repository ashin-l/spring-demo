package com.example.springdemo.pkg.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.springdemo.util.HexStringUtil;

public class SocketClient {
    public static Map<String, Socket> soMap = new ConcurrentHashMap<String, Socket>();
    private static String NET_NUM = "5AA506FFFFFFFF00504C";

    static {
        New("lamp001", "127.0.0.1");
    }

    public static void New(String deviceUuid, String deviceIp) {
        try {
            var socket = new Socket(deviceIp, 20001);
            socket.setSoTimeout(3000);
            soMap.put(deviceUuid, socket);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void sendMsg(String deviceUuid, String msg) {
        var socket = soMap.get(deviceUuid);
        if (socket == null) {
            System.out.println("new socket!!!");
            New("lamp001", "127.0.0.1");
            socket = soMap.get(deviceUuid);
        }
        // socket.setOOBInline(true);
        try {
            // 建立连接后获取输出流
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            // DataInputStream inputStream = new DataInputStream(in);
            DataOutputStream outputStream = new DataOutputStream(out);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            // outputStream.write(pkgMsg(mTypeEnum, jsonStr));
            // outputStream.write('\n');
            outputStream.write(HexStringUtil.hexToBytes(NET_NUM));
            socket.close();
            soMap.remove(deviceUuid);
            // var resp = br.readLine();
            // System.out.println(resp);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
