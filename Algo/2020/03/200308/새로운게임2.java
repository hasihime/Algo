import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 새로운게임2 {

	static int n, k;
	static int[][] map = new int[12][12];
	static ArrayList<Integer>order[][]  = new ArrayList[12][12];
	static ArrayList<robot> a = new ArrayList<>();
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		input();
		playgame();
	}

	private static void playgame() {
		int turn = 0;
		while (turn<1000) {
			turn++;

			//1번 로봇부터 돌린다.
			for (int i = 0; i < k; ++i) {
				int row = a.get(i).row;
				int col = a.get(i).col;
				int ny = row + dy[a.get(i).dir];
				int nx = col + dx[a.get(i).dir];

				//벽면에 부딪치거나 맵이 2면 방향 바꾸고 이동
				if (!(0 <= ny && ny < n && 0 <= nx && nx < n) || map[ny][nx] == 2) {
					if (a.get(i).dir == 0)
						a.get(i).dir = 1;
					else if (a.get(i).dir == 1)
						a.get(i).dir = 0;
					else if (a.get(i).dir == 2)
						a.get(i).dir = 3;
					else if (a.get(i).dir == 3)
						a.get(i).dir = 2;
					ny = row + dy[a.get(i).dir];
					nx = col + dx[a.get(i).dir];
				}

				//또 이동한곳이 2면 그냥 방향만 바꾼채로 유지하고 가만히
				if (!(0 <= ny && ny < n && 0 <= nx && nx < n) || map[ny][nx] == 2)
					;
				//0번 칸이면 이동만 한다.
				else if (map[ny][nx] == 0) {
					int idx = -1;
					for (int j = 0; j < order[row][col].size(); ++j) {
						//현재 칸 아래부터 로봇 번호 가져온다.
						int cand = order[row][col].get(j);
						//현재칸 가져온 로봇의 번호가 지금 이동하고 있는 로봇이면 idx에 그 위에거 가져온다.
						if (cand == i) {
							idx = j;
						}
						//idx 갱신이 안된거면 다음거 가져온다.
						if (idx == -1)
							continue;
						//현재 가져온 로봇의 이동을 바꾸고 list에 넣어줌.
						a.get(cand).row = ny;
						a.get(cand).col = nx;
						order[ny][nx].add(cand);
						//만약 로봇이 4개가 되면 종료
						is4(turn, ny, nx);
					}
					//현재 칸에 있는 로봇은 제거.
					int cnt = order[row][col].size();
					for (int j = idx; j < cnt; ++j)
						order[row][col].remove(order[row][col].size() - 1);
				} 
				//1번 칸이면 
				else {
					int idx = -1;
					//역순으로 가져온다.
					for (int j = order[row][col].size() - 1; j >= 0; --j) {
						int cand = order[row][col].get(j);
						//리스트 로봇번호가 지금 이동하는거면 나옴.
						if (cand == i) {
							idx = j;
							break;
						}
					}
					for (int j = order[row][col].size() - 1; j >= idx; --j) {
						int cand = order[row][col].get(j);
						//꺼꾸로 넣어준다.
						a.get(cand).row = ny;
						a.get(cand).col = nx;
						order[ny][nx].add(cand);
						is4(turn, ny, nx);

					}
					int cnt = order[row][col].size();
					for (int j = idx; j < cnt; ++j)
						order[row][col].remove(order[row][col].size() - 1);
				}

			}
		}
		System.out.println(-1);
	}

	private static void is4(int turn, int ny, int nx) {
		if (order[ny][nx].size() >= 4) {
			System.out.println(turn);
			System.exit(0);
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 12; ++i)
			for (int j = 0; j < 12; ++j)
				order[i][j] = new ArrayList<>();

		for (int i = 0; i < k; ++i) {
			st = new StringTokenizer(br.readLine());
			int row, col, dir;
			row = Integer.parseInt(st.nextToken())-1;
			col = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken())-1;

			a.add(new robot(row, col, dir));
			order[row][col].add(i);
		}
	}

	public static class robot {
		int row;
		int col;
		int dir;

		public robot(int row, int col, int dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
}