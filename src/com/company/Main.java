package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        /*
        Stream API - способ работать со структурами данных в функциональном стиле
        Threads (нити)
         */

        int[] arr = {60,400,40,100,3,5,10};
        for(int x: arr) {
            if(x >= 50)continue;
            x +=10;
            System.out.print(x + " ");
        }
        System.out.println();
        IntStream.of(60,400,40,100,3,5,10).filter(x -> x < 50).map(x -> x+10).forEach(System.out::println);

        Collection<Integer> numbers = Arrays.asList(1,2,3,4);
        System.out.println();

        // получить сумму четных чисел
        // collect преобразует stream в коллекцию или другую структуру
        long sum =  numbers.stream().collect(Collectors.summingInt(( (p) -> p%2 == 0 ? p : 0 )));
        System.out.println(sum);

        // вычесть с каждого элемента -1 и вывести среднее значение
        double average = numbers.stream().collect(Collectors.averagingInt( (p) -> p - 1  ));
        System.out.println(average);

        // разделить числа на четные и нечетные
        Map<Boolean, List<Integer>> res = numbers.stream().collect(Collectors.partitioningBy( (p) -> p%2 == 0 ));
        System.out.println(res);

        Collection<String> strings = Arrays.asList("a1","b2","c3","d4","a1","d10");

        // Получить список без повторов
        List<String> unique = strings.stream().distinct().collect(Collectors.toList());
        System.out.println(unique);

        // Объединение строк через разделитель
        String result = strings.stream().collect(Collectors.joining(" : ","<b>","</b>"));
        System.out.println(result);

        // Преобразование в MAP, сгруппировать по первому символу строки
        Map<String,List<String>> groups = strings.stream().collect(Collectors.groupingBy( (p) -> p.substring(0,1)  ));
        System.out.println(groups);

        // Вернуть первый элемент из коллекции

        String first = strings.stream().findFirst().orElse("Что-то");
        System.out.println(first);

        // поиск элемента

        String find = strings.stream().filter("c3"::equals).findFirst().get();
        System.out.println(find);

        // вернуть четвертый элемент коллекции по порядку
        String fourth = strings.stream().skip(3).findFirst().get();
        System.out.println(fourth);

        // выбрать все элементы, где есть 1
        List<String> list = strings.stream().filter((p) -> p.contains("1")).collect(Collectors.toList());
        System.out.println(list);

        // найти существует ли хоть одно совпадение с шаблоном
        boolean flag = strings.stream().anyMatch("a1"::equals);
        System.out.println(flag);


        Collection<String> strings2 = Arrays.asList("a1","a","d10");
        // найти существует ли хоть одно совпадение с шаблоном
        boolean flag2 = strings2.stream().allMatch((p) -> p.contains("1"));
        System.out.println(flag2);

        // вернуть количество вхождений элемента

        long count = strings2.stream().filter("a1"::equals).count();
        System.out.println(count);

        Collection<People> peoples = Arrays.asList(
                new People("Ivan",20),
                new People("Olga",23),
                new People("Max",25));

        // Найти средний возраст
        double aver_age = peoples.stream().mapToInt(People::getAge).average().getAsDouble();
        System.out.println(aver_age);

        // вернуть первые два элемента
        List<People> limit = peoples.stream().limit(2).collect(Collectors.toList());
        System.out.println(limit);

        People last = peoples.stream().skip(peoples.size()-1).findAny().orElse(new People("",0));
        System.out.println(last.getName());

        // изменение всех элементов коллекции

        List<String> transform = peoples.stream().map( (s) -> s.getName()+"_test").collect(Collectors.toList());
        System.out.println(transform);

        // убрать первый символ из имени

        List<String> transform2 = peoples.stream().map ( (s) -> s.getName().substring(1,s.getName().length())).collect(Collectors.toList());
        System.out.println(transform2);


        // найти человека с максимальным возрастом

        People people = peoples.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();
        System.out.println(people);


        // reduce - метод, который позволяется выполнять агрегатные функциии на всей коллекции
        Collection<Integer> collection = Arrays.asList(1,2,3,4,2);

        // считаем сумму
        Integer itog = collection.stream().reduce( (s1,s2) -> s1+s2).orElse(0);
        System.out.println(itog);

        // вернуть сумму чисел > 2

        Integer itog2 = collection.stream().filter(o-> o>2).reduce( (s1,s2) -> s1+s2).orElse(0);
        System.out.println("itog2="+itog2);

        // отсортировать значения по алфавиту и убрать дубликаты
        Collection<String> strings3 = Arrays.asList("a1","a4","a3","a2","a1","a4");

        List<String> distinct = strings3.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println(distinct);

        // отсорттровать по алфавиту в обратном порядке
        List<String> distinct2 = strings3.stream().sorted((o1,o2) -> -o1.compareTo(o2)).collect(Collectors.toList());
        System.out.println(distinct2);


    }
}
class People {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private Integer age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}