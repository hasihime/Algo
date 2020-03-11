import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질3 {

	private static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[] visited = new boolean[100001];
		result = Integer.MAX_VALUE;
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qcnt = new LinkedList<Integer>();
		qr.add(n);
		qcnt.add(0);

		while (!qr.isEmpty()) {
			int cr = qr.poll();
			int cc = qcnt.poll();
			if (cc >= result || visited[cr]) {
				continue;
			}
			if (cr > m) {
				result = Math.min(result, cc+(cr-m));
				continue;
			}
			if (cr == m) {
				result = Math.min(result, cc);
				break;
			}
			visited[cr] = true;

			if (cr - 1 >= 0 && !visited[cr - 1]) {
				qr.add(cr - 1);
				qcnt.add(cc + 1);
			}

			if (cr * 2 <= 100000 && !visited[cr * 2]) {
				qr.add(cr * 2);
				qcnt.add(cc);
			}

			if (cr + 1 <= 100000 && !visited[cr + 1]) {
				qr.add(cr + 1);
				qcnt.add(cc + 1);
			}
		}
		System.out.println(result);
	}

}
