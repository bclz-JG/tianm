package com.cs;

import com.cs.entity.Ball;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Possible {

    public void CacuPoss() {
        Query Q = new Query();
        List<Ball> red ;
        List<Ball> blue ;
        Map<String, List<Ball>> res = new HashMap<>();

        res = Q.queryRB();
        red = res.get("red");
        blue = res.get("blue");

        //处理red
        for (int i = 0; i < 6; i++) {
            int n = Public.NUMS[i];
            red.get(n).setCsCount(red.get(n).getCsCount() + 1);
        }
        for (int i = 0; i < red.size(); i++) {
            float poss = red.get(i).getCsCount() / Public.SUM;
            float ratio = (1 - poss) * (1 / 27);
            if (ratio == 0) {
                ratio = 1 / (33 * Public.SUM * 10);
            }
            red.get(i).setCsPoss(poss);
            red.get(i).setCsRatio(ratio);
        }

        for (int i = 0; i < 6; i++) {
            int n = Public.NUMS[i];
            red.get(n).setCsRatio(red.get(n).getCsRatio() * 0.1f);
        }

        //处理blue
        int n = Public.NUMS[6];
        blue.get(n).setCsCount(blue.get(n).getCsCount() + 1);
        for (int i = 0; i < red.size(); i++) {
            float poss = blue.get(i).getCsCount() / Public.SUM;
            float ratio = (1 - poss) * (1 / 15);
            if (ratio == 0) {
                ratio = 1 / (16 * Public.SUM * 10);
            }
            blue.get(i).setCsPoss(poss);
            blue.get(i).setCsRatio(ratio);
        }
        blue.get(n).setCsRatio(blue.get(n).getCsRatio() * 0.1f);

    }

}
