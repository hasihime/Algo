
public class 마인즈랩_01몬티홀 {

	private static int[] firstpeek;
	private static int[] prize;
	private static int firstanswer = 0;
	private static int changeanswer = 0;

	public static void main(String[] args) {

		prize = new int[10000];
		// 문에 상품 셋팅
		for (int i = 0; i < 10000; i++) {
			prize[i] = (int) (Math.random() * 3) + 1;
		}

		firstpeek = new int[10000];
		// 처음 아무거나 픽함
		for (int i = 0; i < 10000; i++) {
			firstpeek[i] = (int) (Math.random() * 3) + 1;
		}

		System.out.println("처음 선택을 바꾸지 않는 경우 : " + keepfirst());

		System.out.println("처음 선택을 바꾸는 경우 : " + changefirst());

	}

	private static int changefirst() {
		for (int i = 0; i < 10000; i++) {
			// 첫번째 선택한 번호의 문에 자동차 있는 경우.
			// 바꾸면 무조건 염소
			if (prize[i] == firstpeek[i]) {
			}
			// 첫번째 선택한 번호의 문에 자동차 없음.
			// 이 때는 바꾸면 무조건 자동차가 있는 문임.
			else if (prize[i] != firstpeek[i]) {
				changeanswer++;
			}
		}

		return changeanswer;
	}

	private static int keepfirst() {
		for (int i = 0; i < 10000; i++) {
			if (prize[i] == firstpeek[i])
				firstanswer++;
		}
		return firstanswer;
	}

}
