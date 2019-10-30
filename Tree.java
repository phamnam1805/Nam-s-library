
import java.util.Scanner;

public class Tree {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        int[][] tree;
        int[] degree;
        int[] prufer;
        System.out.println("Nhập số cạnh của cây: ");
        n =  scan.nextInt();
        tree = new int[n][2];
        prufer = new int[n-1];
        degree = new int[n+1];
        for (int i = 0; i < degree.length ; i++) {
            degree[i] = 0;
        }
        System.out.println("Nhập các cạnh của cây :");
        for(int i = 0; i < tree.length; i++){
            tree[i][0] = scan.nextInt();
            tree[i][1] = scan.nextInt();
        }
        for (int i = 0; i < tree.length; i++) {
            int[] tmp = tree[i];
            degree[tmp[0]] +=1;
            degree[tmp[1]] +=1;
        }
        for (int i = 0; i < degree.length ; i++) {
            System.out.println(i+ " "+ degree[i]);

        }
        for (int i = 0; i < prufer.length ; i++) {
            int v = 1;
            while (degree[v] != 1){
                v++;
            }
            for (int j = 0; j <  tree.length; j++) {
                if(v == tree[j][0] && degree[tree[j][1]] != 0){
                    degree[tree[j][0]] -= 1;
                    degree[tree[j][1]] -= 1;
                    prufer[i] =  tree[j][1];
                }else if(v == tree[j][1] && degree[tree[j][0]] != 0){
                    degree[tree[j][0]] -= 1;
                    degree[tree[j][1]] -= 1;
                    prufer[i] =  tree[j][0];
                }
            }
        }
        System.out.println("Prufer code của cây là: ");
        for (int i = 0; i < n-1 ; i++) {
            System.out.print(prufer[i]+" ");
        }
    }
}
