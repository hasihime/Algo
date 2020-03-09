import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 트리의부모찾기 {

	private static int n;
	private static int[] parents;
	private static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Input();
		bfs(1, list, parents, n);
		
	}

	private static void bfs(int start, ArrayList<ArrayList<Integer>> list, int[] parents, int n) {
		 LinkedList<Integer> queue = new LinkedList<Integer>(); 
		 queue.offer(start); parents[start] = 1; 
		 while(!queue.isEmpty()) { 
			 int parent = queue.poll(); 
			 for(int item : list.get(parent)) { 
				 if(parents[item] == 0) { 
					 parents[item] = parent; 
					 queue.offer(item); 
					 } 
				 }// end for 
			 }
		 for(int i=2; i<parents.length; i++) 
			 System.out.println(parents[i]);

		 }
		

	private static void Input() throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		//인접리스트
		list=new ArrayList<ArrayList<Integer>>();
		parents=new int[n+1];
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		//연결
		 int a, b; 
		 for(int i=1; i<n; i++) { 
			 StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
			 a = Integer.parseInt(st.nextToken()); 
			 b = Integer.parseInt(st.nextToken()); 
			 list.get(a).add(b); 
			 list.get(b).add(a); }
	}

}
