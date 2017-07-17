package FunExamples.FunExamples;

import java.util.HashSet;

public class CheckIfBST {

	static HashSet<Integer> set = new HashSet<Integer>();
	static int MAX = 0;
	static int MIN = 0;

	public static void main(String[] args) {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		Node d = new Node(4);
		Node e = new Node(5);
		Node f = new Node(6);
		Node g = new Node(7);

		d.setLeft(b);
		b.setLeft(a);
		b.setRight(c);

		d.setRight(f);
		f.setLeft(e);
		f.setRight(g);

		boolean bst = checkBST(d);
		if (bst) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}

	static boolean checkBST(Node root) {
		int min = getMin(root);
		int max = getMax(root);
		return checkBSTRecursive(root, min, max);
	}

	static boolean checkBSTRecursive(Node root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.data < min || root.data > max) {
			return false;
		}
		if (root.data == min && root.left != null) {
			return false;
		}
		if (root.data == max && root.right != null) {
			return false;
		}

		if (checkBSTRecursive(root.left, min, root.data)) {
			return checkBSTRecursive(root.right, root.data, max);
		} else {
			return false;
		}
	}

	static int getMin(Node root) {
		int min = root.data;
		Node root2 = root;
		while (root2.left != null) {
			if (root2.left.data < min) {
				min = root2.left.data;
			}
			root2 = root2.left;
		}
		while (root.right != null) {
			if (root.right.data < min) {
				min = root.right.data;
			}
			root = root.right;
		}
		return min;
	}

	static int getMax(Node root) {
		int max = root.data;
		Node root2 = root;
		while (root2.right != null) {
			if (root2.right.data > max) {
				max = root2.right.data;
			}
			root2 = root2.right;
		}
		while (root.left != null) {
			if (root.left.data > max) {
				max = root.left.data;
			}
			root = root.left;
		}
		return max;
	}
}

class Node {
	Integer data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}