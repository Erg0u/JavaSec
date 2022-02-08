package run.ergou.deserialize.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializePoint {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("demo1")));
        // 利用链
        // ObjectInputStream#readObject() ->
        // ReadObject.readObject(ObjectInputStream in) -> Risk.risk()
        ois.readObject();
    }
}
