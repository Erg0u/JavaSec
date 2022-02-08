package run.ergou.deserialize.demo2.thirdparty;

import java.io.Serializable;

// classpath中存在的可被序列化的调用风险方法的类
public class Middle implements Serializable {
    private  UseReflect useReflect;

    public void setReflect(UseReflect useReflect) {
        this.useReflect = useReflect;
    }

    public void middle() {
        useReflect.useReflect();
    }
}
