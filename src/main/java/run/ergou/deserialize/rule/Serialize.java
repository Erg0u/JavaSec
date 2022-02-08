package run.ergou.deserialize.rule;

import run.ergou.deserialize.rule.classpath.Any;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serialize {
    public static void main(String[] args) throws Exception {
        User user = new User();
        // 使用classpath中存在的可序列化类
        Any any = new Any(user);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rule"));
        oos.writeObject(any);
        oos.close();
    }
}
