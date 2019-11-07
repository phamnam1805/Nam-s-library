/*
Bai tap do nuoc
*/



public class PourWater {
    static int n = 0;
    static int result = 0;
    static int didVisit = 0;
    public static void main(String[] args) {
        Node[] g = new Node[1000]; // mang g de luu dinh duoc sinh ra
        Node[] visited = new  Node[1000]; // mang luu cac dinh da di
        Node test = new Node(0,7,4,null); // so nuoc ban dau o cac binh 1 2 va 3. luong nuoc toi da o 3 binh lan luot la 10 7 4
        Try(g,visited,test);
        if(result == 0) System.out.println("Khong co ket qua nao");

    }
    static Node pourWater(Node before, int type){ // ham thuc hien do nuoc
        Node after = new Node();
        if(type == 1){ //binh 2 vao binh 1
            after.x = (before.x+before.y > 10)? 10 : before.x+before.y;
            after.y = (before.x+before.y > 10)? before.x+before.y-10 : 0;
            after.z = before.z;
        }else if(type == 2){ // binh 3 vao binh 1
            after.x = (before.x+before.z > 10)? 10: before.x+before.z;
            after.y = before.y;
            after.z = (before.x+before.z > 10)? before.x+before.z-10 : 0;
        }else if(type == 3){ // binh 1 vao binh 2
            after.x = (before.x+before.y > 7)? before.x+before.y-7 : 0;
            after.y = (before.x+before.y >= 7)? 7 : before.x+before.y;
            after.z = before.z;
        }else if(type == 4){ // binh 3 vao binh 2
            after.x = before.x;
            after.y = (before.y+before.z > 7)? 7: before.y+before.z;
            after.z = (before.y+before.z > 7)? before.y+before.z-7 : 0;
        }else if(type == 5){ // binh 1 vao binh 3
            after.x = (before.x+before.z > 4)? before.x+before.z-4 : 0;
            after.y = before.y;
            after.z = (before.z+before.x > 4)? 4 : before.x+before.z;
        }else if(type == 6){ // binh 2 vao binh 3
            after.x = before.x;
            after.y = (before.y+before.z > 4)? before.y+before.z-4 : 0;
            after.z = (before.y+before.z > 4)? 4 : before.y+before.z;
        }
        return after;
    }
    static boolean isExist(Node[] v,Node[] k,Node temp){ // kiem tra xem co ton tai trong mang v hay mang k hay chua, mang v la mang cach dinh duoc sinh ra, mang k la mang chua cac dinh da di het
        for (int i = 1; i <= n ; i++) {
            if(v[i].x == temp.x && v[i].y == temp.y && v[i].z == temp.z) return true;
        }
        if (didVisit >= 1){
            for (int i = 1; i <= didVisit ; i++) {
                if(k[i].x == temp.x && k[i].y == temp.y && k[i].z == temp.z) return true;
            }
        }
        return false;
    }
    static boolean isSolution(Node temp){ // kiem tra xem co la nghiem khong
        if(temp.y == 2 || temp.z == 2) return true;
        return false;
    }
    static void printResult(Node result){ // in ket qua
        System.out.println("Co ket qua");
        printReverse(result);
        result.show();
    }
    static void printReverse(Node v){ // dung de quy de in tu dinh parents den dinh con cuoi cung
        if(v.parents != null){
            printReverse(v.parents);
            v.parents.show();
        }
    }
    static void Try(Node[] v,Node[] k,Node temp){ // mang v la mang chua cac dinh duoc sinh ra, mang k la mang chua cac dinh da di qua
        if(!isExist(v,k,temp)){ // kiem tra xem da ton tai chua
            if(isSolution(temp)){ // kiem tra xem co la nghiem khong
                n = n+1;  // thuc hien dua temp vao trong mang v
                v[n] = temp;
                if (n > 1) v[n].parents = v[n-1]; // cho temp tro toi parents
                printResult(temp); // in ket qua
                result += 1; // so luong ket qua 
                didVisit += 1; // so dinh da di qua
                k[didVisit] = v[n]; // dua dinh temp vao trong mang k-mang chua cac dinh da di qua
                n -= 1;
                if(n==0) return; // neu tat ca cac dinh deu duoc di qua roi thi ket thuc ham

            } else {
                n = n+1;
                v[n] = temp;
                if (n > 1) v[n].parents = v[n-1];
                for (int i = 1; i <= 6 ; i++) { // thuc hien backtracking de thu het cac truong hop duong di co the
                    Node temp1 = new Node();
                    temp1 = pourWater(temp,i);
                    Try(v,k,temp1);
                }
                n = n-1;
                if(n == 0) return;
                didVisit += 1;
                k[didVisit] = v[n+1];
            }
        }
    }

}
class Node{
    int x; // nuoc trong binh 1
    int y; // nuoc trong binh 2
    int z; // nuoc trong binh 3
    Node parents; 

    public Node(int x, int y, int z, Node parents) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.parents = parents;
    }
    public Node() {
    }
    public void show(){
        System.out.println(x+" "+y+" "+z);
    }
}
