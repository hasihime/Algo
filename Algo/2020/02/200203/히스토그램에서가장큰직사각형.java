import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 히스토그램에서가장큰직사각형 {
//https://dundung.tistory.com/96
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> st = new Stack<Integer>();
		long[] area=new long[n];
		for (int i = 0; i < n; i++) {
			area[i]= Integer.parseInt(br.readLine());
		}
		int fh=Integer.parseInt(br.readLine());
		int topH=fh;
		long result = 0;
		
		for (int i = 1; i < n; i++) {
			int cur = Integer.parseInt(br.readLine());
		}
	}
}
