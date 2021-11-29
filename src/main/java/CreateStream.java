import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        //1.通过数组创建
        String[] arr = {"一","二","三","四","五"};
        Stream<String> stream1 = Arrays.stream(arr);
        stream1.forEach(System.out::println);

        //2.通过集合构建
        List<String> list = Arrays.asList("一","二","三","四","五");
        //2.1创建一个顺序流（串行执行）
        Stream<String> stream2 = list.stream();
        stream2.forEach(System.out::println);

        //2.2创建一个并行流（以多线程的方式对流进行操作，底层用的线程池）
        //通过parallel方法把顺序流转成并行流
        Stream<String> parallelStream1 = list.stream().parallel();
        parallelStream1.forEach(System.out::println);

        //通过集合创建一个并行流
        Stream<String> parallelStream2 = list.parallelStream();
        parallelStream2.forEach(System.out::println);

        //3.通过Stream的静态方法创建
        //3.1 iterate方法：
        Stream<Integer> stream3 = Stream.iterate(1,(x)->x+1).limit(6);
        stream3.forEach(System.out::println);

        //3.2 of 方法:
        Stream <String> stream4 = Stream.of("一","二","三","四","五");
        stream4.forEach(System.out::println);

        //3.3 generate 方法:
        Stream<String> stream5 = Stream.generate(()->"一二三四五").limit(3);
        stream5.forEach(System.out::println);

        //4.通过数字Steam创建
        IntStream intStream1 = IntStream.of(1,2,3);
        intStream1.forEach(System.out::println);
        IntStream intStream2 = IntStream.rangeClosed(1,3);
        intStream2.forEach(System.out::println);

        //5.通过random创建无限流
        IntStream randomStream = new Random().ints(1,3).limit(3);
        randomStream.forEach(System.out::println);
    }
}



















