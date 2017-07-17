package FunExamples.FunExamples;

import java.util.HashMap;
import java.util.Scanner;

public class MainTriesContacts {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		TriesContacts t = new TriesContacts();

		int n = in.nextInt();
		for (int a0 = 0; a0 < n; a0++) {
			String op = in.next();
			String contact = in.next();
			switch (op) {
			case "add": {
				t.add(contact);
				break;
			}
			case "find": {
				System.out.println(t.find(contact));
				break;
			}
			}
		}
	}
}

class TriesContacts {

	//found this on github
	HashMap<Character, TriesContacts> contacts = new HashMap<Character, TriesContacts>();
	int count;

	public void add(String name) {
		count++;
		if (!name.isEmpty()) {
			if (!contacts.containsKey(name.charAt(0))) {
				contacts.put(name.charAt(0), new TriesContacts());
			}
			contacts.get(name.charAt(0)).add(name.substring(1));
		}
	}

	public int find(String partial) {
		if (partial.isEmpty()) {
			return count;
		}
		if (contacts.containsKey(partial.charAt(0))) {
			return contacts.get(partial.charAt(0)).find(partial.substring(1));
		}
		return 0;
	}
}
