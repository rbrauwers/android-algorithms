package com.androidalgorithms.rbrauwers.android_algorithms;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Scanner;

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

    // Rotation left
    //https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int rotationsCount = in.nextInt();
        int a[] = new int[n];
        for(int i=0; i < n; i++){
            a[i] = in.nextInt();
        }

        for (int i=0; i<rotationsCount; i++) {
            int tmp = a[0];
            for (int j=0; j<n-1; j++) {
                a[j] = a[j+1];
            }
            a[n-1] = tmp;
        }

        StringBuilder sb = new StringBuilder(String.valueOf(a[0]));

        if (n > 1) {
            for (int i=1; i<n; i++) {
                sb.append(" ");
                sb.append(String.valueOf(a[i]));
            }
        }

        System.out.println(sb.toString());
    }

    // Is binary tree
    // https://www.hackerrank.com/challenges/ctci-is-binary-search-tree/problem
    static boolean checkBST(Node root) {
        if (root == null) {
            return false;
        }

        if (root.left != null) {
            if (!validateIsLessThanRootValue(root.left, root)) {
                return false;
            }
        }

        if (root.right != null) {
            if (!validateIsGreaterThanRootValue(root.right, root)) {
                return false;
            }
        }

        return true;
    }

    static boolean validateIsLessThanRootValue(Node node, Node root) {
        //CommonUtils.log(String.format(Locale.getDefault(), "ValidateIsLessThanRootValue: %d %d", root.data, node.data));

        if (node != root && node.data >= root.data) {
            return false;
        }

        if (node.left != null) {
            if (!validateIsLessThanRootValue(node.left, root)) {
                return false;
            }
            if (!validateIsLessThanRootValue(node.left, node)) {
                return false;
            }
        }

        if (node.right != null) {
            if (!validateIsLessThanRootValue(node.right, root)) {
                return false;
            }
            if (!validateIsGreaterThanRootValue(node.right, node)) {
                return false;
            }
        }

        return true;
    }

    static boolean validateIsGreaterThanRootValue(Node node, Node root) {
        //CommonUtils.log(String.format(Locale.getDefault(), "validateIsGreaterThanRootValue: %d %d", root.data, node.data));

        if (node != root && node.data <= root.data) {
            return false;
        }

        if (node.left != null) {
            if (!validateIsGreaterThanRootValue(node.left, root)) {
                return false;
            }
            if (!validateIsLessThanRootValue(node.left, node)) {
                return false;
            }
        }

        if (node.right != null) {
            if (!validateIsGreaterThanRootValue(node.right, root)) {
                return false;
            }
            if (!validateIsGreaterThanRootValue(node.right, node)) {
                return false;
            }
        }

        return true;
    }


    // Bubble sort
    // https://www.hackerrank.com/challenges/ctci-bubble-sort/problem
    public static void main2(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        int count = 0;

        while (s.hasNextInt()) {
            a[count] = s.nextInt();
            count++;
        }

        // Track number of elements swapped during a single array traversal
        int numberOfSwaps = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    numberOfSwaps++;
                }
            }

            // If no elements were swapped during a traversal, array is sorted
            if (numberOfSwaps == 0) {
                break;
            }
        }

        System.out.println(String.format("Array is sorted in %d swaps.", numberOfSwaps));
        System.out.println(String.format("First Element: %d", a[0]));
        System.out.println(String.format("Last Element: %d", a[n-1]));
    }
}
