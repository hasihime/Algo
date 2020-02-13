import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class aㅡb {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		Map<Long, Integer> map = new HashMap<Long, Integer>();

		Queue<Long> q = new LinkedList<Long>();
		q.add((long)n);
		int result = -1;
		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				long cn = q.poll();
				if (cn == t) {
					System.out.println(time + 1);
					return;
				} else if (cn > t) {
					continue;
				}
				if (!map.containsKey((cn * 10) + 1)) {
					map.put((cn * 10) + 1, 1);
					q.add((cn * 10) + 1);
				}
				if (!map.containsKey(cn * 2)) {
					map.put(cn * 2, 1);
					q.add(cn * 2);
				}

			}
			time++;
		}
		System.out.println(result);

	}

}
