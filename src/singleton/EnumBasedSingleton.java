package singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum EnumBasedSingleton{
    INSTANCE;

    EnumBasedSingleton(){
        value = 42;
    }

    private int value;

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }
}
class EnumBasedSingletonTest {

    static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception {
        try(FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream))
        {
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename) throws Exception{
        try(FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            return  (EnumBasedSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "myfile.bin";
//        EnumBasedSingleton enumBasedSingleton1 = EnumBasedSingleton.INSTANCE;
//        enumBasedSingleton1.setValue(111);
//        saveToFile(enumBasedSingleton1, filename);

        EnumBasedSingleton enumBasedSingleton2 = readFromFile(filename);
        System.out.println(enumBasedSingleton2.getValue());
    }

}
