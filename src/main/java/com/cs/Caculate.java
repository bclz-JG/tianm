package com.cs;

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
        Map<Integer, Double> qBlue = this.qBlue();
        for (Map.Entry<Integer, Double> e : qBlue.entrySet()) {
            int num = e.getKey();
            double poss = e.getValue();
            System.out.println(num + "->" + poss);
        }
        Random random = new Random();
        int blue = random.nextInt(16) + 1;
        return blue;
    }

    private Map<Integer, Double> qBlue() {
        Query query = new Query();
        Map<Integer, Double> map = query.query(false);
        return map;
    }

}
