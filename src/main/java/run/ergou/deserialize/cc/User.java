package run.ergou.deserialize.cc;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {
    private final Map map;

    public User(Map map) {
        this.map = map;
    }

    public Map<Object, Object> getMap() {
        return map;
    }
}
