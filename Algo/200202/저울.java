import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 저울 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int choo[]=new int[n];
		for (int i = 0; i < n; i++) {
			choo[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(choo);
		
		long result=choo[0];
		for (int i = 1; i < n; i++) {
			if (result+1<choo[i]) {
				break;
			}
			result+=choo[i];
		}
		if (choo[0]>1) {
			result=0;
		}
		System.out.println(++result);
	}//main 끝
}
