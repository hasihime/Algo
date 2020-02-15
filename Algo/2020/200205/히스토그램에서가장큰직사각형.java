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
			
			//시작과 끝에 0짜리 직사각형을 넣는다고 생각.
			result = 0;
			stack.add(new rec(0, 0));
			for (int i = 1; i <= n; i++) {
				long curh = Integer.parseInt(st.nextToken());
					while (!stack.isEmpty()&&stack.peek().height > curh) {
						long curheight=stack.peek().height;
						stack.pop();
						long width = (i-stack.peek().idx-1);
						result = Math.max(result, curheight*width);
					}
				stack.push(new rec(i, curh));
			}
			long curh=0;
			while (!stack.isEmpty()&&stack.peek().height > curh) {
				long curheight=stack.peek().height;
				stack.pop();
				long width = ((n+1)-stack.peek().idx-1);
				result = Math.max(result, curheight*width);
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}
