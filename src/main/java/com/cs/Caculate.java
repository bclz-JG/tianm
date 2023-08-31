package com.cs;

import com.cs.entity.Ball;

import java.util.*;
import java.util.stream.Collectors;

public class Caculate {
    public List<Integer> va() {
        Map<String, List<Ball>> rb = this.queryRB();
        List<Ball> red = rb.get("red");
        List<Ball> blue = rb.get("blue");

        List<Integer> r = this.queryRed(red);
        Collections.sort(r);
        Integer b = this.queryBlue(blue);

        List<Integer> v = new ArrayList<>(r);
        v.add(b);
        return v;
    }

    private List<Integer> queryRed(List<Ball> balls) {
        List<Integer> numbers = balls.stream().map(Ball::getCsNum).collect(Collectors.toList());
        List<Float> poss = balls.stream().map(Ball::getCsRatio).collect(Collectors.toList());
        List<Integer> red = this.pick(numbers, poss, 6);
        return red;
    }

    private Integer queryBlue(List<Ball> balls) {
        List<Integer> numbers = balls.stream().map(Ball::getCsNum).collect(Collectors.toList());
        List<Float> poss = balls.stream().map(Ball::getCsRatio).collect(Collectors.toList());
        List<Integer> blue = this.pick(numbers, poss, 1);
        return blue.get(0);
    }

    private Map<String, List<Ball>> queryRB() {
        Query query = new Query();
        Map<String, List<Ball>> map = query.queryRB();
        return map;
    }

    //抽取功能实现
    private List<Integer> pick(List<Integer> numbers, List<Float> probabilities, int numberOfDraws) {
        List<Integer> drawnNumbers = new ArrayList<>();
        Random random = new Random();
        Set<Integer> usedIndexes = new HashSet<>();

        while (drawnNumbers.size() < numberOfDraws) {
            double totalWeight = probabilities.stream().mapToDouble(Float::doubleValue).sum();
            double randomValue = random.nextDouble() * totalWeight;

            double cumulativeWeight = 0.0;
            for (int j = 0; j < numbers.size(); j++) {
                if (usedIndexes.contains(j)) {
                    continue; // Skip used indexes
                }

                cumulativeWeight += probabilities.get(j);
                if (randomValue < cumulativeWeight) {
                    drawnNumbers.add(numbers.get(j));
                    usedIndexes.add(j);
                    break;
                }
            }
        }
        return drawnNumbers;
    }

}
