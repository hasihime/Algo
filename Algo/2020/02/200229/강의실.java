import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1374
public class 강의실 {
	private static int n;
	private static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		go();
	}

	private static void go() {
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		int ms=1;
		pq.add(arr[0]);
		for (int i = 1; i < n; i++) {
			//다음 강의 시작시간보다 현재 큐 peek해서 종료시간이 더 작으면 뺀다.
			while(!pq.isEmpty()) {
				if (pq.peek()[2]<=arr[i][1]) {
					pq.poll();
				}else {
					break;
				}
			}//peek 종료
			
			pq.add(arr[i]);
			ms=Math.max(ms, pq.size());
		}
		System.out.println(ms);
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		arr=new int[n][3];
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
			arr[i][2]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1]==o2[1]) {
					return o1[2]-o2[2];
				}
				return o1[1]-o2[1];
				
			}
		});
	}
}
