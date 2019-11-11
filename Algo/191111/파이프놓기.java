import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프놓기 {
	static int n = 0;
	static int map[][];
	
	static int garodir[] =  { 0, 1 }; // 2
	static int serodir[] = { 1, 0 }; // 3
	static int degakdir[][] =  { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 4

	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		result = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[0][0] = 2;
		map[0][1] = 2;
		dfs(0, 1, 2);
		
		System.out.println(result);
	}// 메인 끝

	private static void dfs(int row, int col, int dir) {
		if (row == n-1 && col == n-1) {
			result++;
			return;
		}

		if (dir == 2) {// 마지막 파이프가 가로
			int newr = row ;
			int newc = col + garodir[1];
			if (newc < n && map[newr][newc] == 0) {
				map[newr][newc] = 2;
				dfs(newr, newc, 2);
				map[newr][newc] = 0;
			}
			boolean garoflag = true;
			for (int i = 0; i < 3; i++) {
				newr = row + degakdir[i][0];
				newc = col + degakdir[i][1];
				if (newr >= n || newc >= n || map[newr][newc] != 0) {
					garoflag = false;
					break;
				}
			}
			if (garoflag) {
				for (int i = 0; i < 3; i++) {
					newr = row +degakdir[i][0];
					newc = col + degakdir[i][1];
					map[newr][newc] = 4;
				}
				dfs(row+1, col+1, 4);
				for (int i = 0; i < 3; i++) {
					newr = row + degakdir[i][0];
					newc = col + degakdir[i][1];
					map[newr][newc] = 0;
				}
			}

		} else if (dir == 3) {// 마지막 파이프가 세로
			int newr = row + serodir[0];
			int newc = col;
			if (newr < n && map[newr][newc] == 0) {
				map[newr][newc] = 3;
				dfs(newr, newc, 3);
				map[newr][newc] = 0;
			}
			boolean seroflag = true;
			for (int i = 0; i < 3; i++) {
				newr = row + degakdir[i][0];
				newc = col + degakdir[i][1];
				if (newr >= n || newc >= n || map[newr][newc] != 0) {
					seroflag = false;
					break;
				}
			}
			if (seroflag) {
				for (int i = 0; i < 3; i++) {
					newr = row + degakdir[i][0];
					newc = col + degakdir[i][1];
					map[newr][newc] = 4;
				}
				dfs(row+1, col+1, 4);
				for (int i = 0; i < 3; i++) {
					newr = row + degakdir[i][0];
					newc = col + degakdir[i][1];
					map[newr][newc] = 0;
				}
			}
		}else if(dir==4) {
			int newr = row ;
			int newc = col + garodir[1];
			if (newc < n && map[newr][newc] == 0) {
				map[newr][newc] = 2;
				dfs(newr, newc, 2);
				map[newr][newc] = 0;
			}
			
			newr = row + serodir[0];
			newc = col ;
			if (newr < n && map[newr][newc] == 0) {
				map[newr][newc] = 3;
				dfs(newr, newc, 3);
				map[newr][newc] = 0;
			}
			boolean degakflag = true;
			for (int i = 0; i < 3; i++) {
				newr = row + degakdir[i][0];
				newc = col + degakdir[i][1];
				if (newr >= n || newc >= n || map[newr][newc] != 0) {
					degakflag = false;
					break;
				}
			}
			if (degakflag) {
				for (int i = 0; i < 3; i++) {
					newr = row + degakdir[i][0];
					newc = col + degakdir[i][1];
					map[newr][newc] = 4;
				}
				dfs(row+1, col+1, 4);
				for (int i = 0; i < 3; i++) {
					newr = row + degakdir[i][0];
					newc = col +degakdir[i][1];
					map[newr][newc] = 0;
				}
			}
			
		}

	}

}
