import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class kakao2020_07 {
	private static int[][] map;
	private static int mapsize;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[][][] visited;
	private static PriorityQueue<Robot> pq;
	private static int[][] newmap;


	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		System.out.println(solution(board));
	}

	public static int solution(int[][] board) {
		map = board;
		int answer = 0;
		mapsize = board.length;
		
		//원래 맵의 경계 체크 생략용 맵 제작
		newmap = new int[mapsize + 2][mapsize + 2];
		for (int i = 0; i < newmap.length; i++) {
			for (int j = 0; j < newmap.length; j++) {
				newmap[i][j] = 1;
			}
		}
		
		//원래 맵 이식
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				newmap[i + 1][j + 1] = map[i][j];
			}
		}
		
		
		int newmapsize = newmap.length;
		visited = new int[2][newmapsize][newmapsize];
		for (int i = 0; i < newmapsize; i++) {
			for (int j = 0; j < newmapsize; j++) {
				visited[0][i][j] = 10000;
				visited[1][i][j] = 10000;
			}
		}
		Robot robot = new Robot(1, 1, 0, 0);

			pq = new PriorityQueue<Robot>(new Comparator<Robot>() {
	
				@Override
				public int compare(Robot o1, Robot o2) {
					// TODO Auto-generated method stub
					return o1.getCost() - o2.getCost();
				}
			});

		pq.add(robot);
		visited[1][1][0] = 0;
		while (!pq.isEmpty()) {
			Robot curRobot = pq.poll();
			int row = curRobot.getRow();
			int col = curRobot.getCol();
			int dir = curRobot.getDir();
			int cost = curRobot.getCost();
			if ((row == mapsize && col == mapsize) || (dir == 0 && row == mapsize && col + 1 == mapsize)
					|| (dir == 1 && row + 1 == mapsize && col == mapsize)) {
				answer = cost;
				break;
			}
			// 이동해서 움직일수 있는지 확인
			move(row, col, dir, cost);

			// 회전에서 움직일수 있는지 확인
			rotate(row, col, dir, cost);

		}

		return answer;
	}

	private static void rotate(int row, int col, int dir, int cost) {
		// 가로일때는 오른쪽
		if (dir == 0) {
			// 일단 row,col기준

			// 기준점부터 우상,장애물 확인
			if (newmap[row - 1][col] == 0 && newmap[row - 1][col + 1] == 0 && cost + 1 < visited[1][row - 1][col]) {
				pq.add(new Robot(row - 1, col, 1, cost + 1));
				visited[1][row - 1][col] = cost + 1;
			}

			// 기준점으로 우하 확인
			if (newmap[row + 1][col] == 0 && newmap[row + 1][col + 1] == 0 && cost + 1 < visited[1][row][col]) {
				pq.add(new Robot(row, col, 1, cost + 1));
				visited[1][row][col] = cost + 1;

			}

			// 로봇의 오른쪽 몸통 기준

			// 좌상 확인
			if (newmap[row - 1][col + 1] == 0 && newmap[row - 1][col] == 0 && cost + 1 < visited[1][row - 1][col + 1]) {
				pq.add(new Robot(row - 1, col + 1, 1, cost + 1));
				visited[1][row - 1][col + 1] = cost + 1;
			}

			// 기준점으로 좌하 확인
			if (newmap[row + 1][col + 1] == 0 && newmap[row + 1][col] == 0 && cost + 1 < visited[1][row][col + 1]) {
				pq.add(new Robot(row, col + 1, 1, cost + 1));
				visited[1][row][col + 1] = cost + 1;
			}

		} else {
			// 새로일때는 아래
			// 일단 row,col기준

			// 기준점부터 좌하,장애물 확인
			if (newmap[row][col - 1] == 0 && newmap[row + 1][col - 1] == 0 && cost + 1 < visited[0][row][col - 1]) {
				pq.add(new Robot(row, col - 1, 0, cost + 1));
				visited[0][row][col - 1] = cost + 1;
			}

			// 기준점부터 우하,장애물 확인
			if (newmap[row][col + 1] == 0 && newmap[row + 1][col + 1] == 0 && cost + 1 < visited[0][row][col]) {
				pq.add(new Robot(row, col, 0, cost + 1));
				visited[0][row][col] = cost + 1;
			}

			// 로봇의 아래 몸통 기준

			// 좌상 확인
			if (newmap[row + 1][col - 1] == 0 && newmap[row][col - 1] == 0 && cost + 1 < visited[0][row + 1][col - 1]) {
				pq.add(new Robot(row + 1, col - 1, 0, cost + 1));
				visited[0][row + 1][col - 1] = cost + 1;
			}

			// 우상 확인
			if (newmap[row + 1][col + 1] == 0 && newmap[row][col + 1] == 0 && cost + 1 < visited[0][row + 1][col]) {
				pq.add(new Robot(row + 1, col, 0, cost + 1));
				visited[0][row + 1][col] = cost + 1;
			}
		}

	}

	private static void move(int row, int col, int dir, int cost) {
		for (int i = 0; i < dr.length; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			int garoc = nc + 1;
			int seror = nr + 1;

			if (cost + 1 < visited[dir][nr][nc] && newmap[nr][nc] == 0) {
				if (dir == 0 && newmap[nr][garoc] == 0) {
					pq.add(new Robot(nr, nc, dir, cost + 1));
					visited[dir][nr][nc] = cost + 1;
				}
				if (dir == 1 && newmap[seror][nc] == 0) {
					pq.add(new Robot(nr, nc, dir, cost + 1));
					visited[dir][nr][nc] = cost + 1;
				}

			}
		}
	}

	public static class Robot {
		int row;
		int col;
		int dir;
		int cost;

		public Robot(int row, int col, int dir, int cost) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.cost = cost;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public int getDir() {
			return dir;
		}

		public void setDir(int dir) {
			this.dir = dir;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

	}

}
