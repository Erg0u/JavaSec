package run.ergou.deserialize.demo1.thirdparty;

import java.io.Serializable;

// classpath中存在的包含风险方法的可被序列化的类
public class Risk implements Serializable {

    public void risk(String cmd) {
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
