package run.ergou.deserialize.basic;

import java.io.Serializable;

public class Passwd implements Serializable {
    private String passwd;

    public Passwd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}