//Mateusz Jachna - 3
//znalezc liczbe dodawan i liczbe roznych rozwiazan
// wypisujemy takie rozwiazanie ktore ma najmniej elementow
import java.util.Scanner;

class Source{
    static class Element{
        Element ()
        {
            x1=0;
            x2=0;
            y1=0;
            y2=0;
            ilosc_elementow=0;
            maxi=0;
        }
        int x1,x2,y1,y2;
        int ilosc_elementow;
        int maxi;
        void wylicz()
        {
            int ile=0;
            ile=(x1-x1+1) * (y2-y1+1);
            if(ile<ilosc_elementow) ilosc_elementow = ile;
        }
    }
    static int dodawania;
    static int rozwiazania;
    static int kadane2D(int tab[][],int COL,int ROW){
        int MAX=0;
        dodawania=0;
        rozwiazania=0;
        Element rozwiazanie = new Element();
        Element temp = new Element();
        Element najmniej = new Element();
        for(int i=0;i<ROW;i++){
            int[]columnSum=new int[COL];
            for(int j=i;j<ROW;j++){
                for(int k=0;k<COL;k++){
                    columnSum[k]+=tab[j][k];
                    dodawania++;
                }
                temp=kadane(columnSum,rozwiazanie); // temp = rozwiazanie
                temp.wylicz();
                if(temp.ilosc_elementow<najmniej.ilosc_elementow)
                {
                    najmniej.ilosc_elementow = temp.ilosc_elementow;
                    najmniej.x1=temp.x1;
                    najmniej.x2=temp.x2;
                    najmniej.y1=temp.y1;
                    najmniej.y1=temp.y2;
                }
                if(temp.maxi<0)temp.maxi=0;
                if(temp.maxi==MAX) rozwiazania++;
                else if(temp.maxi>MAX){
                    MAX=temp.maxi;
                    rozwiazania=1;
                }
            }
        }
        System.out.println("Ilosc dodawan: "+dodawania+" ilosc rozwiazan " + rozwiazania +
                " najmniejsza ilosc elementow "
                           +  najmniej.ilosc_elementow);
        System.out.println("Ich skrajne indeksy:" + najmniej.x1+" "+najmniej.y1+" "
                           + najmniej.x2+" "+najmniej.y2);
        return MAX;
    }
    static Element kadane(int tab[],Element rozwiazanie) {
        int suma;
        rozwiazanie.maxi=suma=0;
        for(int i=0;i<tab.length;i++){
           suma+=tab[i];
           rozwiazanie.y2=i;
           dodawania++;
           if (suma>rozwiazanie.maxi){rozwiazanie.maxi=suma;}
           else if (suma<0){suma=0;}
        }
        rozwiazanie.wylicz();
        return rozwiazanie;
    }
    public static void main(String args[]){
        int I,R,C;
        Scanner n=new Scanner(System.in);
        I=n.nextInt();
        while(I-->0){
            C=n.nextInt();
            R=n.nextInt();
            int tab[][] = new int[R][C];
            for(int i=0;i<C;i++)
                for(int j=0;j<R;j++)
                    tab[j][i]=n.nextInt();
            System.out.println(kadane2D(tab,C,R));
        }
    }
}
 