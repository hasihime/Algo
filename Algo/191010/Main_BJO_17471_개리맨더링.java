import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 *  테케는 맞는데 아래 반례 해결이 안됨.
6
2 2 2 2 2 2
1 3
1 4
1 1
1 2
1 6
1 5
 */

public class Main_BJO_17471_개리맨더링 {
	static int N = 0;
	static int person[];
	static boolean map[][];
	static int combination[];
	static boolean[] visited;
	static int mingap = 10000000;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		person = new int[N + 1];
		combination = new int[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i < N + 1; i++) {
			person[i] = sc.nextInt();
			combination[i] = i;
		}
		map = new boolean[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			int tempn = sc.nextInt();
			for (int j = 0; j < tempn; j++) {
				int x = sc.nextInt();
				map[i][x] = true;
			}
		}

		permutaion(1);
		if (mingap == 10000000) {
			System.out.println("-1");
		} else
			System.out.println(mingap);
	}

	private static void permutaion(int cur) {
		if (cur == N + 1) {
			int count = 0;
			// 구역 나누기용
			List<Integer> area1 = new LinkedList<Integer>();
			List<Integer> area2 = new LinkedList<Integer>();
			for (int i = 1; i < N + 1; i++) {
				if (visited[i]) {
					count++;
					area1.add(combination[i]);
				} else {
					area2.add(combination[i]);
				}
			}

			// 마을이 전체 선택되거나 0개면 바로 리턴
			if (count == 0 || count == N)
				return;

			// 인접 행렬로 처리 방법...?
			int area1size = area1.size();
			int area1person = 0;
			if (area1size != 1) {
				boolean temp[] = new boolean[N + 1];
				for (int i = 0; i < area1size; i++) {
					int t = area1.get(i);
					for (int j = 0; j < N + 1; j++) {
						if (map[t][j]) {
							temp[j] = true;
						}
					}
					area1person += person[t];
				}

				for (int i = 0; i < area1size; i++) {
					int y = area1.get(i);
					if (!temp[y]) {
						return;
					}
				}

			} else {
				area1person += area1.get(0);
			}

			int area2size = area2.size();
			int area2person = 0;
			if (area2size != 1) {
				boolean temp[] = new boolean[N + 1];
				for (int i = 0; i < area2size; i++) {
					int t = area2.get(i);
					for (int j = 0; j < N + 1; j++) {
						if (map[t][j]) {
							temp[j] = true;
						}
					}
					area2person += person[t];
				}
				for (int i = 0; i < area2size; i++) {
					int y = area2.get(i);
					if (!temp[y]) {
						return;
					}
				}

			} else {
				area2person += area2.get(0);
			}

			int gap = Math.abs(area1person - area2person);

			if (gap < mingap) {
				mingap = gap;
			}
			return;
		}

		visited[cur] = false;
		permutaion(cur + 1);
		visited[cur] = true;
		permutaion(cur + 1);

	}

}
