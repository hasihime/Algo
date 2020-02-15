package hw_190821;


import java.util.Arrays;
import java.util.Scanner;

class map {
	int x;
	int y;

	public map(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_D5_1247_최적경로_오석빈 {
	
	static int n;
	
	static int closematrix[][] ;
	static int min;
	static int indexarr[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			min=1000000000;
			n = sc.nextInt();
			indexarr=new int[n];
			for (int i = 0; i < n; i++) {
				indexarr[i] = i+2;
			}
			
			map arr[] = new map[n+2];
			for (int i = 0; i < n+2; i++) {
				arr[i] = new map(sc.nextInt(), sc.nextInt());
			}

//			for (map map : arr) {
//				System.out.println(map.x + " " + map.y);
//			}
			closematrix= new int[n+2][n+2];

			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					closematrix[i][j] = (Math.abs(arr[i].x - arr[j].x)) + (Math.abs(arr[i].y - arr[j].y));
				}
			}
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(closematrix[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			findroad(0);
			System.out.println("#"+t+" "+min);
		}
	}
	
	
	private static void findroad(int index) {
		if (index==n) {
			int distance=0;
//			System.out.println(Arrays.toString(indexarr));
			distance+=closematrix[0][indexarr[0]];
			for (int i = 1; i < n; i++) {
				
				distance+=closematrix[indexarr[i-1]][indexarr[i]];
				
			}
			distance+=closematrix[indexarr[n-1]][1];
//			System.out.println(distance);
			if(min>distance) min=distance;
			return;
		}
		

		for (int i = index; i < n; i++) {
			swap(index, i);
			
//			distance+=closematrix[start][index];
			findroad(index+1);
			swap(i, index);
			
		}
	}

	private static void swap(int i,int j) {
		int temp=indexarr[i];
		indexarr[i]=indexarr[j];
		indexarr[j]=temp;
	}
}
