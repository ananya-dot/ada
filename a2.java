package org.example;

import java.util.*;

import static java.util.Collections.max;

public class Ada{
    public static void main(String[] args) {
        int[] arr1 = new int[]{ -1, -3, 4};
        int[] arr2 = new int[]{-100, 2, 1, -3, -2, -3, -1, -4, -2, 1, 6, 3, 4, -5};
        int[] arr3 = new int[]{1, 2, 3, 4, -1, -2, -3, -4};
        int[] arr4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, -1, -2, -3, -4, -5, -6, -7, -8};

        System.out.println(answer(arr1));
        System.out.println(answer(arr2));
        System.out.println(answer(arr3));
        System.out.println(answer(arr4));
    }


    private static int answer(int[] arr) {
            if(arr.length <= 3){
                return sum(arr);
            }
            int[] temp = new int[6];
            int[] curr = new int[6];
            Arrays.fill(temp, Integer.MIN_VALUE);
            temp[0] = arr[0];
            temp[3] = -1 * arr[0];
            curr = Arrays.copyOf(temp,6);

//            System.out.println(Arrays.toString(curr));

            temp[0] = curr[3] + arr[1];
            temp[1] = curr[0] + arr[1];
            temp[3] = curr[0] - arr[1];
            temp[4] = curr[3] - arr[1];
            curr = Arrays.copyOf(temp,6);

//            System.out.println(Arrays.toString(curr));
            temp[0] = Math.max(curr[3], curr[4]) + arr[2];
            temp[1] = curr[0] + arr[2];
            temp[2] = curr[1] + arr[2];
            temp[3] = Math.max(curr[0], curr[1]) - arr[2];
            temp[4] = curr[3] - arr[2];
            temp[5] = curr[4] - arr[2];

            curr = Arrays.copyOf(temp,6);

//            System.out.println(Arrays.toString(curr));

            // 1,2,4,5 are 0

            for(int i = 3; i < arr.length; i ++){
                temp[0] = Math.max(curr[3], Math.max(curr[4], curr[5])) + arr[i];
                temp[1] = curr[0] + arr[i];
                temp[2] = curr[1] + arr[i];
                temp[3] = Math.max(curr[0], Math.max(curr[1], curr[2])) - arr[i];
                temp[4] = curr[3] - arr[i];
                temp[5] = curr[4] - arr[i];
//                System.out.println(Arrays.toString(temp));
                curr = Arrays.copyOf(temp,6);
            }

            return (max(curr));
        }

    private static int sum(int[] arr) {
        int ans = 0;
        for(int i = 0; i < arr.length; i++){
            ans += Math.abs(arr[i]);
        }
        return ans;
    }


    public static int max(int[] temp){
        int ans = 0;

        for(int i = 0; i < 6; i ++){
            ans = Math.max(ans, temp[i]);
        }
        return ans;
    }
}
