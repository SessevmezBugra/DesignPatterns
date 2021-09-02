package singleton;


class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing a lazy singleton");
    }

//    public static synchronized LazySingleton getInstance() {
//        if(instance == null)
//        {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    public static LazySingleton getInstance() {
        if(instance == null)
        {
            synchronized (LazySingleton.class)
            {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
class LazyAndThreadSafetySingletonTest {
    public static void main(String[] args) {

    }
}
