import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class kakao2019_01 {
	public static void main(String[] args) {
		String[] record= {"Enter uid1234 Muzi","Change uid1234 Niniz", "Leave uid1234"};
		System.out.println(Arrays.toString(solution(record)));
	}
	
    public static String[] solution(String[] record) {
        
        HashMap<String, String> idmap=new HashMap<String, String>();
        int ordersize=0;
        for (int i = 0; i < record.length; i++) {
			StringTokenizer st=new StringTokenizer(record[i]," ");
        	
			String order=st.nextToken();
			String uid=st.nextToken();
			
			if (order.startsWith("E")) {
				String nick=st.nextToken();
				ordersize++;
				if (idmap.containsKey(uid)) {
					idmap.replace(uid, nick);
				}else {
					idmap.put(uid, nick);
				}
			}else if(order.startsWith("L")) {
				ordersize++;
			}else {
				String nick=st.nextToken();
				idmap.replace(uid, nick);
			}
		}
        String[] answer = new String[ordersize];
        int idx=0;
        for (int i = 0; i < record.length; i++) {
        	StringTokenizer st=new StringTokenizer(record[i]," ");
			String order=st.nextToken();
			String uid=st.nextToken();

			if (order.startsWith("E")) {
				answer[idx]=idmap.get(uid)+"님이 들어왔습니다.";
				idx++;

			}else if(order.startsWith("L")) {
				answer[idx]=idmap.get(uid)+"님이 나갔습니다.";
				idx++;
			}else {
				continue;
			}
		}
        return answer;
    }
}
