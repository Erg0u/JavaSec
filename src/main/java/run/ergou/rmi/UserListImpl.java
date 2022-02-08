package run.ergou.rmi;

import run.ergou.rmi.remoteobject.IUserList;
import run.ergou.rmi.remoteobject.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserListImpl extends UnicastRemoteObject implements IUserList {

    protected UserListImpl() throws RemoteException {
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
