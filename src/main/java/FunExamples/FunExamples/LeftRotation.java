package FunExamples.FunExamples;

import java.util.Scanner;

public class LeftRotation {

	public static int[] arrayLeftRotation(int[] a, int n, int k) {
		int[] array = a;
		// k = shift num
		// n = num of elements
		for (int i = 0; i < k; i++) {
			int temp = array[0];
			for (int j = 0; j < n - 1; j++) {
				array[j] = array[j + 1];
			}
			array[n - 1] = temp;
		}

		return array;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int a[] = new int[n];

		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		int[] output = new int[n];
		output = arrayLeftRotation(a, n, k);
		for (int i = 0; i < n; i++)
			System.out.print(output[i] + " ");

		System.out.println();

	}
}
