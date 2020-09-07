import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class kakao2020_02 {
	public static void main(String[] args) {

		String[] s = {"(()())()",")(","()))((()"};
		for (int i = 0; i < s.length; i++) {
			System.out.println(solution(s[i]));
		}

	}

	static String answer = "";

	public static String solution(String p) {
		char[] carr = p.toCharArray();

		return go(carr);
	}

	private static String go(char[] input) {
		if (input.length == 0) {
			return "";
		}
		String result = "";
		char[] u = getu(input);
		char[] v = Arrays.copyOfRange(input, u.length, input.length);

		if (isCorrect(u)) {
			return CtoString(u) + go(v);
		} else {
			String change = "(";
			change += go(v);
			change += ")";
			char[] newu = new char[u.length - 2];
			for (int i = 1; i < u.length - 1; i++) {
				if (u[i] == '(') {
					newu[i - 1] = ')';
				} else {
					newu[i - 1] = '(';
				}
			}
			return change += CtoString(newu);
		}
	}

	private static boolean isCorrect(char[] u) {
		int ocnt = 0;
		int ccnt = 0;
		for (int i = 0; i < u.length; i++) {
			if (u[i] == '(') {
				ocnt++;
			}else {
				ccnt++;
			}
			if (ccnt>ocnt) {
				return false;
			}
		}

		return true;
	}

	private static char[] getu(char[] input) {
		int ocnt = 0;
		int ccnt = 0;

		for (int i = 0; i < input.length; i++) {
			if (input[i] == '(') {
				ocnt++;
			} else {
				ccnt++;
			}
			if (ocnt != 0 && ocnt == ccnt) {
				char[] uarr = Arrays.copyOf(input, i + 1);
				return uarr;
			}
		}
		return null;
	}

	private static String CtoString(char[] input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length; i++) {
			sb.append(Character.toString(input[i]));
		}
		return sb.toString();
	}

}
