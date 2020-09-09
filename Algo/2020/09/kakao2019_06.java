import java.util.HashMap;
import java.util.LinkedList;

//https://www.welcomekakao.com/learn/courses/30/lessons/42893
public class kakao2019_06 {
	public static void main(String[] args) {
		String word = "blind";
		String[] pages = {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" };
		System.out.println(solution(word, pages));

	}

	public static int solution(String word, String[] pages) {
		int answer = 0;
		word = word.toLowerCase();
		HashMap<String, LinkedList<String>> herfmap = new HashMap<>();
		HashMap<String, Integer> scoremap = new HashMap<>();
		LinkedList<String> keylist = new LinkedList<>();
		for (int i = 0; i < pages.length; i++) {
			int matchcnt = 0;
			String[] cur = pages[i].toLowerCase().split("\\P{Alpha}+");
			// word matchcnt
			for (int j = 0; j < cur.length; j++) {
				if (cur[j].equals(word)) {
					matchcnt++;
				}
			}
			// 본 페이지 주소 찾기
			String[] getPagename = pages[i].toLowerCase().split("meta property=\\\"og:url\\\" content=");
			String url = getPagename[1].split("\"")[1];
			String[] getatag = pages[i].toLowerCase().split("<a href=");
			LinkedList<String> herf = new LinkedList<String>();
			for (int k = 1; k < getatag.length; k++) {
				herf.add(getatag[k].split("\"")[1]);
			}
			herfmap.put(url, herf);
			scoremap.put(url, matchcnt);
			keylist.add(url);
		}

		double maxscore = -1.0;
		for (int i = 0; i < keylist.size(); i++) {
			String key = keylist.get(i);
			double curscore = 0.0;
			curscore += scoremap.get(key);
			
			int bylink=0;
			double linkscore = 0.0;
			for (int j = 0; j <keylist.size(); j++) {
				if (i==j) {
					continue;
				}
				String bylinkurl=keylist.get(j);
				LinkedList<String> linklist = herfmap.get(bylinkurl);
				for (int k = 0; k < linklist.size(); k++) {
					if (linklist.get(k).equals(key)) {
						linkscore+=(double)scoremap.get(keylist.get(j))/(double)linklist.size();
					}
				}
			}
			curscore += linkscore;
			if (maxscore<curscore) {
				answer=i;
				maxscore=curscore;
			}
		}
		return answer;
	}

}
