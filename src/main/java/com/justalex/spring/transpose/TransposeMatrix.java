package com.justalex.spring.transpose;

import org.springframework.util.Assert;

import java.util.Arrays;

public class TransposeMatrix {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        int[][] expArr = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}};
        System.out.println("Initial array");
        printArray(arr);

        int[][] resultArr = transpose(arr);

        System.out.println("Result array");
        printArray(resultArr);

        Assert.isTrue(Arrays.deepEquals(resultArr, expArr));
        System.out.println("Done");
    }

    private static void test() {
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println(i + ", " + j);
            }
        }
    }

    private static int[][] transpose(int[][] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
                System.out.println(i + ", " + j);
            }
        }
        return arr;
    }

    private static void printArray(int[][] arr) {
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr[1].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
