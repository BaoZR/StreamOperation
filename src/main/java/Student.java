import java.util.*;
import java.util.stream.Collectors;

/*
   from 不高兴就喝水 https://mp.weixin.qq.com/s/gaLdtxoY_VEZzbfp36CmEA
 */

public class  Student {
    /**
     * id
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 分数
     */
    private Integer score;

    /**
     * 籍贯
     */
    private String area;

    public Student(Integer id, String name, String sex, Integer age, Integer score, String area) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.score = score;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student(1,"小明", "男", 18, 100, "江西"));
        studentList.add(new Student(2,"小花", "女", 17, 90, "安徽"));
        studentList.add(new Student(3,"小虎", "男", 21, 80, "黑龙江"));
        studentList.add(new Student(4,"小军", "男", 20, 85, "黑龙江"));

        // 1.获取studentList中所有的籍贯
        List<String> areaList1 = studentList.stream().map(Student :: getArea).collect(Collectors.toList());
        System.out.println("areaList1：" + areaList1.toString());
        // 2.获取studentList中所有的籍贯,并去重
        Set<String> areaList2 = studentList.stream().map(Student :: getArea).collect(Collectors.toSet());
        System.out.println("areaList2：" + areaList2.toString());
        // 3.将studentList转成map
        Map<Integer, Object> studentMap = studentList.stream().collect(Collectors.toMap(Student::getId, o -> o));
        System.out.println("studentMap：" + studentMap);

        // 1.获取小明的信息
        List<Student> xmStudent = studentList.stream().filter(o -> o.getName().equals("小明")).collect(Collectors.toList());
        System.out.println("获取小明的信息：" + xmStudent.toString());
        // 2.获取年龄大于18的学生名称
        List<String> filterList = studentList.stream().filter(o -> o.getAge() > 18).map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("获取年龄大于18的学生名称：" + filterList);

        // 1.按地区分组，得到学生id的Set集合
        Map<String, Set<Integer>> areaIdSetMap = studentList.stream().collect(Collectors.groupingBy(Student::getArea, Collectors.mapping(Student::getId, Collectors.toSet())));
        System.out.println("按地区分组，得到学生id的Set集合：" + areaIdSetMap);

        // 2.按性别分组
        Map<String, List<Student>> group1 = studentList.stream().collect(Collectors.groupingBy(Student::getSex));
        System.out.println("按性别分组：" + group1);

        // 3.将员工先按性别分组,再按籍贯分组
        Map<String, Map<String, List<Student>>> group2 = studentList.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.groupingBy(Student::getArea)));
        System.out.println("将员工先按性别分组,再按籍贯分组：" + group2);

        // 4.将学生按是否大于80分，分成两个map
        Map<Boolean, List<Student>> partMap = studentList.stream().collect(Collectors.partitioningBy(x -> x.getScore() > 80));
        System.out.println("将学生按是否大于80分，分成两个map：" + partMap);

        // 1.求学生集合总条数
        Long count = studentList.stream().collect(Collectors.counting());
        System.out.println("求学生集合总条数：" + count);
// 2.求学生的平均分数
        Double averageScore = studentList.stream().collect(Collectors.averagingDouble(Student::getScore));
        System.out.println("求学生的平均分数：" + averageScore);
// 3.求学生中的最高分
        Optional<Integer> maxScore = studentList.stream().map(Student::getScore).collect(Collectors.maxBy(Integer::compare));
        System.out.println("求学生中的最高分：" + maxScore.get());
// 4.一次性统计关于分数的所有信息
        DoubleSummaryStatistics collect = studentList.stream().collect(Collectors.summarizingDouble(Student::getScore));
        System.out.println("一次性统计关于分数的所有信息：" + collect);
// 5.统计总分前3的学生姓名并打印出来
        Map<String, Integer> map = new HashMap();
        studentList.stream().collect(Collectors.groupingBy(Student::getName))
                .forEach((k, v) -> map.put(k, v.stream().collect(Collectors.summingInt(Student::getScore))));
        map.keySet().stream().sorted(Comparator.comparing(item->map.get(item)).reversed()).limit(3).collect(Collectors.toList()).forEach(item-> System.out.println(item));

        // 1.获取所有学生的姓名,用逗号分割
        String joinNames = studentList.stream().map(o -> o.getName()).collect(Collectors.joining(","));
        System.out.println("获取所有学生的姓名,用逗号分割：" + joinNames);

        // 2.获取学生的年龄总和
        Optional<Integer> sum = studentList.stream().map(Student::getAge).reduce(Integer::sum);
        System.out.println("获取学生的年龄总和：" + sum.get());

        // 1.按分数升序排序
        List<String> sortList1 = studentList.stream().sorted(Comparator.comparing(Student::getScore)).map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("按分数升序排序：" + sortList1);

        // 2.按分数降序排序
        List<String> sortList2 = studentList.stream().sorted(Comparator.comparing(Student::getScore).reversed())
                .map(Student::getName).collect(Collectors.toList());
        System.out.println("按分数降序排序：" + sortList2);
        // 3.先按分数再按年龄升序排序
        List<String> sortList3 = studentList.stream()
                .sorted(Comparator.comparing(Student::getScore).thenComparing(Student::getAge)).map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("先按分数再按年龄升序排序：" + sortList3);
    }

}

