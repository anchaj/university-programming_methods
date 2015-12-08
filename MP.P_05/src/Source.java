//Mateusz Jachna - 3
import java.util.Scanner;
public class Source {
    static int tab[];
    static String temp = "";
    static public boolean plecak(int pojemnosc, int pocz) {
        boolean wartosc = false;
        if(pocz<tab.length-1){
            if(pojemnosc==0) {
                return true;
            }
            else if(tab[pocz]<=pojemnosc){
                wartosc = plecak(pojemnosc - tab[pocz],pocz+1);
            }
            if (wartosc == false) plecak(pojemnosc,pocz+1);
        }
        return wartosc;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int zestaw = in.nextInt();
        while(zestaw-->0)
        {
            int pojemnosc = in.nextInt();
            int tmp = pojemnosc;
            int ilosc = in.nextInt();
            tab = new int[ilosc];
            for(int i=0;i<tab.length;i++)
               tab[i] = in.nextInt();
            //String wyjscie = plecak(pojemnosc,0);
            boolean cos = plecak(pojemnosc,0);
            if (cos == false) System.out.println("BRAK");
            else System.out.println(pojemnosc+" = "+temp);
        }
    }
}
