import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 피자굽기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] oven = new int[D+1];
		int[] maxoven = new int[D+1];
		Map<Integer, Integer>omap=new HashMap<Integer,Integer>();
		//지름을 키로 최대 깊이기 몇까지
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
			if (i==1) {
				maxoven[i]=oven[i];
			}else {
				if (maxoven[i-1]<=oven[i]) {
					maxoven[i]=maxoven[i-1];
					
				}else {
					maxoven[i]=oven[i];
					omap.put(maxoven[i-1], i-1);
				}
			}
		}
		omap.put(maxoven[D], D);
		
		int[] dough = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			dough[i] = Integer.parseInt(st.nextToken());
		}
		int maxdough=maxoven[1];
		int lastdough = D;
		int canr=maxoven[D];
		for (int i = 0; i < N; i++) {
			int curdough=dough[i];
			if (maxdough<curdough) {
				System.out.println(0);
				return;
			}
			if (canr<=curdough) {
				canr=curdough;
				lastdough=omap.get(curdough);
				omap.replace(curdough, lastdough-1);
			}else {
				lastdough=omap.get(canr);
				if (omap.get(canr)==0) {
					lastdough=0;
					break;
				}
				omap.replace(canr, lastdough-1);
			}
		}
		System.out.println(lastdough);
	}

}
