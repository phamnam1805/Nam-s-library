// Bai tap sinh prufercode tu cay
import java.util.Scanner;

public class Tree {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        int[][] tree; // mang luu cac canh cua cay
        int[] degree; // mang luu bac cua cac dinh
        int[] prufer; // mang luu prufercode
        System.out.println("Nhập số cạnh của cây: ");
        n =  scan.nextInt();
        tree = new int[n][2]; // khoi tao mang
        prufer = new int[n-1]; // khoi tao mang
        degree = new int[n+1]; // khoi tao mang
        for (int i = 0; i < degree.length ; i++) {
            degree[i] = 0; // gan bac cho tat ca cac dinh bang 0
        }
        System.out.println("Nhập các cạnh của cây :");
        for(int i = 0; i < tree.length; i++){
			System.out.print("Nhap vao canh "+i+": ");
            tree[i][0] = scan.nextInt();
            tree[i][1] = scan.nextInt();
        }
        for (int i = 0; i < tree.length; i++) { // tinh bac cua cac dinh
            int[] tmp = tree[i];
            degree[tmp[0]] +=1;
            degree[tmp[1]] +=1;
        }
        for (int i = 0; i < degree.length ; i++) {
            System.out.println(i+ " "+ degree[i]);

        }
        for (int i = 0; i < prufer.length ; i++) {
            int v = 1; // bat dau tu dinh so 1
            while (degree[v] != 1){
                v++; // tim dinh co bac bang 1
            }
            for (int j = 0; j <  tree.length; j++) { // duyet qua tat ca cac canh trong cay
                if(v == tree[j][0] && degree[tree[j][1]] != 0){ // tim canh chua dinh bac bang 1
                    degree[tree[j][0]] -= 1; // giam bac cua nhung dinh nam trong canh bi xoa
                    degree[tree[j][1]] -= 1;
                    prufer[i] =  tree[j][1]; // gan dinh bac 1 vao prufercode
                }else if(v == tree[j][1] && degree[tree[j][0]] != 0){ // tim canh chua dinh bac bang 1
                    degree[tree[j][0]] -= 1; // giam bac cua nhung dinh nam trong canh bi xoa
                    degree[tree[j][1]] -= 1;
                    prufer[i] =  tree[j][0]; // gan dinh bac 1 vao prufercode
                }
            }
        }
        System.out.println("Prufer code của cây là: "); // thuc hien in ra prufercode
        for (int i = 0; i < n-1 ; i++) {
            System.out.print(prufer[i]+" ");
        }
    }
}
