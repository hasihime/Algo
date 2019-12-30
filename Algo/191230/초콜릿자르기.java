import java.util.Scanner;

public class 초콜릿자르기 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		int m=sc.nextInt();
		
		int cut=m-1;
		
		cut+=(n-1)*m;
		
		System.out.println(cut);
	}

}
