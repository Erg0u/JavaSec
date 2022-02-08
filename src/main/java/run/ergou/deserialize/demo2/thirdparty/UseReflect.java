package run.ergou.deserialize.demo2.thirdparty;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// classpath中存在的可被序列化的，反射使用构造函数参数的风险类
public class UseReflect implements Serializable {
    private final String className;
    private final Class<?>[] paramTypes;
    private final Object[] initParams;
    private final String methodName;

    public UseReflect(String className, Class<?>[] paramTypes, Object[] initParams, String methodName) {
        this.className = className;
        this.paramTypes = paramTypes;
        this.initParams = initParams;
        this.methodName = methodName;
    }

    public void useReflect() {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getConstructor(paramTypes);
            Object o = constructor.newInstance(initParams);
            Method method = aClass.getMethod(methodName);
            method.invoke(o);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
