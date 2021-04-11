package HWStreamAPI;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] numbers = {1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4};
        Set<Integer> intList = new TreeSet<>();

        for (int filteredNumbers : numbers) {
            if(filteredNumbers > 0 && filteredNumbers  % 2 == 0){
                intList.add(filteredNumbers);
                continue;
            }
        }
        Iterator<Integer> it = intList.iterator();
            while (it.hasNext()) {
                int num = it.next();
                System.out.println(num);
            }
    }
}