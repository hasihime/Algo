import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/1865
public class 웜홀 {

   static class Info {
      int from, dest, cost;
      
      Info(int f, int d, int c) {
         from = f;
         dest = d;
         cost = c;
      }
   }
   
   static int n;
   static ArrayList<Info> rel;

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      StringBuilder sb = new StringBuilder();
      int T = Integer.parseInt(br.readLine());
      while(--T > -1) {
         st = new StringTokenizer(br.readLine());
         rel = new ArrayList<Info>();
         n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
         for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken());
            rel.add(new Info(a, b, c));
            rel.add(new Info(b, a, c));
         }
         for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken());
            rel.add(new Info(a, b, -c));
         }
         if(find(0)) sb.append("YES\n");
         else sb.append("NO\n");
      }
      System.out.print(sb);
   }

   static boolean find(int node) {
      int[] dist = new int[n];
      Arrays.fill(dist, 1000000000);
      dist[node] = 0;
      boolean update = false;

      for (int i = 0; i < n; i++) {
         update = false;
         for (int j = 0; j < rel.size(); j++) {
            Info tmp = rel.get(j);
            if (dist[tmp.dest] > dist[tmp.from] + tmp.cost) {
               dist[tmp.dest] = dist[tmp.from] + tmp.cost;
               update = true;
            }
         }
         if (!update) break;
      }
      if (update) {
         for (int i = 0; i < rel.size(); i++) {
            Info tmp = rel.get(i);
            if (dist[tmp.dest] > dist[tmp.from] + tmp.cost) {
               update = true;
            }
         }
      }
      return update;
   }
}