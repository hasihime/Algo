import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJO_10159_저울 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int t=Integer.parseInt(br.readLine());
		int map[][]=new int[n+1][n+1];
		
		
		for (int i = 0; i < t; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine(), " ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			map[a][b]=1;
//			map[b-1][a-1]=1;
		}

		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if (i==k) continue;
				for (int j = 1; j <= n; j++) {
					if (i==j) continue;
					if (map[i][k]+map[k][j]==2) {
						map[i][j]=1;
					}
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			int count=0;
			for (int j = 1; j <= n; j++) {
				if (map[i][j]+map[j][i]==0) {
					count++;
					
				}
			}
			System.out.println(count-1);
		}
	}

}
