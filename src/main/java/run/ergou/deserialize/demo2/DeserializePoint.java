package run.ergou.deserialize.demo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializePoint {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("demo2")));
        // 利用链
        // ObjectInputStream#readObject() ->
        // ReadObject.readObject(ObjectInputStream in) -> Middle.middle() -> UseReflect.useReflect()
        ois.readObject();
    }
}
