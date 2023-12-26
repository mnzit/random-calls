package com.mnzit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Manjit Shakya
 */
public class Test {

    public static void main(String[] args) {

        List<Demo> input = new ArrayList<>();

        input.add(new Demo("null1", "1"));
        input.add(new Demo("null2", "1"));
        input.add(new Demo("null3", "1"));
        input.add(new Demo(null, "1"));

        System.out.println(input
                .stream()
                .map(Demo::getName)
                .filter(Objects::nonNull)
                .toList());

        Map<String, Integer> asd = new HashMap<>();
        asd.put("asd", 1);

        System.out.println(      asd.get(null));
    }

    static class Demo {
        String name;
        String id;

        public Demo(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }
}
