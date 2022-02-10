package run.ergou.rmi;

import run.ergou.rmi.interfaces.IUserList;
import run.ergou.rmi.customclass.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/*
    There are six ways to export remote objects:
        1.Subclassing UnicastRemoteObject and calling the UnicastRemoteObject() constructor.
        2.Subclassing UnicastRemoteObject and calling the UnicastRemoteObject(port) constructor.
*/
public class UserListImpl extends UnicastRemoteObject implements IUserList {

    // 继承UnicastRemoteObject，当构造函数执行时暴露该远程对象(RMI Server)
    public UserListImpl() throws RemoteException {
        super(22222);
    }

    @Override
    public User getUser(Object obj) {
        return new User(obj.toString());
    }

    @Override
    public String addUser(User user) {
        return user.getName();
    }

    @Override
    public void createUserList(List<Integer> list) throws RemoteException {
    }

    @Override
    public void delUser(Integer id) throws RemoteException {
    }
}
