import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리의순회 {
//	https://www.acmicpc.net/problem/2263

	private static int n;
	private static int[] inorder;
	private static int[] postorder;
	private static int[] position;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		go(1, n, 1, n);

	}

	static void go(int is, int ie, int ps, int pe) {
		if (is > ie || ps > pe)
			return;
		int root = postorder[pe];
		System.out.print(root + " ");
		int inRoot = position[root]; // 인오더의 루트 인덱스
		int left = inRoot - is; // 포스트오더의 왼쪽 자식의 수
		go(is, inRoot - 1, ps, ps + left - 1);
		go(inRoot + 1, ie, ps + left, pe - 1);
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		inorder = new int[n + 1];
		postorder = new int[n + 1];
		position = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		// 중위순열의 값의 위치를 저장한다.
		for (int i = 1; i <= n; i++)
			position[inorder[i]] = i;
	}

}
