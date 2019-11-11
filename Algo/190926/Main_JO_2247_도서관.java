import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_JO_2247_도서관 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int person[][] = new int[N][2];

		for (int i = 0; i < person.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			person[i][0] = Integer.parseInt(st.nextToken());
			person[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(person, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int a = o1[0];
				int b = o2[0];
				if (a == b) {
					return o1[1] - o2[1];
				}
				return a - b;
			}
		});

		int yestimestart = 0, yestimeend = 0;
		int notimestart = 0, notimeend = 0;
		int yesmaxtime = 0, nomaxtime = 0;

		for (int i = 0; i < N; i++) {

			if (yestimeend < person[i][0]) {
				yestimestart = person[i][0];

				notimeend = yestimestart;

				int nocurtime = notimeend - notimestart;
				if (nocurtime > nomaxtime) {
					nomaxtime = nocurtime;
				}
			}
			if (yestimeend < person[i][1]) {
				yestimeend = person[i][1];
			}
			if (notimestart < yestimeend) {
				notimestart = yestimeend;
			}

			int yescurtime = yestimeend - yestimestart;
			if (yescurtime > yesmaxtime) {
				yesmaxtime = yescurtime;
			}

		}

		System.out.println(yesmaxtime + " " + nomaxtime);
	}
}
