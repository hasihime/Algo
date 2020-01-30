import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스도미노쿠 {

	private static int[][] map;
	private static int N;
	private static StringTokenizer st;
	private static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine()," ");
		
		int T=1;
		while(true) {
			N=Integer.parseInt(st.nextToken());
			if (N==0) break;
			map=new int[9][9];
			makemap();
			
			T++;
		}
		
		
		
	}

	private static void makemap() throws IOException {
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int num1=Integer.parseInt(st.nextToken());
			String sn1=st.nextToken();
			int rn1=sn1.substring(0, 1).charAt(0)-'A';
			int cn1=Integer.parseInt(sn1.substring(1))-1;
			map[rn1][cn1]=num1;
			int num2=Integer.parseInt(st.nextToken());
			String sn2=st.nextToken();
			int rn2=sn2.substring(0, 1).charAt(0)-'A';
			int cn2=Integer.parseInt(sn2.substring(1))-1;
			map[rn2][cn2]=num2;
		}
		st=new StringTokenizer(br.readLine()," ");
		for (int i = 1; i <= 9; i++) {
			String sn1=st.nextToken();
			int rn1=sn1.substring(0, 1).charAt(0)-'A';
			int cn1=Integer.parseInt(sn1.substring(1))-1;
			map[rn1][cn1]=1;
		}
		
	}

}
