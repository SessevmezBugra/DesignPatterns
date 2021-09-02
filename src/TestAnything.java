import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestAnything {

    public long counter;

    public void wasCalled() {
        counter++;
    }

    public static void main(String[] args) {

        //Example1
//        IntStream intStream = IntStream.range(1, 3);
//        LongStream longStream = LongStream.rangeClosed(1, 3);
//
//        intStream.forEach((i) -> System.out.println(i));
//        longStream.forEach((i) -> System.out.println(i));

        //Example2
//        Stream<String> stream =
//                Stream.of("a", "b", "c").filter(element -> element.contains("b"));
//        Optional<String> anyElement = stream.findAny();
//        Optional<String> firstElement = stream.findFirst();// Hata verir cunku yukarida stream kullanildi find any diyerek.

        //Example3
//        List<String> elements =
//                Stream.of("a", "b", "c").filter(element -> element.contains("b"))
//                        .collect(Collectors.toList());
//        Optional<String> anyElement = elements.stream().findAny(); //Boyle calisir
//        Optional<String> firstElement = elements.stream().findFirst();

        //Example4
//        Stream<String> onceModifiedStream =
//                Stream.of("abcd", "bbcd", "cbcd").skip(1);
//        Stream<String> twiceModifiedStream =
//                onceModifiedStream.skip(1).map(element -> element.substring(0, 3));
//        twiceModifiedStream.forEach((i) -> System.out.println(i));

        //Example5
//        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
//        TestAnything testAnything = new TestAnything();
//        testAnything.counter = 0;
//        Stream<String> stream = list.stream().filter(element -> {
//            testAnything.wasCalled();
//            return element.contains("2");
//        });
//        System.out.println(testAnything.counter);
//
//        Optional<String> stream2 = list.stream().filter(element -> {
//            testAnything.wasCalled();
//            return element.contains("2");
//        }).findFirst();
//        System.out.println(testAnything.counter);
//
//        Optional<String> stream3 = list.stream().filter(element -> {
//            System.out.println("filter() was called");
//            return element.contains("2");
//        }).map(element -> {
//            System.out.println("map() was called");
//            return element.toUpperCase();
//        }).findFirst();
        //Example6
        OptionalInt reduced =
                IntStream.range(1, 4).reduce((a, b) -> a + b);
        System.out.println(reduced.getAsInt());

        int reducedTwoParams =
                IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        System.out.println(reducedTwoParams);

        int reducedParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reducedParams);

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reducedParallel);
    }
}
