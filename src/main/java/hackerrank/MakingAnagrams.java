package hackerrank;

import java.util.HashMap;
import java.util.HashSet;

public class MakingAnagrams {

	private static int makeAnagrams(String a, String b) {
		HashMap<Character, Integer> mapA = new HashMap<Character, Integer>();
		HashMap<Character, Integer> mapB = new HashMap<Character, Integer>();
		HashSet<Character> setRemove = new HashSet<Character>();

		int num = 0;

		putStringsInMaps(a, b, mapA, mapB);

		// if the maps have the same keyset/mappings
		if (mapA.equals(mapB)) {
			return 0;
		}

		for (Character c : mapA.keySet()) {
			if (!mapB.containsKey(c)) {
				num += mapA.get(c);
			} else {
				num += Math.abs(mapA.get(c) - mapB.get(c));
				setRemove.add(c);
			}
		}
		for (Character c : mapB.keySet()) {
			if (!mapA.containsKey(c)) {
				num += mapB.get(c);
			} else if (!setRemove.contains(c)) {
				num += Math.abs(mapA.get(c) - mapB.get(c));
			}
		}
		return num;
	}

	private static void putStringsInMaps(String a, String b, HashMap<Character, Integer> mapA,
			HashMap<Character, Integer> mapB) {
		for (int i = 0; i < a.length(); i++) {
			Integer count = mapA.get(a.charAt(i));
			if (count == null) {
				mapA.put(a.charAt(i), 1);
			} else {
				mapA.put(a.charAt(i), count + 1);
			}
		}

		for (int i = 0; i < b.length(); i++) {
			Integer count = mapB.get(b.charAt(i));
			if (count == null) {
				mapB.put(b.charAt(i), 1);
			} else {
				mapB.put(b.charAt(i), count + 1);
			}
		}
	}

	public static void main(String[] args) {

		int i = makeAnagrams("leahleahy", "leah");
		// aaeellhh y
		// e llh o
		System.out.println(i);
	}
}
