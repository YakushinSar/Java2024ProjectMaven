package lesson16_1;

import java.util.*;

public class MainTest {
    public static void main(String[] args) {

//лист
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(23);
            if (list.contains(23)) {
                System.out.println("данное значение присутвует");
            }
            System.out.println(list.get(0));
        System.out.println(list);
        System.out.println();

//множество
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(45);
        set.add(2); //добавляем повторное значение, оно не повторится
        if (set.contains(45)) {
            System.out.println("данное значение присутствует");
        }
        System.out.println(set);
        System.out.println();


//ключ:значение
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "один");
        map.put(2, "два");
        map.put(3,"ivan");
        System.out.println(map);
//        возвращает значение по ключу
        System.out.println(map.getOrDefault(1,"один"));
        }
    }
