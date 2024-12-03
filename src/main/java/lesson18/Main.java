package lesson18;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> ints = new ArrayList<>(List.of(1,4,-2,5,0,6));
        ints.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
//   }.reversed());  по убыванию
        System.out.println("сортировка во возрастанию: " + ints);
    }
}
