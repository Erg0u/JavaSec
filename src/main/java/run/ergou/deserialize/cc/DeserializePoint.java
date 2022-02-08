package run.ergou.deserialize.cc;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializePoint {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("cc")));
        User user = (User) ois.readObject();
        // TransformedMap.put() -> transformValue() -> valueTransformer(Transformer实现类).transform()
        user.getMap().put("age", "1");
    }
}
