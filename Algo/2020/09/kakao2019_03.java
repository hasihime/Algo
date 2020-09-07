import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

public class kakao2019_03 {
	static int n = 0;
	static boolean[] visited;
	private static int row;
	private static int col;
	private static String[][] input;
	private static HashSet<String> answerset;
	private static LinkedList<String> setarr;

	public static void main(String[] args) {
		String[][] relation = 
				
//				{{"100","ryan","music","2"},
//				 {"200","apeach","math","2"},
//				 {"300","tube","computer","3"},
//				 {"400","con","computer","4"},
//				 {"500","muzi","music","3"},
//				 {"600","apeach","music","2"}};
			{{"b","2","a","a","b"},
				{"b","2","7","1","b"},
				{"1","0","a","a","8"},
				{"7","5","a","a","9"},
				{"3","0","a","f","9"}};

		System.out.println(solution(relation));

	}

	public static int solution(String[][] relation) {
		int answer = 0;
		answerset = new HashSet<String>();
		input = relation;
		row = relation.length;
		col = relation[0].length;
		n = col;
		visited = new boolean[n];
		makecombi(0);
		setarr = new LinkedList<String>();
		String[] temp = new String[answerset.size()];
		int idx = 0;
		for (String string : answerset) {
			temp[idx] = string;
			idx++;
		}
		Arrays.sort(temp, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.length() - o2.length();
			}
		});

		for (int i = 0; i < temp.length; i++) {
			if (hascontain(temp[i], setarr)) {
				setarr.add(temp[i]);
			}
		}
		return setarr.size();
	}

	private static boolean hascontain(String string, LinkedList<String> setarr) {
		String[] inputarr = string.split("");
		HashSet<Integer> inputset = new HashSet<Integer>();
		for (int i = 0; i < inputarr.length; i++) {
			inputset.add(Integer.parseInt(inputarr[i]));
		}
		for (int i = 0; i < setarr.size(); i++) {
			String[] temp = setarr.get(i).split("");
			HashSet<Integer> listset = new HashSet<Integer>();
			for (int j = 0; j < temp.length; j++) {
				listset.add(Integer.parseInt(temp[j]));
			}

			if (inputset.containsAll(listset)) {
				return false;
			}
		}

		return true;
	}

	private static void makecombi(int cur) {
		if (cur == n) {
			go();
			return;
		}
		visited[cur] = false;
		makecombi(cur + 1);
		visited[cur] = true;
		makecombi(cur + 1);
	}

	private static void go() {
		for (int i = 0; i < col; i++) {
			HashSet<String> set = new HashSet<>();
			boolean flag = true;
			for (int j = 0; j < row; j++) {
				String key = "";
				for (int k = 0; k < visited.length; k++) {
					if (visited[k]) {
						key += input[j][k];
					}
				}
				if (set.contains(key)) {
					flag = false;
					break;
				} else {
					set.add(key);
				}
			}
			if (flag) {
				String key = "";
				for (int j = 0; j < visited.length; j++) {
					if (visited[j])
						key += j;
				}
				answerset.add(key);
			}
		}
	}
}
