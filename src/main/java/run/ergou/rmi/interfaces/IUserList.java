package run.ergou.rmi.interfaces;

import run.ergou.rmi.customclass.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// The Remote interface serves to identify interfaces whose methods may be invoked from a non-local virtual machine.
// Any object that is a remote object must directly or indirectly implement this interface.
// Remote接口用于标记方法允许远程调用
public interface IUserList extends Remote {
    // 参数为Object
    User getUser(Object obj) throws RemoteException;
    // 参数为自定义类
    String addUser(User user) throws RemoteException;
    // 参数为集合类
    void createUserList(List<Integer> list) throws RemoteException;
    // 参数为其他非基本数据类型（int double等直接传值，不进行序列化。String 2020年左右之后的版本，不再进行序列化）
    void delUser(Integer id) throws RemoteException;
}
