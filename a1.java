package org.example.q1;

import java.util.*;

public class Solution {

    public static int kThSmallest(int[] arr1, int[] arr2, int[] arr3, int k) {
        int low = Math.max(0,k-(2 * arr1.length)), high = Math.min(k,arr1.length);

        while (low <= high) {
            int cut1 = low + (high - low) / 2;
            int cut2 = bSearch(arr2, arr3, k - cut1);

            int cut3 = k - cut1 - cut2;

            int l1 = cut1 <= 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = cut2 <= 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int l3 = cut3 <= 0 ? Integer.MIN_VALUE : arr3[cut3 - 1];

            int r1 = cut1 >= arr1.length ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = cut2 >= arr2.length ? Integer.MAX_VALUE : arr2[cut2];
            int r3 = cut3 >= arr3.length ? Integer.MAX_VALUE : arr3[cut3];

            if (l1 <= r2 && l2 <= r1 && l2 <= r3 && l3 <= r2 && l1 <= r3 && l3 <= r1) {
                return Math.max(Math.max(l1, l2), l3);
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return -1;
    }

    private static int bSearch(int[] arr2, int[] arr3, int target) {
        int low = Math.max(0,target - arr2.length), high = Math.min(target,arr2.length);

        while (low < high) {
            int cut2 = low + (high - low) / 2;
            int cut3 = target - cut2;

            int l2 = cut2 <= 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int l3 = cut3 <= 0 ? Integer.MIN_VALUE : arr3[cut3 - 1];

            int r2 = cut2 >= arr2.length ? Integer.MAX_VALUE : arr2[cut2];
            int r3 = cut3 >= arr3.length ? Integer.MAX_VALUE : arr3[cut3];

            if (l2 <= r3 && l3 <= r2) {
                return cut2;
            }
            else if(l2 > r3){
                high = cut2 - 1;
            }
            else{
                low = cut2 + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 5, 7, 9};
        int[] arr2 = new int[]{2, 4, 6, 10, 14};
        int[] arr3 = new int[]{4, 7, 11, 13, 17};
        int k = 1;
        System.out.println(kThSmallest(arr1, arr2, arr3, k));
    }
}
