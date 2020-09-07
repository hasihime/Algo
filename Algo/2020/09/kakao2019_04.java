
public class kakao2019_04 {
	public static void main(String[] args) {
		int[] food_times = { 3, 1, 3 };
		int k = 6;
		System.out.println(solution(food_times, k));

	}

	public static int solution(int[] food_times, long k) {
		int answer = 0;
		int size = food_times.length;
		int totalcnt = 0;
		for (int i = 0; i < food_times.length; i++) {
			totalcnt += food_times[i];
		}
		if (totalcnt <= k) {
			return -1;
		}
		int skipcnt=0;
		for (int i = 0; i <= k; i++) {
			int curidx = i+skipcnt;
			while (true) {

				if (food_times[curidx % size] == 0) {
					curidx++;
					skipcnt++;
				} else {
					food_times[curidx % size]--;
					answer = curidx % size;
					System.out.println(answer+1);
					break;
				}
			}
		}
		return answer + 1;
	}

	static class Food {
		int no;
		int cnt;

		public Food(int no, int cnt) {
			super();
			this.no = no;
			this.cnt = cnt;
		}

	}
}
