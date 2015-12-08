//Mateusz Jachna - 3

import java.util.Scanner;

public class Source {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int zestaw = s.nextInt();
        s.nextLine();
        for (int i = 0; i < zestaw; i++) {
            String wej = s.nextLine();

            String tab[] = new String[256];

            for (int x = 0; x < wej.length(); x++) {
                tab[x] = Character.toString(wej.charAt(x));
            }

            String TMP[] = new String[251];
            int l = 0;

            for (int j = 5; j < tab.length; j++) {
                if (tab[j] != null) {
                    if (tab[j].charAt(0) == ')' || tab[j].charAt(0) == '(' || tab[j].charAt(0) == '-' || tab[j].charAt(0) == '+' || tab[j].charAt(0) == '~' || tab[j].charAt(0) == '^' || tab[j].charAt(0) == '*' || tab[j].charAt(0) == '/' || (tab[j].charAt(0) >= 'a' && tab[j].charAt(0) <= 'z')) {
                        TMP[l] = tab[j];
                        l++;
                    }
                }
            }

            String wejscie[] = new String[l];
            System.arraycopy(TMP, 0, wejscie, 0, l);

            if (wej.charAt(1) == 'O') {
                ONPtoINF(wejscie);
            } else {
                INFtoONP(wejscie);
            }
        }
    }

    static int getPriority(String znak) {
        int priority = 0;
        if(znak == "") return -1;
        if (znak.equals("+") || znak.equals("-")) {
            priority = 1;
        } else if (znak.equals("*") || znak.equals("/")) {
            priority = 2;
        } else if (znak.equals("^")) {
            priority = 3;
        } else if (znak.equals("~")) {
            priority = 4;
        } else if (znak.charAt(0) >= 'a' && znak.charAt(0) <= 'z') {
            priority = 5;
        }
        return priority;
    }

    static void ONPtoINF(String[] wejscie) {
        stackArray stos = new stackArray(wejscie.length);
        stackArrayInt stosPriorytetow = new stackArrayInt(wejscie.length);

        String tmp = "";
        int operandy = 0, operatory = 0;

        for (int j = 0; j < wejscie.length; j++) {
            if (!wejscie[j].equals("(") && !wejscie[j].equals(")")) {
                if (wejscie[j].charAt(0) >= 'a' && wejscie[j].charAt(0) <= 'z') {
                    stos.push(wejscie[j]);
                    stosPriorytetow.push(getPriority(wejscie[j]));
                    operandy++;
                } else {
                    tmp = "";

                    if (!wejscie[j].equals("~")) {
                        operatory++;

                        if (stosPriorytetow.top() <= getPriority(wejscie[j])) {
                            tmp = "(" + stos.pop() + ")";
                        } else {
                            tmp = stos.pop();
                        }

                        stosPriorytetow.pop();

                        if (stosPriorytetow.top() < getPriority(wejscie[j])) {
                            tmp = "(" + stos.pop() + ")" + wejscie[j] + tmp;
                        } else {
                            tmp = stos.pop() + wejscie[j] + tmp;
                        }

                        stosPriorytetow.pop();
                    } else {
                        if (stosPriorytetow.top() <= getPriority(wejscie[j])) {
                            tmp = wejscie[j] + "(" + stos.pop() + ")";
                        } else {
                            tmp = wejscie[j] + stos.pop();
                        }

                        stosPriorytetow.pop();
                    }

                    stos.push(tmp);
                    stosPriorytetow.push(getPriority(wejscie[j]));
                }
            }
        }

        if (operandy - 1 == operatory) {
            System.out.println("<INF>" + stos.pop());
        } else {
            System.out.println("<INF>error");
        }
    }

    static void INFtoONP(String[] wejscie) {
        stackArray stos = new stackArray(wejscie.length);


        int iloscNawiasowOtwierajacych = 0;
        int iloscNawiasowZamykajacych = 0;
        int iloscOperandow = 0;
        int iloscOperatorow = 0;
        int otwarcie = 0;
        boolean error = false;


        String wyjscie = "";

        for (int j = 0; j < wejscie.length; j++) {
            if (wejscie[j].charAt(0) >= 'a' && wejscie[j].charAt(0) <= 'z')
            {
                iloscOperandow++;
                wyjscie = wyjscie + wejscie[j];
            } else if (wejscie[j].equals("+") || wejscie[j].equals("-") || wejscie[j].equals("/") || wejscie[j].equals("*") || wejscie[j].equals("^") || wejscie[j].equals("~")) {
                if (!wejscie[j].equals("~")) {
                    iloscOperatorow++;
                }

                int priorytetWejscia = -1;
                int priorytetStos = -1;
                priorytetStos = getPriority(stos.top());
                priorytetWejscia = getPriority(wejscie[j]);

                while (priorytetStos >= priorytetWejscia && !stos.isEmpty()) {
                    priorytetStos = -1;

                    if (stos.top().equals("+") || stos.top().equals("-") || stos.top().equals("/") || stos.top().equals("*") || stos.top().equals("^") || stos.top().equals("~")) {
                        wyjscie = wyjscie + stos.pop();
                    }
                    priorytetStos = getPriority(stos.top());
                }

                stos.push(wejscie[j]);
            } else if (wejscie[j].equals("(")) {
                iloscNawiasowOtwierajacych++;
                otwarcie++;
                stos.push(wejscie[j]);
            } else if (wejscie[j].equals(")"))
            {
                iloscNawiasowZamykajacych++;
                if (otwarcie > 0) {
                    otwarcie--;
                } else {
                    error = true;
                }

                while (stos.top().equals("+") || stos.top().equals("-") || stos.top().equals("/") || stos.top().equals("*") || stos.top().equals("^") || stos.top().equals("~")) {
                    wyjscie = wyjscie + stos.pop();
                }
                stos.pop(); 
            }
        }

        while (!stos.isEmpty()) {
            wyjscie = wyjscie + stos.pop();
        }

        if (iloscNawiasowZamykajacych == iloscNawiasowOtwierajacych && iloscOperandow - 1 == iloscOperatorow && !error) {
            System.out.println("<ONP>" + wyjscie);
        } else {
            System.out.println("<ONP>error");
        }
    }

}

class stackArray {

    private final int maxSize; // rozmiar tablicy zawierajÄcej stos 
    private final String[] Elem; // tablica zawierajÄca stos 
    public int top; // indeks szczytu stosu 

    public stackArray(int size) {
        maxSize = size; // ustawiamy rozmiar tablicy 

        Elem = new String[maxSize]; // tworzymy tablicÄ dla elementĂłw 
        top = maxSize; // na razie brak elementĂłw (stos roĹnie w gĂłrÄ) 
    }

    public void push(String x) {
        // wstawia element na szczyt stosu 
        if (!isFull()) {
            Elem[--top] = x; // zmniejszamy top, odkĹadamy element 
        }
    }

    public String pop() {
        // usuwa element ze szczytu stosu 
        if (isEmpty()) {
            return "";
        } else {
            return Elem[top++]; // pobieramy element i zwiÄkszamy top 
        }
    }

    public String top() {
        // zwraca wartoĹÄ na szczycie stosu 
        if (isEmpty()) {
            return "";
        } else {
            return Elem[top];
        }
    }

    public boolean isEmpty() {
        // zwraca true, jeĹźeli stos pusty 
        return (top == maxSize);
    }

    public boolean isFull() {
        //zwraca true, jeĹźeli stos peĹny 	 
        return (top == 0);
    }
}

class stackArrayInt {

    private final int maxSize; // rozmiar tablicy zawierajÄcej stos 
    private final int[] Elem; // tablica zawierajÄca stos 
    public int top; // indeks szczytu stosu 

    public stackArrayInt(int size) {
        maxSize = size; // ustawiamy rozmiar tablicy 

        Elem = new int[maxSize]; // tworzymy tablicÄ dla elementĂłw 
        top = maxSize; // na razie brak elementĂłw (stos roĹnie w gĂłrÄ) 
    }

    public void push(int x) {
        // wstawia element na szczyt stosu 
        if (!isFull()) {
            Elem[--top] = x; // zmniejszamy top, odkĹadamy element 
        }
    }

    public int pop() {
        // usuwa element ze szczytu stosu 
        if (isEmpty()) {
            return 0;
        } else {
            return Elem[top++]; // pobieramy element i zwiÄkszamy top 
        }
    }

    public int top() {
        // zwraca wartoĹÄ na szczycie stosu 
        if (isEmpty()) {
            return 0;
        } else {
            return Elem[top];
        }
    }

    public boolean isEmpty() {
        // zwraca true, jeĹźeli stos pusty 
        return (top == maxSize);
    }

    public boolean isFull() {
        //zwraca true, jeĹźeli stos peĹny 	 
        return (top == 0);
    }
}
