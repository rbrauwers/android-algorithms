package com.androidalgorithms.rbrauwers.android_algorithms;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * Created by rodrigobrauwers on 04/12/17.
 */

public class Algorithms {

    static String removeTrivialCases(String expr) {
        if (expr == null || expr.length() < 2) {
            return expr;
        }

        StringBuilder sb = new StringBuilder(expr);

        for (int i=0; i<expr.length()-1; i++) {
            char c0 = expr.charAt(i);
            char c1 = expr.charAt(i+1);
            if (c0 == '<' && c1 == '>') {
                sb.deleteCharAt(i);
                sb.deleteCharAt(i);
                return removeTrivialCases(sb.toString());
            }
        }

        return expr;
    }

    public static int[] balancedOrNot(String[] expressions, int[] maxReplacements) {
        // Checks for invalid data
        if (expressions == null || expressions.length == 0 || maxReplacements == null || maxReplacements.length != expressions.length) {
            return new int[]{0};
        }

        int[] results = new int[expressions.length];
        for (int i=0; i<expressions.length; i++) {
            String expr = expressions[i];

            // Checks for empty expression
            if (expr == null || expr.length() == 0) {
                results[i] = 0;
            }

            int changesRequired = 0;
            String expr2 = removeTrivialCases(expr);

            if (expr2 == null || expr2.length() == 0) {
                changesRequired = 0;
            }
            else {
                changesRequired = expr2.length();
            }

            CommonUtils.log(String.format(Locale.getDefault(), "Changes required: %s %d", expr, changesRequired));

            results[i] = Math.abs(changesRequired) <= maxReplacements[i] ? 1 : 0;
        }

        return results;
    }

    public static boolean isAlmostPalindrome(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }

        String reversed = StringUtils.reverse(source);
        char[] sourceChars = source.toCharArray();
        char[] reversedChars = reversed.toCharArray();
        int diffs = 0;

        for (int i=0; i<sourceChars.length; i++) {
            char c1 = sourceChars[i];
            char c2 = reversedChars[i];

            if (c1 != c2) {
                diffs++;
                if (diffs > 2) {
                    return false;
                }
            }
        }

        return true;
    }

}
