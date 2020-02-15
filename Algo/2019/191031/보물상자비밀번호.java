import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 보물상자비밀번호 {
	static int N;
	static Set<String> set;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
	
		for (int t = 1; t <= T; t++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			set=new HashSet<String>();
			int K=Integer.parseInt(st.nextToken());
			
			Deque<Character>que=new LinkedList<Character>();
			
			String input=br.readLine();
			for (int i = 0; i < N; i++) {
				que.add(input.charAt(i));
			}
			makeres(que);
			
			
			for (int i = 0; i < N/4; i++) {
				char inputc=que.pollLast();
				que.addFirst(inputc);
				makeres(que);
			}
			
			String [] results=new String[set.size()];
			
			int x=0;
			for (String e : set) {
				results[x]=e;
				x++;
			}
			Arrays.sort(results);
			
			String result=results[set.size()-(K)];
			
			
			long resultnum=Integer.parseUnsignedInt(result, 16);
			System.out.println("#"+t+" "+resultnum);
		}
		
	}

	private static void makeres(Deque<Character> que) {
		
		Queue<Character>tempq=new LinkedList<>(que);
		int dnum=N/4;

		String res0="";
		String res1="";
		String res2="";
		String res3="";
		for (int i = 0; i < N; i++) {
			String temp=tempq.poll().toString();
			
			switch (i/dnum) {
			case 0:
				res0+=temp;
				break;
			case 1:
				res1+=temp;
				break;
			case 2:
				res2+=temp;
				break;
			case 3:
				res3+=temp;
				break;
			}
		}
		set.add(res0);
		set.add(res1);
		set.add(res2);
		set.add(res3);
	}

}
