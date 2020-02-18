import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용액 {

	private static int N;
	private static int[] liquid;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		
		//처음과 끝을 비교하면서 내려온다.
		int a=liquid[0];
		int b=liquid[N-1];
		int gap=2000000000;
		int start=0;
		int end=N-1;
		while(start<end) {
						int curgap=liquid[start]+liquid[end];
			if (gap>Math.abs(curgap)) {
				gap=Math.abs(curgap);
				a=liquid[start];
				b=liquid[end];
			}
			if(Math.abs(liquid[start])>=Math.abs(liquid[end])) {
				start++;
			}else {
				end--;
			}
		}
		System.out.println(a+" "+b);
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		liquid=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			liquid[i]=Integer.parseInt(st.nextToken());
		}
		
	}

}
