package com.cs;

import com.cs.entity.Ball;

import java.util.*;

public class Caculate {
    public List<Integer> va() {
        Set<Integer> r = this.queryRed();
        Integer b = this.queryBlue();

        List<Integer> v = new ArrayList<>(r);
        v.add(b);
        return v;
    }

    private Set<Integer> queryRed() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        int count = 6;

        while (set.size() < count) {
            int blue = random.nextInt(33) + 1;
            if (!set.contains(blue)) {
                set.add(blue);
            }
        }

        return set;
    }

    private Integer queryBlue() {
        Random random = new Random();
        int blue = random.nextInt(16) + 1;
        return blue;
    }

    private Map<String, List<Ball>> queryRB() {
        Query query = new Query();
        Map<String, List<Ball>> map = query.queryRB();
        return map;
    }

}
