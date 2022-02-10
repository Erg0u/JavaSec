package run.ergou.rmi.codebase;

import run.ergou.rmi.UserListImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServerLoadRemoteClass {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.rmi.server.hostname", "localhost");

        // JDK 8u121, 7u21, 6u45, 5u45之后默认为true
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");

        // 配置并安装SecurityManager
        System.setProperty("java.security.policy", RMIServerLoadRemoteClass.class.getResource("/").getPath().substring(1) + "SecurityManager.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // 创建RMI Registry
        LocateRegistry.createRegistry(1099);

        // 创建并暴露远程对象(RMI Server)
        UserListImpl userList = new UserListImpl();

        // 远程对象和RMI Registry name进行绑定
        Naming.bind("users", userList);
    }
}
