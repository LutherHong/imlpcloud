package zzh.inspur.testcomparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author imlp
 * @date 2021/2/3 15:45
 */
public class testComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("001");
        list.add("014");
        list.add("023");
        list.add("002");
        list.add("007");
        list.add("001");
        Collections.sort(list);
        System.out.println(list);
        String[] arr=new String[] {"001","014","023","002","007","001"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
