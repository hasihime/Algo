
public class kakao2020_03 {
	public static void main(String[] args) {

		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

		System.out.println(solution(key, lock));

	}

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
		int width = lock.length;

		int[][] newlock = new int[3 * width][3 * width];
		int[][] backnewlock = new int[3 * width][3 * width];

		threetimemap(newlock, lock);
		copymap(backnewlock, newlock);

		// 돌리면서 시작.
		for (int d = 0; d < 4; d++) {
			key = rotate(key);
			// 가로 비교
			for (int i = 0; i < 2 * width; i++) {
				for (int j = 0; j < 2 * width; j++) {
					if (garo(newlock, key, i, j, width)) {
						return true;
					} else {
						copymap(newlock, backnewlock);
					}
				}
			}

			answer = false;
		}

		return answer;
	}

	private static boolean garo(int[][] lock, int[][] key, int row, int col, int width) {
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				lock[row + i][col + j] += key[i][j];
			}
		}
		return check(lock, width);
	}

	private static boolean check(int[][] lock, int width) {
		for (int i = width; i < 2 * width; i++) {
			for (int j = width; j < 2 * width; j++) {
				if (lock[i][j] != 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static int[][] rotate(int[][] key) {
		int size = key.length;
		int[][] rotatekey = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// 0,0 0,1 0,2 2,0 1,0 0,0
				// 1,0 1,1 1,2 ->2,1 1,1 0,1
				// 2,0 2,1 2,2 2,2 1,2 0,2
				rotatekey[j][size - 1 - i] = key[i][j];
			}
		}

		return rotatekey;
	}

	private static void copymap(int[][] newlock, int[][] lock) {
		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock.length; j++) {
				newlock[i][j] = lock[i][j];
			}
		}
	}

	private static void threetimemap(int[][] newlock, int[][] lock) {
		for (int i = lock.length; i < 2 * lock.length; i++) {
			for (int j = lock.length; j < 2 * lock.length; j++) {
				newlock[i][j] = lock[i - lock.length][j - lock.length];
			}
		}
	}
}
