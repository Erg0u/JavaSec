package run.ergou.deserialize.demo1.thirdparty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ReadObject implements Serializable {
    private final Risk risk;
    private Controlled controlled;

    public ReadObject(Risk risk) {
        this.risk = risk;
    }

    public void setControlled(Controlled controlled) {
        this.controlled = controlled;
    }

    // readObject(ObjectInputStream in)方法调用了风险方法
    // 风险方法使用了可控对象
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        risk.risk(controlled.getCmd());
    }

}
