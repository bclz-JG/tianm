package com.cs;

import com.cs.entity.Ball;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Possible {

    public Map<String, List<Ball>> CacuPoss(Map<String, List<Ball>> rb, int[] nums) {
        Map<String, List<Ball>> res = rb;
        List<Ball> red;
        List<Ball> blue;
        red = res.get("red");
        blue = res.get("blue");

        //处理red
        for (int i = 0; i < 6; i++) {
            int n = nums[i];
            red.get(n - 1).setCsCount(red.get(n - 1).getCsCount() + 1);
        }
        for (int i = 0; i < red.size(); i++) {
            float poss = red.get(i).getCsCount() / Public.SUM;
            float ratio = (1.0f - poss) * (1.0f / 27.0f);
            if (ratio == 0) {
                ratio = 1.0f / (33.0f * Public.SUM * 10.0f);
            }
            red.get(i).setCsPoss(poss);
            red.get(i).setCsRatio(ratio);
        }

        //对上次出现的补概率
        for (int i = 0; i < 6; i++) {
            int n = nums[i];
            red.get(n - 1).setCsRatio(red.get(n - 1).getCsRatio() * 0.1f);
        }

        //处理blue
        int n = nums[6];
        blue.get(n - 1).setCsCount(blue.get(n - 1).getCsCount() + 1);
        for (int i = 0; i < blue.size(); i++) {
            float poss = blue.get(i).getCsCount() / Public.SUM;
            float ratio = (1.0f - poss) * (1.0f / 15.0f);
            if (ratio == 0) {
                ratio = 1.0f / (16.0f * Public.SUM * 10.0f);
            }
            blue.get(i).setCsPoss(poss);
            blue.get(i).setCsRatio(ratio);
        }
        blue.get(n - 1).setCsRatio(blue.get(n - 1).getCsRatio() * 0.1f);

        return res;
    }

}
