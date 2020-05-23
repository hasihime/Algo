

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/18809
public class Gaaaaaaaaaarden {

	private static int row;
	private static int col;
	private static int gseed;
	private static int rseed;
	private static int[][] map;
	private static int persize;
	private static int[] permu;
	private static boolean[] checkper;
	private static int[] seedarr;
	private static int seedtotal;
	private static int result;

	public static void main(String[] args) throws Exception {
		input();
		go();
		
		
	}

	private static void go() {
		permu=new int[persize];
		seedtotal=gseed+rseed;
		checkper=new boolean[seedtotal];
		seedarr=new int[seedtotal];
		
		makepermu(0);
	}


	private static void makepermu(int cur) {
		if (cur==persize) {
			//씨 뿌리기 시작.
			if(result>bloom());
			return;
		}
		
		for (int i = 0; i < seedtotal; i++) {
			if (!checkper[i]) {
				checkper[i]=true;
				permu[cur]=i;
				makepermu(cur+1);
				checkper[i]=false;
			}
		}
		
	}

	private static int bloom() {
		
		
		return 0;
	}

	private static void input() throws Exception {
		result=0;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		row=Integer.parseInt(st.nextToken());
		col=Integer.parseInt(st.nextToken());
		gseed=Integer.parseInt(st.nextToken());
		rseed=Integer.parseInt(st.nextToken());
		LinkedList<matrix>canSeed=new LinkedList<matrix>();
		map=new int[row][col];
		
		
		for (int i = 0; i < row; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				// 배양액을 뿌릴 수 있는 땅을 구한다.
				 if (map[i][j]==2) {
					canSeed.add(new matrix(i, j));
				}
			}
		}
		persize=canSeed.size();
	}
	public static class matrix {
		int row;
		int col;
		
		public matrix(int row, int col) {
			this.row=row;
			this.col=col;
		}
	}
}
