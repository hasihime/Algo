import java.util.Arrays;
import java.util.Scanner;

public class combination2 {
	static int n;
	static int r;
	static int arr[];
	static int carr[];
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		r=sc.nextInt();
		arr=new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i]=i+1;
		}
		
		carr=new int[r];
		c(0,0);
		
	}

	private static void c(int startnum,int count) {
		if (count==r) {
			System.out.println(Arrays.toString(carr));
			return;
		} 
		for (int i = startnum; i < arr.length; i++) {
			carr[count]=arr[i];
			c(i+1, count+1);
		}
		
	}

}
