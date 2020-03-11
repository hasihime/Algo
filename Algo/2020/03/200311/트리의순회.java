import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/1991
public class 트리의순회 {

	public static class node {
		char data;
		node left,right;
		public node(char data) {
			super();
			this.data = data;
		}
		
	}
public static class tree {
	node root;
	public void add(char data, char leftdata,char rightdata) {
		if (root==null) {//루트 노드가 비어있는 상태이면..(아무것도 넣지 않은 최초 상태)
			if(data!='.') root=new node(data); // .이 아니라 값이 들어오면 루트 노드 생성
			if(leftdata!='.')root.left=new node(leftdata); //좌측이 . 이 아니라면 왼쪽 자식생성
			if(rightdata!='.') root.right=new node(rightdata);// 우측치 .이 아니라면 오른쪽 자식 생성
		}
		//루트 노드가 이미 있으면 탐색해서 붙인다.
		else {
			search(root, data, leftdata, rightdata);
		}
	}
	
	// 검색
	public void search(node root, char data, char leftdata, char rightdata) {
		//삽입 위치를 찾지 못하면 재귀 종료
		if (root==null) return;
		//위치를 찾았으면
		else if(root.data==data){
			//데이터를 붙여준다. '.'이 아닌 경우만
			if (leftdata!='.')root.left=new node(leftdata);
			if (rightdata!='.')root.right=new node(rightdata);
			}
		//데이터 못찾으면 좌우로 가서 탐색
		else {
			search(root.left, data, leftdata, rightdata);
			search(root.right, data, leftdata, rightdata);
		}
	}
	public void preorder(node root) {
		//일단 가운데 출력
		sb.append(root.data);
		//왼쪽 출력
		if (root.left!=null) preorder(root.left);
		//그다음 오른쪽
		if (root.right!=null) preorder(root.right);
			
	}
	public void inorder(node root) {
		//왼쪽 탐색
		if (root.left!=null) inorder(root.left);
		//일단 가운데 출력
		sb.append(root.data);
		//그다음 오른쪽
		if (root.right!=null) inorder(root.right);
	}
	public void postorder(node root) {
		//왼쪽 탐색
		if (root.left!=null) postorder(root.left);
		//그다음 오른쪽
		if (root.right!=null) postorder(root.right);
		//일단 가운데 출력
		sb.append(root.data);
	}
}
private static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
	}
	private static void input() throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		int n=Integer.parseInt(br.readLine());
		tree t=new tree();
	
		for (int i = 0; i < n; i++) {
			char[] input=br.readLine().toCharArray();
			t.add(input[0], input[2], input[4]);
		}
		
		t.preorder(t.root);
		sb.append("\n");
		t.inorder(t.root);
		sb.append("\n");
		t.postorder(t.root);
		System.out.println(sb);
		br.close();
	}

}
