package run.ergou.deserialize.demo1.thirdparty;

import java.io.Serializable;

// classpath中存在的可被序列化的普通类
public class Controlled implements Serializable {
    private final String cmd;
    public Controlled(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }
}
