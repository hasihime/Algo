import java.util.Scanner;

public class 아바바바 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		for (int T = 1; T <= t; T++) {
			long n=sc.nextLong();
			
			long result=(n/2)*(n/2);
			
			System.out.println("#"+t+" "+result);
		}
		
	}

}
