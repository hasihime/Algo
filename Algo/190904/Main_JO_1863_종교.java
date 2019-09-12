package hw_190904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;


public class Main_종교2 {
	private static int parents[];

	static int getParent(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = getParent(parents[a]);
	}

	static void union(int a, int b) {
		int aRoot = getParent(a);
		int bRoot = getParent(b);
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot;
		} else
			parents[aRoot] = bRoot;
	}

	static boolean find(int a, int b) {
		int aRoot = getParent(a);
		int bRoot = getParent(b);
		if (aRoot == bRoot)
			return false;
		else
			return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
		
		int line = Integer.parseInt(st.nextToken());
		
		int arr[][] = new int[line][2];
		if (line == 0) {
			System.out.println(N);
			return;
		} else if (line != 0) {
			for (int i = 0; i < line; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (a < b) {
					arr[i][0] = a;
					arr[i][1] = b;
				} else {
					arr[i][0] = b;
					arr[i][1] = a;
				}
			}
			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					int a = o1[0];
					int b = o2[0];
					return a - b;
				}
			});
			for (int i = 0; i < line; i++) {
				if (!find(arr[i][0], arr[i][1])) {
					continue;
				}else {
					union(arr[i][0], arr[i][1]);
				}
			}
		}
		
		Arrays.sort(parents);
		HashSet<Integer> hs=new HashSet<Integer>();
		
		for (int i = 1; i < N+1; i++) {
			hs.add(parents[i]);
		}
		
		System.out.println(hs.size());
	}
}