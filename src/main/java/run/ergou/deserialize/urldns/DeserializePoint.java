package run.ergou.deserialize.urldns;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializePoint {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("urldns")));
        ois.readObject();
        ois.close();
    }
}
