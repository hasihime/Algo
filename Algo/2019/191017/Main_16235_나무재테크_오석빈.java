import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16235_나무재테크_오석빈 {

	public static void main(String[] args) throws IOException {
//		long starttime = System.nanoTime();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int stree = Integer.parseInt(st.nextToken());
		int targetyear = Integer.parseInt(st.nextToken());
		int rdir[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int cdir[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

		// 초기 환경 설정
		int[][] map = new int[n][n];

		List<Integer> yearlist[][] = new ArrayList[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				yearlist[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 0; i < n; i++) {
			Arrays.fill(map[i], 5);
		}

		int amap[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				amap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < stree; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int srow = Integer.parseInt(st.nextToken()) - 1;
			int scol = Integer.parseInt(st.nextToken()) - 1;
			int syear = Integer.parseInt(st.nextToken());
			yearlist[srow][scol].add(syear);
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				Collections.sort(yearlist[i][j]);
//			}
//		}

		// 연수반복
		for (int i = 0; i < targetyear; i++) {

			int accumap[][] = new int[n][n];
			// 봄
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {

					int curtreecnt = yearlist[j][k].size();
					for (int l = 0; l < curtreecnt; l++) {
						int needa = yearlist[j][k].get(l);
						if (map[j][k] - needa < 0) {
							accumap[j][k] += (yearlist[j][k].get(l) / 2);
							yearlist[j][k].remove(l);
							curtreecnt--;
							l--;
							
							
						} else {
							yearlist[j][k].set(l, needa + 1);
							map[j][k] -= needa;
						}
					}
				}
			}

			// 가을
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {

					int curtreecnt = yearlist[j][k].size();
					for (int l = 0; l < curtreecnt; l++) {
						if (yearlist[j][k].get(l) % 5 == 0) {
							for (int m = 0; m < 8; m++) {
								int newr = j + rdir[m];
								int newc = k + cdir[m];
								if (newr > -1 && newr < n && newc > -1 && newc < n) {
									yearlist[newr][newc].add(0, 1);
								}
							}
						}
					}
				}
			}

			// 겨울
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					map[j][k] += amap[j][k] + accumap[j][k];
				}
			}
		}

		int treecnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				treecnt += yearlist[i][j].size();
			}
		}

//		long endtime = System.nanoTime();
		System.out.println(treecnt);
	}

}
