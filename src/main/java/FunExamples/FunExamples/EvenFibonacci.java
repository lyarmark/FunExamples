package FunExamples.FunExamples;

public class EvenFibonacci {
	public static void main(String args[]) {
		int sum = 0;
		int lastI = 1;
		for (int i = 2; i < 4000000; i += lastI) {
			if (i % 2 == 0) {
				System.out.println(i);
				sum += i;
			}
			if (lastI % 2 == 0) {
				System.out.println(lastI);
				sum += lastI;
			}
			lastI = i + lastI;
		}
		System.out.println("SUM: " + sum);
	}
}
