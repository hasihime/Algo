package hw_day190816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_D3_1240_단순2진암호코드_오석빈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int startrow = 0, startcol = 0;
			int endrow = 0;
			boolean startflag = false;
			boolean endflag = false;
			int result = 0;

			char[][] sentence = new char[row][col];

			for (int i = 0; i < row; i++) {
				String line = br.readLine();
				for (int j = 0; j < col; j++) {

					sentence[i][j] = line.charAt(j);

					if (startflag == false && sentence[i][j] == '1') {
						startrow = i;
						startcol = j;
						startflag = true;
					}
				}
				if (endflag == false) {
					if (startflag && sentence[i][startcol] == '0') {
						endrow = i - 1;
						endflag = true;
					}
				}

			}

			boolean rowisequal = true;
			for (int i = startrow; i < endrow; i++) {
				for (int j = 0; j < col; j++) {
					if (sentence[i][j] != sentence[i + 1][j]) {
						rowisequal = false;
						break;
					}
					if (rowisequal == false) {
						result = 0;
						break;
					}
				}
			}

			if (rowisequal == false) {
				System.out.println("#" + t + " " + result);
				continue;
			}
			int endindex = 0;

			for (int i = col - 1; i >= 0; i--) {
				if (sentence[startrow][i] == '1') {
					endindex = i;
					break;
				}
			}

			StringBuilder code = new StringBuilder();
			int codecount = 0;
			for (int i = endindex; i >= 0; i -= 7) {
				codecount++;
				StringBuilder sb = new StringBuilder();
				if (codecount > 8) {
					break;
				}
				for (int j = 0; j < 7; j++) {
					sb.append(sentence[startrow][i - 6 + j]);

				}

				switch (sb.toString()) {
				case "0001101":
					code.append("0 ");
					break;

				case "0011001":
					code.append("1 ");
					break;

				case "0010011":
					code.append("2 ");
					break;
				case "0111101":
					code.append("3 ");
					break;

				case "0100011":
					code.append("4 ");
					break;
				case "0110001":
					code.append("5 ");
					break;
				case "0101111":
					code.append("6 ");
					break;
				case "0111011":
					code.append("7 ");
					break;
				case "0110111":
					code.append("8 ");
					break;
				case "0001011":
					code.append("9 ");
					break;
				default:
					break;
				}
			}
			code.reverse();
//			System.out.println(code.toString());
			
			StringTokenizer codest = new StringTokenizer(code.toString(), " ");
			
			if (codest.countTokens()!=8) {
				System.out.println("#" + t + " " + 0);
				continue;
			}
			
			int verifycode=0;
			for (int j = 0; j < 8; j++) {
				int num = Integer.parseInt(codest.nextToken());
				if (j % 2 == 0) {
					verifycode += num * 3;
					
				} else if (j % 2 == 1) {
					verifycode += num;
				}
					result+=num;
				
			}
			if (verifycode%10==0) {
				System.out.println("#" + t + " " + (result));
			}else if (result%10!=0) {
				System.out.println("#" + t + " " + 0);
			}
			
			
		}

	}
}
