package run.ergou.deserialize.rule;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializePoint {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("rule"));
        // ObjectInputStream#readObject()时，对流中指定类(classpath存在)，调用该类readObject(ObjectInputStream in)方法，构建对象
        // 读取流中指定类的字段值，赋值给所构建对象的相应字段
        User user = (User) ois.readObject();
    }
}
