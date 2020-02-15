package hw_day190816;

import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;
    import java.util.Comparator;
    import java.util.StringTokenizer;
     
    public class Solution_D3_1244_최대상금_오석빈2 {
        static int r = 0;
        static int count = 0;
        static int mintofindmaxcount = 0;
        static int size = 0;
        static int max = 0;
        static Integer[] arrint;
        static Integer[] sortarrint;
     
        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
     
            int T = Integer.parseInt(br.readLine());
            for (int t = 1; t <= T; t++) {
                //줄을 받아들임
                st = new StringTokenizer(br.readLine(), " ");
                 
                //숫자를 받아들이는 부분
                String num = st.nextToken();
                //움직이는 횟수 받아들이는 부분
                r = Integer.parseInt(st.nextToken());
                //들어오는 숫자 길이 파악
                size = num.length();
     
                //한자리씩 받아들이는 스트링 배열 생성
                String[] arr = num.split("");
                //스트링으로 된 배열을 int로 바꾸기 위한 용도
                arrint = new Integer[size];
                //들어온 숫자가 최대가 될 수 있는 경우를 배열하는 용도
                 
                sortarrint = new Integer[size];
     
                //스트링 배열에 들어있는 수를 int배열로 옮기는 코드
                for (int i = 0; i < size; i++) {
                    arrint[i] = Integer.parseInt(arr[i]);
                    sortarrint[i] = Integer.parseInt(arr[i]);
                }
                 
                //내림차순으로 배열을 정렬하면 최대의 숫자가 되고 그 배열을 sortarrint에 저장
                Arrays.sort(sortarrint, Comparator.reverseOrder());
     
                //count는 재귀 탈때마다 1씩 증가.
                count = 0;
                //max초기화
                max = 0;
                mintofindmaxcount = size;
     
                //함수구현
                if (size>=r) {
                    findmax(count);
                }else if(size<r) {
                    findmax2(count);
                    //이부분은 아직 확정은 아니고 아이디어
                    //배열이 최대값으로 정렬시 남은 움직임 수가 1인 경우 맨 뒷자리, 그다음 뒷자리랑 바꾸는 것이 그다음 최댓값
                    //ex) 88832인 최대 배열을 끝에 숫자끼리 바꾸면  88823이 됨.
                    //단 남은 움직임 수가 2 이상이면 무조건 최대숫자로 원상복구가 가능함.
                    //ex) 88321이 남은 숫자가 2면 88312->88321
                    //    남은 숫자가 3이면 88132->88213->88321 이런 예시가 나옴
                    //그러므로 남은 횟수가 1일때만 바꿔주면 되고 아니면 최댓값을 출력하면 됨.
                     
                    if ((r - mintofindmaxcount )%2==1) {
                        //max가 int형이라 스트링으로 변환
                        String x = max + "";
                        //문자열 하나하나씩 담을 temp 임시 배열을 생성
                        String[] temp = x.split("");
                        //배열의 크기 지정
                        int tempsize = temp.length;
         
                        //마지막과 마지막 전과 swap
                        String y = null;
                        y = temp[tempsize - 1];
                        temp[tempsize - 1] = temp[tempsize - 2];
                        temp[tempsize - 2] = y;
                         
                        //다시 하나의 문자로 생성
                         
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < temp.length; j++) {
                            sb.append(temp[j]);
                        }
                         
                        //sb를 인트형으로 변환
                        max = Integer.parseInt(sb.toString());
                         
                    }else if ((r - mintofindmaxcount )%2==0){
                         
                        StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < sortarrint.length; j++) {
                        sb.append(sortarrint[j]);
                    }
                    max = Integer.parseInt(sb.toString());
                    }
                }
                 
                //max갑 출력
                System.out.println("#" + t + " " + max);
            }
     
        }
     
        private static void findmax(int count) {
            //카운트가 돌리는 횟수만큼 되거나 최대 배열만큼 돌리면 리턴
            if (count == r || 
                    Arrays.equals(arrint, sortarrint)) {
 
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    sb.append(arrint[i]);
                }
                 
                //디버깅용 코드
//              System.out.println(count+" "+sb.toString());
                if (max < Integer.parseInt(sb.toString())) {
                    max = Integer.parseInt(sb.toString());
                }
                return;
            }
             
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                     
                    //swap
                    int temp = 0;
                    temp = arrint[i];
                    arrint[i] = arrint[j];
                    arrint[j] = temp;
                     
                     
                    //디버깅용 코드
    //              StringBuilder sb = new StringBuilder();
    //              for (int r = 0; r < size; r++) {
    //                  sb.append(arrint[r]);
    //              }
    //              System.out.println(count+" "+sb.toString());
                     
                    //재귀 함수 탐
                    findmax(count+1);
                     
                     
                    //swap
                    temp = 0;
                    temp = arrint[j];
                    arrint[j] = arrint[i];
                    arrint[i] = temp;
                     
                }
            }
        }
         
        private static void findmax2(int count) {
            //카운트가 돌리는 횟수만큼 되거나 최대 배열만큼 돌리면 리턴
            if (count == size-1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    sb.append(arrint[i]);
                }
                 
                //디버깅용 코드
//              System.out.println(count+" "+sb.toString());
                if (max < Integer.parseInt(sb.toString())) {
                    max = Integer.parseInt(sb.toString());
                }
                return;
            }
             
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                     
                    //swap
                    int temp = 0;
                    temp = arrint[i];
                    arrint[i] = arrint[j];
                    arrint[j] = temp;
                     
                    if(Arrays.equals(arrint, sortarrint) ) {
                            if(mintofindmaxcount>count+1) {
                            mintofindmaxcount=count+1;
                        }
                    }
                    //디버깅용 코드
    //              StringBuilder sb = new StringBuilder();
    //              for (int r = 0; r < size; r++) {
    //                  sb.append(arrint[r]);
    //              }
    //              System.out.println(count+" "+sb.toString());
                     
                    //재귀 함수 탐
                    findmax2(count+1);
                     
                     
                    //swap
                    temp = 0;
                    temp = arrint[j];
                    arrint[j] = arrint[i];
                    arrint[i] = temp;
                     
                }
            }
        }
     
    }