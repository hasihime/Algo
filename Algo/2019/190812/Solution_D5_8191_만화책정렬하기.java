import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 8191. 만화책 정렬하기 
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWwtYmX6hvsDFAWU
 *  100,900 kb 메모리436 ms 실행시간 
 */
public class Solution_만화책 {
	 public static void main(String[] args) throws IOException {
		 BufferedReader sb=new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 
		 int T=Integer.parseInt(sb.readLine());
		 
		 for (int t = 1; t <=T; t++) {
			int n=Integer.parseInt(sb.readLine());
			
			int origin[]=new int [n+1];
			int arrange[]=new int [n+1];
			st=new StringTokenizer(sb.readLine()," ");
			for (int i = 1; i <= n; i++) {
				int temp=Integer.parseInt(st.nextToken());
				origin[i]=temp;
				arrange[temp]=i;
			}
			
			int count=1;
			
			for (int i = 1; i < n; i++) {
				if(arrange[i]>arrange[i+1]) {
					count++;
				}
			}
			
			System.out.println("#"+t+" "+count);
		}
	 }
}
