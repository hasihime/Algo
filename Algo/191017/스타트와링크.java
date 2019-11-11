import java.util.Arrays;
import java.util.Scanner;

public class 스타트와링크 {
	static int n=0;
	static int r=0;
	static int team[][];
	static int arr[];
	static int startscore=0;
	static int linkscore=0;
	static int min=Integer.MAX_VALUE;
	
	static int start[];
	static int link[];
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		arr=new int[n];
		team=new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				team[i][j]=sc.nextInt();
			}
		}
		
		for (int i = 0; i < n; i++) {
			arr[i]=i;
		}
			start=new int[n/2];
			link=new int[n/2];
			c(0,0);
System.out.println(min);
	}

	private static void c(int startnum,int count) {
		if (count==n/2) {
			 startscore=0;
			 linkscore=0;
			 
			 int p=0;
			 boolean[] check=new boolean[n];
			 for (int i = 0; i < n/2; i++) {
				check[start[i]]=true;
			}
			 
			 for (int i = 0; i < n; i++) {
				if (!check[i]) {
					link[p]=i;
					p++;
				}
			}
			 
			 Arrays.sort(link);
			 
			for (int i = 0; i < n/2; i++) {
				for (int j = 0; j < n/2; j++) {
					if (i==j) continue;
					startscore+= team[start[i]][start[j]];
					linkscore+= team[link[i]][link[j]];
				}
			}
			int curgap=Math.abs(startscore-linkscore);
			if (min>curgap) {
				min=curgap;
			}
			
			return;
		} 
		
		for (int i = startnum; i < n; i++) {
			start[count]=arr[i];
			if (start[0]!=0) {
				break;
			}
			c(i+1, count+1);
			
		}
		
	}

}
