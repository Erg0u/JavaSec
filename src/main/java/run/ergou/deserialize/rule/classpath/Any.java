package run.ergou.deserialize.rule.classpath;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Any implements Serializable {
    static {
        System.out.println("classpath中存在的任意可序列化类初始化");
    }

    public Any(Object any) {
        System.out.println("classpath中存在的任意可序列化类实例化");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        System.out.println("classpath中存在的任意可序列化类readObject()方法执行");
    }
}
