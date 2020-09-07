
public class kakao2020_04 {
	public static void main(String[] args) {
		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String[] queires = {"fro??", "????o", "fr???",  "fro???", "pro?" };
		System.out.println(solution(words, queires).toString());
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		char[][] chcarqueries = new char[queries.length][];

		for (int i = 0; i < queries.length; i++) {
			chcarqueries[i] = queries[i].toCharArray();
		}

		for (int i = 0; i < words.length; i++) {
			char[] wordchar = words[i].toCharArray();
			int cnt = 0;

			for (int j = 0; j < chcarqueries.length; j++) {
				if (chcarqueries[j].length != wordchar.length) {
					continue;
				}
				boolean flag = true;
				for (int k = 0; k < wordchar.length; k++) {
					if (chcarqueries[j][k] == '?' || 
							wordchar[k] == chcarqueries[j][k]) {

					} else {
						flag = false;
						break;
					}
				}
				if (flag) {
					answer[j]++;
				}
			}
			
		}
		return answer;
	}
}
