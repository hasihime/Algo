import java.util.Scanner;
//https://www.acmicpc.net/problem/1629
public class 곱셈 {
/*
 * 	public static long mypow(long x, long y, long z) {
	    if (y == 0)return 1;
	    if (y % 2 == 1)
	        return (x*mypow(x, y - 1, z)) % z;
	    return mypow((x * x) % z, y / 2, z) % z;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());
		
		System.out.println(mypow(a,b,c));
	}
 * 
 * */
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long m = sc.nextLong();
		long d = sc.nextLong();

		long result = 1;
		// 터짐.
//		for (int i = 0; i < m; i++) {
//			result*=(n%d*n%d)%d;
//			result%=d;
//		}

		result=dq(n%d,m,d);
		System.out.println(result);
	}

	private static long dq(long n, long m, long d) {
		if (m==1) {
			return n;
		}
		if (m%2==0) {
			long r=dq(n,m/2,d);
			return (r*r)%d;
		}else {
			return (n*dq(n,m-1,d))%d;
		}
	}

}
