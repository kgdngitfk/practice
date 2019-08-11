import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * Created by User on 2017/10/16.
 */
@Slf4j
public class Test1 {
    @Test
    public void test0() {
        System.out.println("fdsfaaf需要");
        Integer a = -321;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toHexString(a));
    }


    @Test
    public void test1() {
        Random random = new Random();
        int loop = 30000;
        long startTime = System.currentTimeMillis();
        List<Integer> objects = new ArrayList<>();
        for (int i = 0; i < loop; i++) {
            objects.add(i);
        }

        startTime = System.currentTimeMillis();
        JSON.toJSONString(objects);

        log.info("add{},use time{}", loop, System.currentTimeMillis() - startTime);

    }

    @Test
    public void test2() {
        int loop = 30000;

        long startTime = System.currentTimeMillis();

        LinkedList<Integer> objects1 = new LinkedList<>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            objects1.add(i);
        }

        startTime = System.currentTimeMillis();

        JSON.toJSONString(objects1);
        Iterator<Integer> iterator = objects1.iterator();

        log.info("add{},use time{}", loop, System.currentTimeMillis() - startTime);
    }
}
