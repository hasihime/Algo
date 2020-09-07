import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class kakao2019_02 {
	public static void main(String[] args) {
		int[] N = { 5, 4 };
		int[][] stages = { { 2, 1, 2, 6, 2, 4, 3, 3 }, { 4, 4, 4, 4, 4 } };
		for (int i = 0; i < stages.length; i++) {

			System.out.println(Arrays.toString(solution(N[i], stages[i])));
		}
	}

	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		PriorityQueue<Falerate> pq = new PriorityQueue<Falerate>(new Comparator<Falerate>() {
			
			@Override
			
			public int compare(Falerate o1, Falerate o2) {
				// TODO Auto-generated method stub
				if (o2.rate == o1.rate) {
					return o1.stage - o2.stage;
				}
				
				return Double.compare(o2.rate, o1.rate);
			}
		});
		Arrays.sort(stages);
		int peoplecnt = stages.length;
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			if (peoplecnt == 0) {
				for (int j = i; j <= N; j++) {
					pq.add(new Falerate(j, 0));
				}
				break;
			}
			int curstage = i;
			int falecnt = 0;
			// 현재 스테이지에서 막혀있으면 일단 그 수를 센다.
			for (int j = idx; j < stages.length; j++) {
				if (curstage >= stages[j]) {
					falecnt++;
					idx++;
					continue;
				}
				// 다른 경우 실패자/현재 사람 구하고 answer기입
				else {
					break;
				}
			}
			pq.add(new Falerate(i, (double)falecnt / (double)peoplecnt));
			peoplecnt -= falecnt;
		}
		for (int i = 0; i < answer.length; i++) {
			answer[i] = pq.poll().stage;
		}
		return answer;
	}

	static class Falerate {
		int stage;
		double rate;

		public Falerate(int stage, double rate) {
			super();
			this.stage = stage;
			this.rate = rate;
		}

	}
}
