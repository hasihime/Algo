import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 스티커붙이기 {
private static int row;
private static int col;
private static int sticker;
private static int[][] map;
private static int[][][] sarr;

	//https://www.acmicpc.net/problem/18808
	public static void main(String[] args) throws IOException {
		input();
		go();

	}

	private static void go() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		row=Integer.parseInt(st.nextToken());
		col=Integer.parseInt(st.nextToken());
		sticker=Integer.parseInt(st.nextToken());

		//맵 생성
		map=new int[row][col];
		//스티커 배열 만들기.
		//0번째 스티커
		sarr=new int[sticker][][];
		for (int i = 0; i < sticker; i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			sarr[i]=new int[r][c];
			for (int x = 0; x < r; x++) {
				st=new StringTokenizer(br.readLine());
				for (int y = 0; y < c; y++) {
					sarr[i][x][y]=Integer.parseInt(st.nextToken());
				}
			}
		}
	}

}
