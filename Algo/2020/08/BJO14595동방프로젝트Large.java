import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJO14595동방프로젝트Large {

	private static int roomcnt;
	private static int count;
	private static int[][] arr;
	private static Stack<int[]> st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		if (arr.length > 0) {
			checkboundary();
			System.out.println(getroomcnt());
		} else {
			System.out.println(roomcnt);
		}
	}

	private static int getroomcnt() {
		int result = roomcnt;
		int size = st.size();
		for (int i = 0; i < size; i++) {
			int[] temp = st.pop();
			result += temp[0];
			result -= temp[1];
		}
		return result;
	}

	private static void checkboundary() {
		st = new Stack<int[]>();
		st.add(arr[0]);
		for (int i = 1; i < count; i++) {
			// 스택 top의 끝에 수 -지금 들어오는 수 앞의 수가 0보다 크면 포함관계
			if (st.peek()[1] >= arr[i][0]) {
				// 벽을 허물고 방을 합친다. 단 들어온 수의 뒷 수가 top의 뒷자리보다 큰 경우만.
				if (st.peek()[1] < arr[i][1]) {
					int[] temp = st.pop();
					temp[1] = arr[i][1];
					st.add(temp);
				}
			} else {
				st.add(arr[i]);
			}
		}

	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		roomcnt = Integer.parseInt(br.readLine());
		count = Integer.parseInt(br.readLine());

		arr = new int[count][2];
		for (int i = 0; i < count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
	}
}
