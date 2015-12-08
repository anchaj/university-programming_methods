//Mateusz Jachna
import java.util.Scanner;
public class Source {

    static long temp;
    static long tab[];

    private static int partition(int left, int right) {
        int i = (left + right) / 2, j;
        long piwot = tab[i];
        tab[i] = tab[right];
        for (j = i = left; i < right; i++) {
            if (tab[i] < piwot) {
                //swap(tab[i], tab[j]);
                temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                j++;
            }
        }
        tab[right] = tab[j];
        tab[j] = piwot;
        return j;
    }

    private static void quickSort() {
        int licznik = 0, l = 0, r = tab.length - 1;
        while (licznik > 0 || l < r) {
            if (l < r) {
                ++licznik;
                int x = partition(l, r);
                tab[r] = -tab[r];
                r = x - 1;
            } else {
                l = r + 2;
                r = l;
                while (r < tab.length) {
                    if (tab[r] >= 0) {
                        r++;
                    } else {
                        tab[r] = -tab[r];
                        break;
                    }
                }
                --licznik;
            }
        }
    }

    private static void quickSort2() {
        int left = 0, right = tab.length - 1;
        while (left < tab.length - 1) {
            temp = tab[(left + right) / 2];
            tab[(left + right) / 2] = tab[right];
            tab[right] = temp;
            long x = tab[right];
            int l = left - 1, r = right;
            while (l <= r) {
                while (tab[++l] < x) ;
                while (r > l && tab[--r] > x) ;
                if (l >= r) {
                    break;
                } else {
                    //swap(tab, l, r);
                    temp = tab[l];
                    tab[l] = tab[r];
                    tab[r] = temp;
                }
            }
            //swap(tab, l, right);
            temp = tab[l];
            tab[l] = tab[right];
            tab[right] = temp;
            if (++l < tab.length) {
                tab[l] = -tab[l];
            }
            right = l - 2;
            if (right <= left) {
                while (left < tab.length && tab[left] > 0) {
                    ++left;
                }
                if (left < tab.length) {
                    tab[left] = -tab[left];
                    right = left + 1;
                    while (right < tab.length - 1 && tab[right] > 0) {
                        ++right;
                    }
                    if (right < tab.length - 1) {
                        tab[right] = -tab[right];
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ilosc_testow = in.nextInt(), size;
        while (ilosc_testow-- > 0) {
            size = in.nextInt();
            tab = new long[size];
            boolean sort = false;
            for (int i = 0; i < tab.length; i++) {
                tab[i] = in.nextInt();
                if (!sort && tab[0] == tab[i]) {
                    sort = true;
                }
            }
            
            if (sort) {
                if(Math.random()<0.5)quickSort2();
                else quickSort();
            }
            for (int i = 0; i < tab.length; i++) {
                if (tab[i] > 0) {
                    System.out.print(tab[i] + " ");
                } else {
                    System.out.print(-tab[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
