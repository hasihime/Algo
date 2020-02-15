import java.util.Scanner;

/*
 * 벌집 
 * https://www.acmicpc.net/problem/2292
 *  메모리 14252kb	시간 104ms
 */
public class Main_벌집 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);

		int n= sc.nextInt();
		
		int i=0;
		int lastnum=1;
		int count=1;
		while(true) {
			if(n<=lastnum) {
				System.out.println(count);
				break;
				
			}
			i+=6;
			count++;
			lastnum+=i;
		}
	}
}
