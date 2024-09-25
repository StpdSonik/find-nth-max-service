package com.ksy.nthmax.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MathService {
    /**
     * Поиск n-го максимального числа в столбце
     *
     * @param numbers числа
     * @param n       позиция искомого числа
     * @return искомое число
     */
    public int findNthMaxNumber(List<Integer> numbers, int n) {
        int[] topN = new int[n];
        for (int i = 0; i < n; i++) {
            topN[i] = Integer.MIN_VALUE;
        }

        for (int num : numbers) {
            if (num > topN[n - 1]) {
                topN[n - 1] = num;
                for (int j = n - 1; j > 0 && topN[j] > topN[j - 1]; j--) {
                    int temp = topN[j];
                    topN[j] = topN[j - 1];
                    topN[j - 1] = temp;
                }
            }
        }
        return topN[n - 1];
    }
}
