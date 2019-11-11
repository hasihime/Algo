import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class checked {
	boolean garo;
	boolean sero;
	boolean degakup;
	boolean degakdown;

	public checked() {
		super();
	}

}

public class 오목 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int map[][] = new int[19][19];
		checked visited[][] = new checked[19][19];

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				visited[i][j] = new checked();
			}
		}

		for (int i = 0; i < 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				int input = map[i][j];
				if (input == 0) {
					continue;
				}
				if (input == 1) {

					// 가로
					int count = 1;

					if (!visited[i][j].garo) {

						int newc = j;
						while (true) {
							newc++;
							if (newc >= 19)
								break;
							if (map[i][newc] == 1)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("1");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].garo = true;
							newc = j;
							while (true) {
								newc++;
								if (newc >= 19)
									break;
								if (map[i][newc] == 1)
									visited[i][newc].garo = true;
								else
									break;
							}
						}
					}
					// 세로
					count = 1;
					if (!visited[i][j].sero) {
						int newr = i;
						while (true) {
							newr++;
							if (newr >= 19)
								break;
							if (map[newr][j] == 1)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("1");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].sero = true;
							newr = i;
							while (true) {
								newr++;
								if (newr >= 19)
									break;
								if (map[newr][j] == 1)
									visited[newr][j].sero = true;
								else
									break;
							}
						}
					}
					// 대각선 위
					count = 1;
					if (!visited[i][j].degakup) {
						int newr = i;
						int newc = j;
						while (true) {
							newr--;
							newc++;
							if (newr < 0 || newc >= 19)
								break;
							if (map[newr][newc] == 1)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("1");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].degakup = true;
							newr = i;
							newc = j;
							while (true) {
								newr--;
								newc++;
								if (newr < 0 || newc >= 19)
									break;
								if (map[newr][newc] == 1)
									visited[newr][newc].degakup = true;
								else
									break;
							}
						}
					}
					// 대각선 아래
					count = 1;
					if (!visited[i][j].degakdown) {
						int newr = i;
						int newc = j;
						while (true) {
							newr++;
							newc++;
							if (newr >= 19 || newc >= 19)
								break;
							if (map[newr][newc] == 1)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("1");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].degakdown = true;
							newr = i;
							newc = j;
							while (true) {
								newr++;
								newc++;
								if (newr >= 19 || newc >= 19)
									break;
								if (map[newr][newc] == 1)
									visited[newr][newc].degakdown = true;
								else
									break;
							}
						}
					}
				}
				if (input == 2) {
					// 가로
					int count = 1;
					if (!visited[i][j].garo) {
						int newc = j;
						while (true) {
							newc++;
							if (newc >= 19)
								break;
							if (map[i][newc] == 2)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("2");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].garo = true;
							newc = j;
							while (true) {
								newc++;
								if (newc >= 19)
									break;
								if (map[i][newc] == 2)
									visited[i][newc].garo = true;
								else
									break;
							}
						}
					}

					// 세로
					count = 1;
					if (!visited[i][j].sero) {
						int newr = i;
						while (true) {
							newr++;
							if (newr >= 19)
								break;
							if (map[newr][j] == 2)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("2");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].sero = true;
							newr = i;
							while (true) {
								newr++;
								if (newr >= 19)
									break;
								if (map[newr][j] == 2)
									visited[newr][j].sero = true;
								else
									break;
							}
						}
					}

					// 대각선 위
					count = 1;
					if (!visited[i][j].degakup) {
						int newr = i;
						int newc = j;
						while (true) {
							newr--;
							newc++;
							if (newr < 0 || newc >= 19)
								break;
							if (map[newr][newc] == 2)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("2");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].degakup = true;
							newr = i;
							newc = j;
							while (true) {
								newr--;
								newc++;
								if (newr < 0 || newc >= 19)
									break;
								if (map[newr][newc] == 2)
									visited[newr][newc].degakup = true;
								else
									break;
							}
						}
					}
					// 대각선 아래
					count = 1;
					if (!visited[i][j].degakdown) {
						int newr = i;
						int newc = j;
						while (true) {
							newr++;
							newc++;
							if (newr >= 19 || newc >= 19)
								break;
							if (map[newr][newc] == 2)
								count++;
							else
								break;
						}
						if (count == 5) {
							System.out.println("2");
							System.out.println((i + 1) + " " + (j + 1));
							return;
						} else {
							visited[i][j].degakdown = true;
							newr = i;
							newc = j;
							while (true) {
								newr++;
								newc++;
								if (newr >= 19 || newc >= 19)
									break;
								if (map[newr][newc] == 2)
									visited[newr][newc].degakdown = true;
								else
									break;
							}
						}
					}
				}
			}
		}

		System.out.println(0);
	}

}
