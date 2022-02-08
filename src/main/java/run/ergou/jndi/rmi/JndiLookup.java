package run.ergou.jndi.rmi;

import javax.naming.InitialContext;

public class JndiLookup {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        context.lookup("rmi://localhost:1099/obj");
    }
}