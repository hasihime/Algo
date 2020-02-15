import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 가르침 {

	private static int k;
	private static int size;
	private static int[] comarr;
	private static ArrayList<Character> inputList;
	private static HashMap<Character, Integer> alaphamap;
	private static int n;
	private static int result;
	private static char[][] testarr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()) - 5;
		if (k < 0) {
			System.out.println(0);
			return;
		}
		alaphamap = new HashMap<Character, Integer>();
		boolean[] checked = new boolean[26];
		alaphamap.put('a', 1);
		alaphamap.put('n', 1);
		alaphamap.put('t', 1);
		alaphamap.put('i', 1);
		alaphamap.put('c', 1);
		checked['a' - 'a'] = true;
		checked['n' - 'a'] = true;
		checked['t' - 'a'] = true;
		checked['i' - 'a'] = true;
		checked['c' - 'a'] = true;
		inputList = new ArrayList<Character>();
		testarr = new char[n][8];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			input = input.substring(4);
			input = input.substring(0, input.length() - 4);
			testarr[i] = input.toCharArray();
			for (int j = 0; j < input.length(); j++) {
				if (!checked[input.charAt(j) - 'a']) {
					inputList.add(input.charAt(j));
					checked[input.charAt(j) - 'a'] = true;
				}
			}
		}
		size = inputList.size();
		comarr = new int[k];
		result = 0;
		// 현재 배워야 할 알파벳이 배울수 있는 단어보다 적으면 모든 단어를 다 배울수 있다.
		if (size < k) {
			result = n;
		} else {
			makecom(0, 0);
		}
		System.out.println(result);
	}

	private static void makecom(int nownum, int target) {
		if (target == k) {
			test();
			return;
		}
		for (int i = nownum; i < size; i++) {
			comarr[target] = i;
			makecom(i + 1, target + 1);
		}

	}

	private static void test() {
		for (int i = 0; i < comarr.length; i++) {
			char curc = inputList.get(comarr[i]);
			alaphamap.put(curc, 1);
		}
		int cur = 0;
		for (int i = 0; i < n; i++) {
			int idx = 0;
			boolean flag = true;
			while (idx < testarr[i].length) {
				if (!alaphamap.containsKey(testarr[i][idx])) {
					flag = false;
					break;
				}
				idx++;
			}
			if (flag)
				cur++;
		}
		result = result < cur ? cur : result;
		for (int i = 0; i < comarr.length; i++) {
			char curc = inputList.get(comarr[i]);
			alaphamap.remove(curc);
		}
	}

}
