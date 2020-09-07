import java.util.LinkedList;
import java.util.Stack;

public class kakao2020_01 {
	public static void main(String[] args) {

		String[] s = {"a","aabbaccc","ababcdcdababcdcd","abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd" };
		for (int i = 0; i < s.length; i++) {
			System.out.println(solution(s[i]));
		}

	}

	public static int solution(String s) {
		// "aabbaccc"
		char[] arr = s.toCharArray();
		int answer = 100000;
		int size = s.length();

		for (int i = 1; i <= size / 2; i++) {
			String namu="";

			// 그러면 나눠라
			LinkedList<char[]> clist = new LinkedList<>();
			LinkedList<Integer> cntlist = new LinkedList<>();

			for (int j = 0; j < arr.length;) {
				if (j + i > arr.length) {
					namu=s.substring(j,arr.length);
					break;
				}
				char[] zipletter = getcuridx(arr, i, j);
				int idx = j;
				int cnt = 0;
				while (idx < arr.length) {
					
					if (check(i, idx, arr, zipletter)) {
						cnt++;
					} else {
						clist.add(zipletter);
						cntlist.add(cnt);
						break;
					}
					idx += i;
					if (idx >= arr.length) {
						clist.add(zipletter);
						cntlist.add(cnt);
						break;
					}
				}
				j += cnt * i;

			} // 압축 끝

			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < clist.size(); j++) {
				sb.append(clist.get(j));
				if (cntlist.get(j) != 1) {
					sb.append(cntlist.get(j));
				}
			}
			sb.append(namu);
//			System.out.println(sb);
			answer = answer > sb.length() ? sb.length() : answer;

		}

		return answer;
	}

	private static boolean check(int size, int idx, char[] arr, char[] zipletter) {
		for (int i = 0; i < size; i++) {
			if(idx + i>=arr.length) return false;
			if (arr[idx + i] != zipletter[i]) {
				return false;
			}
		}
		return true;

	}

	private static char[] getcuridx(char[] arr, int size, int idx) {

		char[] rtchar = new char[size];
		for (int k = 0; k < size; k++) {
			rtchar[k] = arr[idx + k];
		}
		return rtchar;
	}

}
