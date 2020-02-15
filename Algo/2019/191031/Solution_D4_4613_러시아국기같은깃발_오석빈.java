import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_4613_러시아국기같은깃발_오석빈 {
	static int n,m;
	static int [] comresult;
	static char flag[][];
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
	
		for (int t = 1; t <= T; t++) {
			result=Integer.MAX_VALUE;
			comresult=new int[3];
			
			for (int i = 0; i < 3; i++) {
				comresult[i]=1;
			}
			StringTokenizer st=new StringTokenizer(br.readLine(), " ");
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			 flag=new char[n][m];
			
			for (int i = 0; i < n; i++) {
				String input=br.readLine();
				for (int j = 0; j <m; j++) {
					flag[i][j]=input.charAt(j);
				}
			}
			
			makecom(0,0);
			System.out.println("#"+t+" "+result);
		}
		
	}

	private static void makecom(int cur,int target) {
		if (target==n-3) {
			int count=0;
			int wnum=comresult[0];
			int bnum=wnum+comresult[1];
			for (int i = 0; i < wnum; i++) {
				for (int j = 0; j <m; j++) {
					if (flag[i][j]!='W') {
						count++;
					}
				}
			}
			for (int i = wnum; i < bnum; i++) {
				for (int j = 0; j <m; j++) {
					if (flag[i][j]!='B') {
						count++;
					}
				}
			}
			for (int i = bnum; i < n; i++) {
				for (int j = 0; j <m; j++) {
					if (flag[i][j]!='R') {
						count++;
					}
				}
			}
			result=result>count?count:result;
			
			return;
		}
		for (int i = cur; i < 3; i++) {
			comresult[i]++;
			makecom(i,target+1);
			comresult[i]--;
		}
		
	}

}
