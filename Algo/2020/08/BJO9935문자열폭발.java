import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJO9935문자열폭발 {

	private static char[] arr;
	private static char[] bomb;

	public static void main(String[] args) throws IOException {
		input();
		go();
	}

	private static void go() {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < arr.length; i++) {
			stack.add(arr[i]);
			if (stack.size() >= bomb.length) {
				boolean flag = true;
				for (int j = 0; j < bomb.length; j++) {
					if (bomb[j] != stack.get(stack.size() - bomb.length + j)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					for (int j = 0; j < bomb.length; j++) {
						stack.pop();
					}
				}
			}
		} // for 끝

		StringBuilder sb = new StringBuilder();
		int size=stack.size();
		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			for (int i = 0; i < size; i++) {
				sb.append(Character.toString(stack.pop()));
			}
			sb.reverse();
			System.out.println(sb);
		}

	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = br.readLine().toCharArray();
		bomb = br.readLine().toCharArray();

	}

}
