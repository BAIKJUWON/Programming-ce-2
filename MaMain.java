package njk1;

import java.util.*;

public class MaMain {
    public static void main(String[] args) {
        int a[] = {40, 20, 60, 30, 10, 25};
        
        try {
            Btree bt = new Btree();
            
            for (int i = 0; i < a.length; i++) // i++ 수정
                bt.insert(a[i]);
                
            Node result = bt.search(30);
            if (result != null) {
                System.out.println(result.data);
            } else {
                System.out.println("값이 없습니다.");
            }
            
        } catch (IndexOutOfBoundsException e) {
            System.out.println("exception 1 발생!!");
            e.printStackTrace();
        } catch (NullPointerException s) {
            System.out.println("exception 2 발생!!");
        }
    }
}

class Btree {
    Node root;
    
    Btree() {
        root = null;
    }
    
    Node search(int b) {
        Node ptr = root;
        while (ptr != null && ptr.data != b) {
            if (ptr.data > b) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        return ptr;
    }

    void insert(int b) {
        Node ptr = root;
        Node parent = null;
        
        // 루트가 없는 경우 첫 삽입
        if (root == null) {
            root = new Node(b);
            return;
        }
        
        // 삽입 위치를 찾기
        while (ptr != null) {
            parent = ptr;
            if (ptr.data == b) {
                System.out.println("기존에 있는 자료 삽입하므로 실패!!");
            } else if (ptr.data > b) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

        // 새 노드 삽입
        if (parent.data > b) {
            parent.left = new Node(b);
        } else {
            parent.right = new Node(b);
        }
    }
}

class Node {
    int data;
    Node left, right;
    
    Node(int b) {
        data = b;
        left = right = null;
    }
}
