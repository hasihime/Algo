import java.util.LinkedList;
import java.util.Queue;

public class kakao2019_07 {
	public static void main(String[] args) {
		int[][] board = 
			{ 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 8, 8, 1, 2, 0, 0, 0, 0, 0, 0 },
				{ 8, 9, 1, 2, 2, 2, 0, 0, 0, 0 }, 
				{ 8, 9, 1, 1, 4, 0, 0, 0, 0, 0 },
				{ 0, 9, 9, 6, 4, 4, 4, 0, 0, 0 }, 
				{ 0, 0, 6, 6, 6, 7, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 7, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 7, 7, 0, 5, 5 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 } 
				};
//			{
//					{0,1,1,1,0},
//					{0,2,0,1,0},
//					{2,2,2,0,3},
//					{0,4,3,3,3},
//					{0,4,4,4,0}
//			};
//			{
//					{3,0,4,0,2},
//					{3,4,4,4,2},
//					{3,3,0,2,2},
//					{0,0,1,0,0},
//					{0,1,1,1,0}
//			};
			

		System.out.println(solution(board));

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static int n;
	private static boolean[][] visited;
	private static int[][] map;
	private static int[][][] possibleblock = { { 
		{ 1, 0, 0 }, 
		{ 1, 1, 1 }, 
		{ 0, 0, 0 }, },
			{ 
			{ 0, 1, 0 }, 
			{ 0, 1, 0 }, 
			{ 1, 1, 0 } }, { 
				{ 1, 0, 0 }, 
				{ 1, 0, 0 }, 
				{ 1, 1, 0 } },
			{ { 0, 0, 1 }, 
					{ 1, 1, 1 }, 
					{ 0, 0, 0 }, }, { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 0, 0 } },

	};
	private static int answer;
	private static boolean isbroken;

	public static int solution(int[][] board) {
		answer = 0;
		map = board;
		n = board.length;
		isbroken=false;
		
		
		while(true) {
			isbroken=false;
			visited = new boolean[n][n];
			
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < n; i++) {
					if (map[i][j]<=0) {
						map[i][j]=-1;
					}else {
						break;
					}
				}
			}
			//다 채우면 블록 탐색
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j]>0&&!visited[i][j]) {
						go(i,j,map[i][j]);
					}
				}
			}
			if (!isbroken) {
				break;
			}
		}
		return answer;
	}

	private static void go(int row, int col, int num) {
		int[][] sample = new int[3][3];
		// row가 가장 위에 값이니 가장 왼쪽인col만 찾자.
		int top = row;
		int bottom = row;
		int left = col;
		int right = col;
		LinkedList<Matrix> list = new LinkedList<Matrix>();
		Queue<Matrix> q = new LinkedList<Matrix>();
		list.add(new Matrix(row, col));
		q.add(new Matrix(row, col));
		visited[row][col] = true;

		while (!q.isEmpty()) {
			Matrix curmatrix = q.poll();
			int cr = curmatrix.row;
			int cc = curmatrix.col;

			// 가장자리 갱신
			top = top >= cr ? cr : top;
			bottom = bottom < cr ? cr : bottom;
			left = left >= cc ? cc : left;
			right = right < cc ? cc : right;

			for (int d = 0; d < dr.length; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == num && !visited[nr][nc]) {
					list.add(new Matrix(nr, nc));
					q.add(new Matrix(nr, nc));
					visited[nr][nc] = true;
				}
			}
		} // bfs끝

		// sample생성
		for (int i = 0; i < list.size(); i++) {
			sample[list.get(i).row - top][list.get(i).col - left] = 1;
		}
		// sample이 유효한 블록인지 확인.
		if (isPossibleBlock(sample)) {
			
		} else {
			return;
		}
		
		// 각 가장자리가 0이거나 num이 아니면 false return
		for (int i = top; i <= bottom; i++) {
			for (int j = left; j <= right; j++) {
				if (map[i][j] == -1 || map[i][j] == num) {
					continue;
				}else {
					return;
				}
			}
		}
		for (int k = 0; k < list.size(); k++) {
			map[list.get(k).row][list.get(k).col]=0;
		}
	isbroken=true;
	answer++;

	}

	private static boolean isPossibleBlock(int[][] sample) {
		for (int i = 0; i < possibleblock.length; i++) {
			boolean flag = true;
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (sample[j][k]==1||sample[j][k]==0) {
						if (possibleblock[i][j][k] != sample[j][k]) {
							flag = false;
							break;
						}
					}else if(sample[j][k]==-1) {
						if (possibleblock[i][j][k] != sample[j][k]+1) {
							flag = false;
							break;
						}
					}
				}
				if (!flag) {
					break;
				}
			}
			if (flag) {
				return true;
			}
		}
		return false;
	}

	static class Matrix {
		int row;
		int col;

		public Matrix(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}
}
