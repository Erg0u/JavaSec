package run.ergou.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception {

        // 创建RMI Registry
        LocateRegistry.createRegistry(1099);

        // 创建并暴露远程对象(RMI Server)
        UserListImpl userList = new UserListImpl();

        // 远程对象和RMI Registry name进行绑定
        Naming.bind("users", userList);
    }
}
