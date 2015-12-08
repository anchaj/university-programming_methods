//Mateusz Jachna - 3
import java.util.Scanner;
public class Source {
public static Scanner in = new Scanner(System.in);
static int bin(int sz,int tab[]){
    int l=0,p=tab.length-1,s=1;
    while(l<=p){
        s=((l+p)>>1);
        if(tab[s] == sz){
            if(s+1<tab.length && tab[s+1]==sz){
                l=s+1;
                continue;
            }
            else break;
        }
        if(sz>tab[s])l=s+1;
        else p=s;
        if(l==p && l==s)break;
        }
    if(tab[s]>sz)--s;
    return s;
}
public static void main(String[] args){
    int t,n,k;
    t=in.nextInt();
    while(t-->0)
    {
        n=in.nextInt();
        int tab[] = new int[n];
        for(int i=0;i<n;i++)
            tab[i]=in.nextInt();
        k=in.nextInt();
        for(int j=0;j<k;j++)
        {
            int sz=in.nextInt();
            System.out.println(bin(sz,tab)-bin(sz-1,tab)+" ");
        }
    }
} 
}