package run.ergou.deserialize.demo2.thirdparty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

// readObject(ObjectInputStream in)方法间接调用风险方法
public class ReadObject implements Serializable {
    private final Middle middle;

    public ReadObject(Middle middle) {
        this.middle = middle;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        middle.middle();
    }

}
