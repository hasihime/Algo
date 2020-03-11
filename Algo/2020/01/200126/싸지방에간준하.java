import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 싸지방에간준하 {
	static class person {
		int starttime;
		int endtime;
		int seatnum;

		public void setSeatnum(int seatnum) {
			this.seatnum = seatnum;
		}

		public person(int starttime, int endtime) {
			super();
			this.starttime = starttime;
			this.endtime = endtime;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<person> pq = new PriorityQueue<person>(new Comparator<person>() {
			@Override
			public int compare(person o1, person o2) {
				int ea = o1.endtime;
				int eb = o2.endtime;
				return ea - eb;
			}
		});

		int N = Integer.parseInt(br.readLine());
		int[][] parr = new int[N][2];
		int[] seat = new int[100001];
		boolean[] isseat = new boolean[100001];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			parr[i][0] = Integer.parseInt(st.nextToken());
			parr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(parr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int seatcnt = 1;
		for (int i = 0; i < N; i++) {
			person p = new person(parr[i][0], parr[i][1]);
			if (!pq.isEmpty()) {
				while (true) {
					int x = pq.peek().endtime;
					// 지금 들어온 시간보다 큐에 있는 놈이 시간이 작으면 나와야지. 시작시간 종료시간 겹치는 놈 없으니 =은 배제
					if (p.starttime > x) {
						person t = pq.poll();
						isseat[t.seatnum] = false;
					} else {
						break;
					}
				}
			}
			if (!pq.isEmpty()) {
				boolean neednewseat = true;
				// 지금 들어온 놈의 시작시간이 위에 있는 애 끝나는 시간보다 크면 뭐 다른자리 가야지
				for (int j = 1; j <= seatcnt; j++) {
					if (!isseat[j]) {
						isseat[j] = true;
						seat[j]++;
						p.setSeatnum(j);
						neednewseat = false;
						break;
					}
				}
				if (neednewseat) {
					seatcnt++;
					seat[seatcnt]++;
					isseat[seatcnt] = true;
					p.setSeatnum(seatcnt);
				}
				pq.add(p);
			} else {
				p.setSeatnum(1);
				seat[1]++;
				isseat[1] = true;
				pq.add(p);
			}
		}
		System.out.println(N == 0 ? 0 : seatcnt);
		for (int i = 1; i <= seatcnt; i++) {
			System.out.print(seat[i] + " ");
		}
	}// 메인 끝

}
