package run.ergou.deserialize.ysoserial;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    interface Log {
        void log();
        void readObject();
    }

    static class Logger implements Log {
        @Override
        public void log() {
            System.out.println("log");
        }

        @Override
        public void readObject() {
            System.out.println("readObject");
        }
    }

    static class LoggerHandler implements InvocationHandler {
        private final Log log;

        public LoggerHandler(Log log) {
            this.log = log;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before");
            Object result = method.invoke(log, args);
            System.out.println("after");
            return result;
        }
    }


    public static void main(String[] args) {
        Log logger = new Logger();
        Log proxyLogger = (Log) Proxy.newProxyInstance(Logger.class.getClassLoader(), Logger.class.getInterfaces(), new LoggerHandler(logger));
        proxyLogger.log();
        proxyLogger.readObject();
    }
}
