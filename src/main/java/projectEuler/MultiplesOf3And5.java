package projectEuler;

public class MultiplesOf3And5 {
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i < 1000; i++) {
			if (checkNum(i)) {
				sum += i;
			}
		}
		System.out.println(sum);
	}

	private static boolean checkNum(int i) {
		if (i % 3 == 0) {
			return true;
		} else {
			return i % 5 == 0;
		}
	}
}
