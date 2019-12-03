package com.belong.common.util.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Description: 网络工具类
 * @Author: fengyu
 * @CreateDate: 2019/11/27 10:08
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 10:08
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@AllArgsConstructor
@Data
@Slf4j
public class NetUtils {

    private static final String LOCAL_IP = "127.0.0.1";

    /**
     * 方法实现说明:检查远程端口是否可用
     *
     * @param ip
     * @param port
     * @return boolean
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:07
     */
    public static boolean remotePortAbled(String ip, int port) {
        try (Socket s = new Socket()) {
            SocketAddress add = new InetSocketAddress(ip, port);
            s.connect(add, 3000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 方法实现说明:检查本机端口是否可用
     *
     * @param port
     * @return boolean
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:07
     */
    public static boolean localPortAbled(int port) {
        try (Socket s = new Socket()) {
            s.bind(new InetSocketAddress(LOCAL_IP, port));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
