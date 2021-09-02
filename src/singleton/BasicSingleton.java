package singleton;

import java.io.*;

class BasicSingleton implements Serializable {
    private BasicSingleton() {

    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public  static BasicSingleton getInstance() {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    // ********************
    protected Object readResolve() {
        return INSTANCE;
    }
}


class BasicSingletonTest {

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception {
        try(FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream))
        {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception{
        try(FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            return  (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
//        BasicSingleton singleton = BasicSingleton.getInstance();
//        singleton.setValue(123);
//        System.out.println(singleton.getValue());

        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(111);
        String filename = "singleton.bin";
        saveToFile(singleton, filename);
        singleton.setValue(222);
        BasicSingleton singleton2 = readFromFile(filename);
        System.out.println(singleton2 == singleton);
        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());
    }
}
