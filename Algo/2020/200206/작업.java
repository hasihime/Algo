import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 작업 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] time = new int[n];
		ArrayList<Integer> job[] = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			job[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			for (int j = 0; j < idx; j++) {
				job[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		int totaltime = time[0];
		for (int i = 1; i < n; i++) {
			int curmaxt = 0;
			int size = job[i].size();
			for (int j = 0; j < size; j++) {
				curmaxt = Math.max(curmaxt, time[job[i].get(j)-1]);
			}
			time[i] += curmaxt;
			totaltime = Math.max(totaltime, time[i]);
		}
		System.out.println(totaltime);

	}

}
