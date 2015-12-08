import java.util.Scanner;

public class Source {

    // k - szukany element :)
    static int select(int tab[], int k) {
        //1.
        if (tab.length < 5) {
            bubbleSortMed(tab, 0, tab.length);

            if(k==0) return tab[0];
            return tab[k - 1];
        } else {
            //2.
            int koniec = 4, poczatek = 0, i = (int) Math.ceil(tab.length / 5);
            int med[] = new int[i];
            for (i = 0; i < med.length; i++) {
                if (koniec > tab.length) {
                    koniec = tab.length;
                }
                if (poczatek >= koniec) {
                    break;
                }
                med[i] = bubbleSortMed(tab, poczatek, koniec);
                poczatek = koniec + 1;
                koniec += 5;
            }

            int m5 = select(med, med.length / 2);
            int mniejsze = 0, wieksze = 0, rowne = 0;
            for (i = 0; i < tab.length; i++) {
                if (tab[i] < m5) {
                    mniejsze++;
                } else if (tab[i] > m5) {
                    wieksze++;
                } else {
                    rowne++;
                }
            }
            if (mniejsze >= k) {
                i = 0;
                int tmp[] = new int[mniejsze];
                for (int j = 0; j < tab.length; j++) {
                    if (tab[j] < m5) {
                        tmp[i] = tab[j];
                        i++;
                    }
                }
                return select(tmp, k);
            } else if (mniejsze + rowne >= k) {
                return m5;
            } else {
                i = 0;
                int tmp[] = new int[wieksze];
                for (int j = 0; j < tab.length; j++) {
                    if (tab[j] > m5) {
                        tmp[i] = tab[j];
                        i++;
                    }
                }
                return select(tmp, k - mniejsze - rowne);
            }
        }
    }

    static int bubbleSortMed(int tab[], int p, int k) {
        for (int i = p; i < k; i++) {
            for (int j = p; j < k - 1; j++) {
                if (tab[i] < tab[j]) {
                    int temp = tab[i];
                    tab[i] = tab[j];
                    tab[j] = temp;
                }
            }
        }
        return tab[p + ((k - p) / 2)];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ilosc_zestawow = in.nextInt();
        while (ilosc_zestawow-- > 0) {
            int size = in.nextInt();
            int tab[] = new int[size];
            for (int i = 0; i < size; i++) {
                tab[i] = in.nextInt();
            }
            size = in.nextInt();
            int szukana;
            for (int i = 0; i < size; i++) {
                szukana = in.nextInt();
                int a = select(tab, szukana);
                System.out.println(szukana + " " + a);
            }
        }
    }
}
