package run.ergou.deserialize.rule;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class User implements Serializable {
    static {
        System.out.println("初始化");
    }

    public User() {
        System.out.println("实例化");
    }

    // ObjectInputStream#readObject()时，对流中指定类(classpath存在)，调用该类readObject(ObjectInputStream in)方法，构建对象
    // 读取流中指定类的字段值，赋值给所构建对象的相应字段
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        System.out.println("readObject()方法执行");
    }

}
