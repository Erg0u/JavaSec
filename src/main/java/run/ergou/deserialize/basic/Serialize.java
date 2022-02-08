package run.ergou.deserialize.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {
    public static void main(String[] args) throws Exception {
        Passwd passwd = new Passwd("123456");
        User user = new User(1);
        user.setPasswd(passwd);

        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("basic"));
        oos.writeObject(user);
        oos.close();

        // 本地存储、网络传递...

        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("basic"));
        User user1 = (User) ois.readObject();
        System.out.println(user1.getId());
        ois.close();

    }
}
