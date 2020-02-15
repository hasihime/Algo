package hw_190829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 냉장고 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int N=Integer.parseInt(br.readLine());
		int[][] arr=new int [N][2];
				
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine()," " );
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int a=o1[0];
				int b=o2[0];
				return a-b;
			}
		});;
		
		List<int[]> list=new LinkedList<int[]>();
		list.add(arr[0]);
		boolean flag=false;
		for (int i = 1; i < arr.length; i++) {
			int [] temp=arr[i];
			flag=true;
			for (int j = 0; j < list.size(); j++) {
				int[] listtemp=list.get(j);
				if (listtemp[1]>=temp[0]) {
					listtemp[0]=listtemp[0]>=temp[0]?listtemp[0]:temp[0];
					listtemp[1]=listtemp[1]<=temp[1]?listtemp[1]:temp[1];
					list.remove(j);
					list.add(listtemp);
					flag=false;
					break;
				}
			}
			if (flag) {
				list.add(temp);
			}
			
		}
		System.out.println(list.size());
	}
}
