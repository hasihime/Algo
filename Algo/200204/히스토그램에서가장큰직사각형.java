import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 히스토그램에서가장큰직사각형 {
//	
	public static class rec {
		int idx;
		long height;

		public rec(int idx, long height) {
			super();
			this.idx = idx;
			this.height = height;
		}

	}

	private static Stack<rec> stack;
	private static long result;
	private static long preh;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;

			stack = new Stack<rec>();
			result = 0;
			stack.add(new rec(0, 0));
			preh=1000000000;
			for (int i = 1; i <= n; i++) {
				long curh = Integer.parseInt(st.nextToken());
				work(i, curh);
			}
			work(n + 1, 0);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void work(int idx, long curh) {
		if (stack.peek().height <= curh) {
			stack.add(new rec(idx, curh));
		} else {
			while (stack.peek().height > curh) {
				rec cur = stack.pop();
				preh=cur.height-1;
				long temp = (idx-cur.idx) * cur.height;
				result = Math.max(result, temp);
			}
			if (curh<preh) {
				result = Math.max(result, preh*idx);
			}
			stack.add(new rec(idx, curh));
		}

	}
}
