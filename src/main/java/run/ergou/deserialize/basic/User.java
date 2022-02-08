package run.ergou.deserialize.basic;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private Passwd passwd;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passwd getPasswd() {
        return passwd;
    }

    public void setPasswd(Passwd passwd) {
        this.passwd = passwd;
    }
}
