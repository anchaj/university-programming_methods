// Mateusz Jachna - 3

import java.util.Scanner;

class Wagon {

    String name;
    Wagon prev;
    Wagon next;

    Wagon(String name) {
        this.name = name;
    }

    Wagon(String name, Wagon pop, Wagon nast) {
        this.name = name;
        prev = pop;
        next = nast;
    }
}

class Pociag {

    String name;
    Pociag next;
    Wagon first;
    Wagon last;

    Pociag(String name, String name2) {
        this.name = name;
        Wagon head = new Wagon("");
        first = head;
        Wagon nowy = new Wagon(name2);
        last = nowy;

        head.prev = nowy;
        head.next = nowy;

        nowy.prev = head;
        nowy.next = head;
    }

    Pociag(String name) {
        this.name = name;
        Wagon head = new Wagon("");
        first = head;
        last = head;
    }
}

public class Source {
static Pociag tmp = new Pociag("");
    static class List {

        Pociag Top;

        Pociag findTrain(String name) {
            tmp = Top;
            while (name.equals(tmp.name) == false) {
                tmp = tmp.next;
            }
            return tmp;
        }

        void addNew(String name, String name2) {
            Pociag nowy = new Pociag(name, name2);
            nowy.next = Top;
            Top = nowy;
        }

        void InsertFirst(String name, String name2) {
            tmp = findTrain(name);
            Wagon nowy = new Wagon(name2, tmp.first, tmp.first.next);
            tmp.first.next = nowy;
            nowy.next.prev = nowy;
        }

        void InsertLast(String name, String name2) {
            tmp = findTrain(name);
            Wagon nowy = new Wagon(name2, tmp.last, tmp.first);
            tmp.first.prev = nowy;
            tmp.last.next = nowy;
            tmp.last = nowy;
        }

        void TrainsList() {
            tmp = Top;
            System.out.print("Trains:");
            while (tmp != null) {
                System.out.print(" " + tmp.name);
                tmp = tmp.next;
            }
            System.out.println();
        }

        void Reverse(String name) {
            Pociag tmp = findTrain(name);
            
            Wagon newEnd = tmp.first.next;
            tmp.first.prev = tmp.first.next;
            tmp.first.next = tmp.last;
            
            Wagon tmp2 = tmp.last.next;
            tmp.last.next = tmp.last.prev;
            tmp.last.prev = tmp2;
            
            tmp2 = newEnd.next;
            newEnd.next = newEnd.prev;
            newEnd.prev = tmp2;
            tmp.last = newEnd;
        }

        void Union(String name, String name2) {
            tmp = Top;
            Pociag T1 = null, T2 = null, przedT2 = null;
            while (tmp != null) {
                if (name2.equals(tmp.name)) {
                    T2 = new Pociag("");
                    T2 = tmp;
                } else if (name.equals(tmp.name)) {
                    T1 = new Pociag("");
                    T1 = tmp;
                }
                if (T1 != null && T2 != null) {
                    break;
                }
                if (tmp.next != null && name2.equals(tmp.next.name)) {
                    przedT2 = new Pociag("");
                    przedT2 = tmp;
                }
                tmp = tmp.next;
            }
            if (T2 == Top) {
                Top = Top.next;
            } else if (T2.next == null) {
                przedT2.next = null;
            } else if (T2.next == T1) {
                przedT2.next = T1;
            } else if (T1.next == T2) {
                T1.next = T2.next;
            } else {
                przedT2.next = T2.next;
            }
            T1.first.prev = T2.last;
            T2.last.next = T1.first;
            T1.last.next = T2.first.next;
            T1.last.next.prev = T1.last;
            T1.last = T2.last;
        }

        void Display(String name) {
            tmp = findTrain(name);
            System.out.print(tmp.name + ": ");
            Wagon one = tmp.first.next, two = tmp.first;
            while (one.name.compareTo("") != 0) {
                System.out.print(one.name + " ");
                if (two == one.prev) {
                    two = one;
                    one = one.next;
                } else {
                    two = one;
                    one = one.prev;
                }
            }
            System.out.println();
        }

        void DelFirst(String name, String name2) {
            Pociag T1 = Top, przedT1 = null;

            while (T1 != null) {
                if (T1.name.compareTo(name) == 0) {
                    break;
                }
                przedT1 = T1;
                T1 = T1.next;
            }
            
            if (T1.first == T1.last.prev) {
                if (przedT1 == null) {
                    Top = Top.next;
                } else {
                    przedT1.next = T1.next;
                }
                Pociag nowy = new Pociag(name2);
                nowy.first.next = T1.last;
                nowy.first.prev = T1.last;

                T1.last.prev = nowy.first;
                T1.last.next = nowy.first;
                nowy.last = nowy.first.next;
                nowy.next = Top;
                Top = nowy;
              /*if (przedT1 != null) {
                    przedT1.next = T1.next;
                    T1.name = name2;
                    T1.next = Top;
                    Top = T1;
                }
                else {
                    T1.name = name2;
                }*/
            } else {
                Pociag nowy = new Pociag(name2);
                nowy.next = Top;
                Top = nowy;
                //ustawienie nowy.head
                nowy.first.next = T1.first.next;
                nowy.first.prev = T1.first.next;
                nowy.last = T1.first.next;

                //ustawienie p1.head - to jest ok jezeli nie odwrocony inne sprawdzic
                T1.first.next.next.prev = T1.first;
                T1.first.next = T1.first.next.next;

                //ustawienie pierwszego wagonu z p1
                nowy.first.next.prev = nowy.first;
                nowy.first.next.next = nowy.first;
            }
        }

        void DelLast(String name, String name2) {
            Pociag T1 = Top, przedT1 = null;

            while (T1 != null) {
                if (T1.name.compareTo(name) == 0) {
                    break;
                }
                przedT1 = T1;
                T1 = T1.next;
            }
            
            if (T1.first == T1.last.prev) {
                if (przedT1 == null) {
                    Top = Top.next;
                } else {
                    przedT1.next = T1.next;
                }
                Pociag nowy = new Pociag(name2);
                nowy.first.next = T1.last;
                nowy.first.prev = T1.last;

                T1.last.prev = nowy.first;
                T1.last.next = nowy.first;
                nowy.last = nowy.first.next;
                nowy.next = Top;
                Top = nowy;
                
                /*if (przedT1 != null) { // nie jest na top-ie
                    przedT1.next = T1.next;
                    T1.name = name2;
                    T1.next = Top;
                    Top = T1;
                }
                else { // jest na top-ie
                    T1.name = name2;
                }*/

            } else {
                Pociag nowy = new Pociag(name2);
                nowy.next = Top;
                Top = nowy;
                nowy.first.next = T1.last;
                nowy.first.prev = T1.last;
                nowy.last = T1.last;

                //ustaniwanie p1.head
                T1.first.prev = T1.last.prev;
                T1.last.prev.next = T1.first;
                T1.last = T1.first.prev;

                // ustawianie ostatniego wagonu z p1
                nowy.last.next = nowy.first;
                nowy.last.prev = nowy.first;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ilosc_zestawow = in.nextInt();
        String polecenie;
        int ilosc_polecen;
        while (ilosc_zestawow-- > 0) {

            ilosc_polecen = in.nextInt();
            List l = new List();
            while (ilosc_polecen-- > 0) {
                polecenie = in.next();
                if (polecenie.compareTo("New") == 0) {
                    l.addNew(in.next(), in.next());
                } else if (polecenie.equals("InsertFirst")) {
                    l.InsertFirst(in.next(), in.next());
                } else if (polecenie.equals("InsertLast")) {
                    l.InsertLast(in.next(), in.next());
                } else if (polecenie.equals("Display")) {
                    l.Display(in.next());
                } else if (polecenie.equals("TrainsList")) {
                    l.TrainsList();
                } else if (polecenie.equals("Reverse")) {
                    l.Reverse(in.next());
                } else if (polecenie.equals("Union")) {
                    l.Union(in.next(), in.next());
                } else if (polecenie.equals("DelFirst")) {
                    l.DelFirst(in.next(), in.next());
                } else {
                    l.DelLast(in.next(), in.next());
                }
            }
        }
    }
}