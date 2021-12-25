import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
*非短路操作：必须所有元素都处理完才能得到最终的结果
*短路操作：一旦得到符合条件的元素就可以中断流得到最终的结果
 */
public class StreamOperationEnd {
    public static void main(String[] args) {
        String str = "bu gao xing jiu he shui";

// 使用foreach得到乱序结果(因为用了并行流)
// 输出：n go xu sgbuhiaui  hiej
        str.chars().parallel().forEach(i -> System.out.print((char) i));
// 使用forEachOrdered得到顺序结果
// 输出bu gao xing jiu he shui
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));

// 收集到list集合中
// 输出：[bu, gao, xing, jiu, he, shui]
        List<String> list = Stream.of(str.split(" "))
                .collect(Collectors.toList());
        System.out.println(list);

// 使用reduce对字符串进行拼接
// 输出bu_gao_xing_jiu_he_shui
        Optional<String> stringOptional = Stream.of(str.split(" "))
                .reduce((s1, s2) -> s1 + "_" + s2);
        System.out.println(stringOptional.get());

// 计算字符串总长度
// 输出：18
        Integer length = Stream.of(str.split(" ")).map(s -> s.length())
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(length);

// 取长度最大的单词
// 输出：xing
        Optional<String> max = Stream.of(str.split(" "))
                .max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());

// 使用findFirst取第一个元素，取到则中断操作
// 输出：bu
        Optional<String> findFirst = list.stream().findFirst();
        System.out.println(findFirst.get());
    }
}
