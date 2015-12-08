//Mateusz Jachna - 3
import java.util.Scanner;

class binaryTree {

    int value;
    binaryTree left, right;

    binaryTree(int value) {
        this.value = value;
    }
}

public class Source {
    
    static int pOrder[], inOrder[];

    static void printPostOrder(binaryTree n,StringBuilder out) {
        if (n != null) {
            printPostOrder(n.left,out);
            printPostOrder(n.right,out);
            //System.out.print(n.value + " ");
            out.append(n.value);
            out.append(" ");
        }
    }

    static void printPreOrder(binaryTree n,StringBuilder out) {
        if (n != null) {
            //System.out.print(n.value + " ");
            out.append(n.value);
            out.append(" ");
            printPreOrder(n.left,out);
            printPreOrder(n.right,out);
        }
    }

    static binaryTree toPostOrder(int i, int start, int end) {
        if(start >= end)
            return null;
        
        binaryTree n = new binaryTree(pOrder[i]);
        int j=0;
        while(inOrder[j] != pOrder[i])
            ++j;
        
        n.left = toPostOrder(i+1, start, j);
        n.right = toPostOrder(i+j+1-start, j+1, end);
        
        return n;
    }

    static binaryTree toPreOrder(int i, int start, int end) {
        if(end == 0)
            return null;
        
        binaryTree n = new binaryTree(pOrder[i]);
        int j = 0;
        int k = start;
        while (k>=0 && inOrder[k] != n.value)
        {
            ++j;
            --k;
        }
        
        n.left = toPreOrder(i-j-1, start-j-1,end-(j+1));
        n.right = toPreOrder(i-1, start, j);
        return n;
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int ilosc_zestawow = in.nextInt();
        while (ilosc_zestawow-- > 0) {
            StringBuilder out = new StringBuilder(50);
            int size = in.nextInt();
            pOrder = new int[size];
            
            String we = in.next();
            for (int i = 0; i < size; i++) {
                pOrder[i] = in.nextInt();
            }
            in.next();
            inOrder = new int[size];
            for (int i = 0; i < size; i++) {
                inOrder[i] = in.nextInt();
            }
            if (we.equals("PREORDER")) {
                binaryTree n = toPostOrder(0, 0, size);
                printPostOrder(n,out);
                System.out.println(out);
            } else {
                binaryTree n = toPreOrder(size - 1, size - 1, size);
                printPreOrder(n,out);
                System.out.println(out);
            }
        }
    }
}
