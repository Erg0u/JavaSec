package run.ergou.jndi.ldap;

import javax.naming.InitialContext;

public class JndiLookup {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        context.lookup("ldap://127.0.0.1:1389/obj");
    }
}