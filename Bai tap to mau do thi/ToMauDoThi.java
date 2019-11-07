import  java.io.*;
import java.util.Scanner;
import java.lang.String;

public class ToMauDoThi {
    public static void main(String[] args) throws IOException{
        File fin = new File("dothi.txt");
        File fout = new File("dothitomau.dot");
        FileOutputStream out = null;
        String color[]= {"","snow","lightSalmon1","orange","violet","purple","brown","grey",
                "blue","green","red","black","navy","gold","pink","yellow","khaki",
                "tomato","aquamarine1","tan2","maroon1"};
        int n = 0; // so dinh cua do thi
        int m = 0; // so canh cua do thi
        int[][] tree = new int[m+1][2]; // mang luu cac canh cua cay
        try(Scanner sc = new Scanner(fin)){
            n = sc.nextInt();
            m = sc.nextInt();
            tree = new int[m+1][2]; // khoi tao mang luu cac canh cua cay
            int i = 1;
            while(sc.hasNext()){
                tree[i][0]= sc.nextInt();
                tree[i][1]= sc.nextInt();
                i++;
            }
        }catch (Exception e){
        }
        int degree[] = new int[n+1]; // mang luu bac cua dinh
        int list[][] = new int[n+1][]; // mang luu danh sach ke
        for (int i = 1; i < tree.length ; i++) { // tinh bac cua cac dinh
            int tmp[] = tree[i];
            degree[tmp[0]] += 1;
            degree[tmp[1]] += 1;
        }
        for (int i = 1; i < list.length; i++) { //  luu danh sach canh vao mang list
            list[i] = new int[degree[i]];
            int temp = 0;
            for (int j = 1; j < tree.length ; j++) {
                if(tree[j][0] == i){
                    list[i][temp] = tree[j][1];
                    temp += 1;
                }else if(tree[j][1] == i){
                    list[i][temp] = tree[j][0];
                    temp += 1;
                }
            }
        }

        int painted[] = new int[n+1]; // mang luu mau cua cac dinh
        painted[1] = 1;
        for (int i = 2; i <= n ; i++) { // to mau
            boolean cantPaint[] = new boolean[21]; // luu nhung mau khong the to
            for (int j = 1; j <= 20 ; j++) {// tim xem nhung mau nao khong the to
                for (int k = 0; k <list[i].length ; k++) {
                    if (painted[list[i][k]] == j) cantPaint[j] = true;
                }
            }
            int j = 1;
            while(painted[i] == 0){ // neu dinh chua duoc to mau thi thuc hien to mau
                if(!cantPaint[j]) painted[i] = j;
                else j++;
            }
        }

        try{
            out = new FileOutputStream(fout);
            String s1 = "graph dothi\n" +
                    "{\n";
            byte array1[] = s1.getBytes();
            out.write(array1);
            for (int i = 1; i < list.length ; i++) {
                String s = Integer.toString(i) +" [fillcolor="+color[painted[i]]+",style = filled];\n";
                byte v[] = s.getBytes();
                out.write(v);
            }
            for (int i = 1; i <  tree.length; i++) {
                String s = Integer.toString(tree[i][0]) +"--"+ Integer.toString(tree[i][1])+";\n";
                byte v[] = s.getBytes();
                out.write(v);
            }
            out.write('}');
        }catch (Exception e){
            System.out.println(e);
        }finally {
            out.close();
            System.out.println("success...");
        }
    }
}
