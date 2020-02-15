import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보석도둑 {
	public static class jewal{
		int weight;
		int price;
		public jewal(int weight, int price) {
			super();
			this.weight = weight;
			this.price = price;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int k=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		jewal[] ja=new jewal[k];
		int[] ba=new int[n];
		
		for (int i = 0; i < k; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int cw=Integer.parseInt(st.nextToken());
			int cv=Integer.parseInt(st.nextToken());
			ja[i]=new jewal(cw,cv);
		}
		for (int i = 0; i < n; i++) {
			ba[i]=Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(ja,new Comparator<jewal>() {
			@Override
			public int compare(jewal o1, jewal o2) {
				if (o1.weight==o2.weight) {
					return o2.price-o1.price;
				}
				return o1.weight-o2.weight;
			}
		});
		Arrays.sort(ba);
		
		PriorityQueue<jewal>jq=new PriorityQueue<jewal>(new Comparator<jewal>() {
			@Override
			public int compare(jewal o1, jewal o2) {
				return o2.price-o1.price;
			}
		});
		long result=0;
		int idx=0;
		
		for (int i = 0; i < n; i++) {
			int bagw=ba[i];
			while(idx<k) {
				jewal curj=ja[idx];
				if (bagw<curj.weight) {
					break;
				}
				jq.add(curj);
				idx++;
			}
			if (!jq.isEmpty()) {
				result+=jq.poll().price;
			}
		}
		System.out.println(result);
	}
}
