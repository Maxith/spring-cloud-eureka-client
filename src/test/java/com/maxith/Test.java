package com.maxith;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by zhouyou on 2017/8/31.
 */
public class Test {
    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static void main(String[] args) throws InterruptedException {
        String[] x = {"+", "-", ""};
        String[] y = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        String[][] z = new String[y.length][x.length];
        for (int i = 0; i < y.length; i++) {
            z[i] = x;
        }

        //计算结果
        String[][] result = cartesianProduct(z);
        for (int i = 0; i < result.length; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < result[i].length; j++) {
                sb.append(y[j]);
                sb.append(result[i][j]);
            }
            sb.append(y[y.length - 1]);

            try {
                Object obj = jse.eval(sb.toString());
                if (Integer.parseInt(String.valueOf(obj)) == 100){
                    System.out.println(sb.toString() + " = 100");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String[][] cartesianProduct(String[][] args) {
        int total = 1;
        int counterIndex = args.length - 1;
        int[] counter = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            total *= args[i].length;
            counter[i] = 0;
        }

        String[][] result = new String[total][args.length];
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < args.length; j++) {
                result[i][j] = args[j][counter[j]];
            }
            counterIndex = handle(counter, counterIndex, args);
        }
        return result;
    }

    private static int handle(int[] counter, int counterIndex, String[][] args) {
        counter[counterIndex]++;
        if (counter[counterIndex] >= args[counterIndex].length) {
            counter[counterIndex] = 0;
            counterIndex--;
            if (counterIndex >= 0) {
                handle(counter, counterIndex, args);
            }
            counterIndex = args.length - 1;
        }
        return counterIndex;
    }
}
