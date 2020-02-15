import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJO_9205_맥주마시면서_걸어가기 {
	public final static int INF = 999999;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int HND=N+2;
			int input[][] = new int[HND][2];
			for (int i = 0; i < HND; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				input[i][0] = Integer.parseInt(st.nextToken());
				input[i][1] = Integer.parseInt(st.nextToken());
			}

			int map[][] = new int[HND][HND];
			for (int i = 0; i < HND; i++) {
				for (int j = 0; j < HND; j++) {
					if(i == j)
						continue;
					map[i][j] = Math.abs(input[j][0] - input[i][0]) + Math.abs(input[j][1] - input[i][1]);
					
					if (map[i][j] <= 1000) {
						map[i][j]=1;
					}
				}
			}

			for (int k = 0; k < HND; k++) {
				for (int i = 0; i < HND; i++) {
					if(i == k)
						continue;
					for (int j = 0; j < HND; j++) {
						if (i!=j&&j!=k&&map[i][k] + map[k][j] == 2) {
							map[i][j] = 1;
						}
					}
				}
			}


			
			if (map[0][N+1]==1) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}

	}

}
