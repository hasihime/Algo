import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_D4_4261_빠른휴대전화키패드_오석빈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> alphabet = new HashMap<Character, Integer>();

		for (char i = 'a'; i <= 'c'; i++) {
			alphabet.put(i, 2);
		}
		for (char i = 'd'; i <= 'f'; i++) {
			alphabet.put(i, 3);
		}

		for (char i = 'g'; i <= 'i'; i++) {
			alphabet.put(i, 4);
		}

		for (char i = 'j'; i <= 'l'; i++) {
			alphabet.put(i, 5);
		}

		for (char i = 'm'; i <= 'o'; i++) {
			alphabet.put(i, 6);
		}

		for (char i = 'p'; i <= 's'; i++) {
			alphabet.put(i, 7);
		}

		for (char i = 't'; i <= 'v'; i++) {
			alphabet.put(i, 8);
		}

		for (char i = 'w'; i <= 'z'; i++) {
			alphabet.put(i, 9);
		}
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine(), " ");
			String input = st.nextToken();
			int n = Integer.parseInt(st.nextToken());
			int result = 0;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				String voca = st.nextToken();

				boolean flag = true;
				for (int j = 0; j < voca.length(); j++) {
					if (alphabet.get(voca.charAt(j)) != (input.charAt(j) - '0')) {
						flag = false;
						break;
					}
				}
				if (flag)
					result++;
			}
			sb.append(result);
			sb.append("\n");

		} // T 끝
		System.out.println(sb);
	}// 메인 끝

}
