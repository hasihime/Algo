import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class egg {
	int armor;
	int weight;

	public egg(int armor, int weight) {
		super();
		this.armor = armor;
		this.weight = weight;
	}

}

public class 계란으로계란치기 {
	static List<egg> egglist;
	static int n = 0;
	static int eggcnt;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		egglist = new ArrayList<egg>(n);
		visited=new boolean[n];
		eggcnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int armor = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			egglist.add(new egg(armor, weight));
		}

		visited[0]=true;
		dfs(0);

		System.out.println(eggcnt);

	}

	private static void dfs(int cur) {
		if (cur == n - 1) {
			int tempcnt = 0;
			for (int i = 0; i < n; i++) {
				if (egglist.get(i).armor<=0) {
					tempcnt++;
				}
			}
			eggcnt = eggcnt < tempcnt ? tempcnt : eggcnt;
			return;
		}

		for (int i=cur+1;  i< visited.length; i++) {
			if (egglist.get(cur).armor<=0) {
				dfs(cur+1);
			}
			
			if (!visited[i]) {
				egglist.get(cur).armor-=egglist.get(i).weight;
				egglist.get(i).armor-=egglist.get(cur).weight;	
				visited[i]=true;
				dfs(i);
				egglist.get(cur).armor+=egglist.get(i).weight;
				egglist.get(i).armor+=egglist.get(cur).weight;	
				visited[i]=false;
				
			}
			
		}
	}

}
