//Mateusz Jachna - 3

import java.util.Scanner;

public class Source {

    static int[][] data;
    static int[] heap;
    static int heapSize;
    static int[] indexOfStart;

    static void push(int in) {
        heap[heapSize] = in;

        int j = heapSize, i = (j - 1) / 2;
        int tmp = heap[j];
        while (j > 0 && data[heap[i]][indexOfStart[heap[i]] - 1]
                > data[tmp][indexOfStart[tmp] - 1]) {
            heap[j] = heap[i];
            j = i;
            i = (i - 1) / 2;
        }
        heap[j] = tmp;
        heapSize++;
    }

    static int pop() {
        int root = heap[0];
        heap[0] = heap[--heapSize];
        down(0);

        return root;
    }

    static void down(int i) {
        int j;
        int tmp = heap[i];

        while (i < (heapSize / 2)) {
            j = 2 * i + 1;
            if (j < heapSize - 1
                    && data[heap[j]][indexOfStart[heap[j]] - 1]
                    >= data[heap[j + 1]][indexOfStart[heap[j + 1]] - 1]) {
                j++;
            }
            if (data[tmp][indexOfStart[tmp] - 1]
                    < data[heap[j]][indexOfStart[heap[j]] - 1]) {
                break;
            }
            heap[i] = heap[j];
            i = j;
        }
        heap[i] = tmp;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ilosc_zestawow = in.nextInt();

        while (ilosc_zestawow-- > 0) {
            int N = in.nextInt();
            indexOfStart = new int[N];
            heap = new int[N];
            heapSize = 0;
            data = new int[N][];
            for (int i = 0; i < N; i++) {
                data[i] = new int[in.nextInt()];
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = in.nextInt();
                }
            }
            for (int i = 0; i < data.length; i++) {
                indexOfStart[i] = 0;
                if (data[i].length > 0) {
                    indexOfStart[i] = 1;
                    push(i);
                }
            }
            while (heapSize != 0) {
                int tmp = pop();
                System.out.print(data[tmp][indexOfStart[tmp] - 1] + " ");
                if (data[tmp].length > indexOfStart[tmp]) {
                    indexOfStart[tmp]++;
                    push(tmp);
                }
            }
            System.out.println();
        }
    }
}
