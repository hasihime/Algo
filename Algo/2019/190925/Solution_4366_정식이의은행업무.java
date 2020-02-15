import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_4366_정식이의은행업무 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			String binaryst = br.readLine();
			String ternaryst = br.readLine();

			int blen = binaryst.length();
			int tlen = ternaryst.length();
			int btod = 0;

			boolean flag = false;

			for (int i = 0; i < blen; i++) {
				btod = 0;

				StringBuilder bsb = new StringBuilder(binaryst);

				if (binaryst.charAt(i) == '0') {
					bsb.setCharAt(i, '1');
					for (int j =  0; j < blen; j++) {
						btod += (bsb.charAt(j) - '0') * Math.pow(2, blen - j-1);
					}
				} else {
					bsb.setCharAt(i, '0');
					for (int j = 0; j < blen; j++) {
						btod += (bsb.charAt(j) - '0') * Math.pow(2, blen - j-1);
					}
				}

				///////////////////////////////////////////////////
				for (int j = 0; j < tlen; j++) {

					int ttod = 0;
					StringBuilder tsb = new StringBuilder(ternaryst);
					if (ternaryst.charAt(j) == '0') {
						tsb.setCharAt(j, '1');
						for (int k = 0; k < tlen; k++) {
							ttod += (tsb.charAt(k) - '0') * Math.pow(3, tlen - k-1);
						}
						if (btod == ttod) {
							flag = true;
							break;
						}
						ttod = 0;
						tsb.setCharAt(j, '2');
						for (int k = 0; k < tlen; k++) {
							ttod += (tsb.charAt(k) - '0') * Math.pow(3, tlen - k-1);
						}
						if (btod == ttod) {
							flag = true;
							break;
						}
					} else if (ternaryst.charAt(j) == '1') {
						tsb.setCharAt(j, '0');
						for (int k = 0; k < tlen; k++) {
							ttod += (tsb.charAt(k) - '0') * Math.pow(3, tlen - k-1);
						}
						if (btod == ttod) {
							flag = true;
							break;
						}
						ttod = 0;
						tsb.setCharAt(j, '2');
						for (int k = 0; k < tlen; k++) {
							ttod += (tsb.charAt(k) - '0') * Math.pow(3, tlen - k-1);
						}
						if (btod == ttod) {
							flag = true;
							break;
						}
					}else {
						tsb.setCharAt(j, '0');
						for (int k = 0; k < tlen; k++) {
							ttod += (tsb.charAt(k) - '0') * Math.pow(3, tlen - k-1);
						}
						if (btod == ttod) {
							flag = true;
							break;
						}
						ttod = 0;
						tsb.setCharAt(j, '1');
						for (int k = 0; k < tlen; k++) {
							ttod += (tsb.charAt(k) - '0') * Math.pow(3, tlen - k-1);
						}
						if (btod == ttod) {
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					
					break;
				}
			}
			System.out.println("#"+t+" "+btod);
		}
	}

}
