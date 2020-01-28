import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 감시 {
	public static class camera {
		int num;
		int row;
		int col;
		public camera(int num, int row, int col) {
			super();
			this.num = num;
			this.row = row;
			this.col = col;
		}
	}

	private static int c1[][]= {{},{-1,0},{0,1},{1,0},{-1,0}};
	private static int row;
	private static int col;
	private static int[][] map;
	private static int ccnt;
	private static int[] permuarr;
	private static int[] cameranumarr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new int[row][col];
		ccnt = 0;
		ArrayList<camera> clist = new ArrayList<camera>();
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] % 6 != 0) {
					ccnt++;
					clist.add(new camera(map[i][j], i, j));
				}
			}
		}
		System.out.println();
		cameranumarr=new int[ccnt];
		for (int i = 0; i < ccnt; i++) {
			cameranumarr[i]=clist.get(i).num;
		}
		permuarr=new int[ccnt];
		makepermu(0);
	}

	private static void makepermu(int cur) {
		if (cur==ccnt) {
			System.out.println(Arrays.toString(permuarr));
			makemap();
			return;
		}
		for (int i = 1; i <= 4; i++) {
			if (cameranumarr[cur]==2&&i>=3) {
				continue;
			}else if(cameranumarr[cur]==5&&i>=2) {
				continue;
			}
			permuarr[cur]=i;
			makepermu(cur+1);
		}
	}

	private static void makemap() {
		// TODO Auto-generated method stub
		
	}

}
