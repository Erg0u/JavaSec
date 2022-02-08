package run.ergou.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        // 默认hostname为内网地址
//        System.setProperty("java.rmi.server.hostname", "101.37.84.167");

        // JDK 8u121, 7u21, 6u45, 5u45之后 信任客户端codebase，加载远端类需要：
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }

        // 创建RMI Registry
        LocateRegistry.createRegistry(1099);

        // 创建远程对象
        UserListImpl userList = new UserListImpl();

        // 远程对象和RMI Registry name进行绑定
        Naming.bind("users", userList);
    }
}
