package run.ergou.deserialize.chain;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

// Java版本：<8u71
public class DeserializePoint {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("chain")));
        // 利用链
        // ObjectInputStream#readObject() -> AnnotationInvocationHandler.readObject(ObjectInputStream in) ->
        // AbstractInputCheckedMapDecorator.setValue() -> TransformedMap.checkSetValue() ->
        // valueTransformer(Transformer实现类).transform()
        ois.readObject();
    }
}
