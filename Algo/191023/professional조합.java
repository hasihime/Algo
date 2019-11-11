import java.util.Scanner;

public class professional조합 {
	static int p = 1234567891;
	static long facto[];
	static int n ;
	static int r;

	private static long power(long a, int b) {
		if (b == 1)
			return a;

		long r = power(a, b / 2);
		r = (r * r) % p;
		if (b % 2 > 0) {
			r = (r * a) % p;
		}
		return r % p;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		/*
		 * nCr = fact(n) / (fact(r) x fact(n-r)) Here fact() means factorial.
		 * 
		 * nCr % p = (fac[n]* modIverse(fac[r]) % p * modIverse(fac[n-r]) % p) % p;
		 * 
		 */
		facto = new long[1000001];
		facto[0] = 1;

		for (int i = 1; i <= 1000000; i++) {
			facto[i] = (facto[i - 1] * i) % p;
		}

		int t = sc.nextInt();
		for (int T = 1; T <= t; T++) {
			 n = sc.nextInt();
			 r = sc.nextInt();

			long result = 0;
			result = (facto[n] % p * facto[n - r] % p * facto[r] % p) % p;

			System.out.println("#" + T + " " + combination());
		}

	}

	private static long combination() {
		// TODO Auto-generated method stub
		return (facto[n]*power(facto[n-r],p-2)%p*power(facto[r],p-2)%p);
	}

}
