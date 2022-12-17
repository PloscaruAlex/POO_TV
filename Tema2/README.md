# POO TV - Proiect1 - POO
Pentru implementarea acestui proiect am avut nevoie de cunostintele invatate
la cursurile si laboratoarele de POO. A fost un proiect greu, care mi s-a 
parut interesant.

Am inceput cu partea de I/O, mi-am creat cate o clasa pentru fiecare field
specific din fisierele de input. Pentru output, am creat singur fisierul
corespunzator rezultatelor care se crea si se stergea la fiecare test,
si pentru a vedea rezultatele fiecarui test am creat separat fisiere in
care scriam rezultatul pentru a ma ajuta la debugging, pe care le-am
sters ulterior.

Apoi am inceput implementarea efectiva a proiectului. Mi-am creat o clasa
care reprezinta conexiunea curenta a platformei, in care este administrata
aproape toata functionalitatea platformei. M-am gandit ca am nevoie doar
de o instanta a acestei clase asa ca am folosit design pattern-ul Singleton.

Pentru a administra toate paginile existente si pagina curenta pe care se afla
utilizatorul, am creat cate o clasa pentru fiecare pagina, cu atribute care
spun aplicatiei ce alte pagini pot fi accesate de pe cea curenta si ce actiuni
sunt permise. Am folosit design pattern-ul Factory pentru a putea schimba
pagina atunci cand este nevoie, deoarece toate implementeaza aceeasi clasa
abstracta si toate au aceleasi atribute. Atributele unei clase fiind 
constante, am implementat fiecare pagina sa fie de tip Singleton.

La fel am facut si pentru actiuni, am creat cate o clasa pentru fiecare
actiune specifica care implementeaza aceeasi clasa abstracta. Am folosit
si aici un factory pentru a stabili tipul fiecarei actiuni. Am creat de
fapt doua astfel de clase, deoarece trebuia sa fac diferenta intre
actiunile de tipul "change page" si "on page". Astfel, factory-ul principal
pentru actiuni creeaza ori actiunea "change page" ori apeleaza factory-ul care
se ocupa de actiunile de tip "on page".

Pe langa toate acestea, clasele Movie, User, Credentials sunt principalele
elemente ale platformei, OutputHelper si Constants sunt doar clase 
ajutatoare iar ContainsField, SortField si FiltersField sunt clasele 
necesare actiunii filter, pentru a putea citi si lucra corect cu valorile
din fisierele de input.

Am avut dificultati cu intelegerea unor cerinte deoarece cazurile de eroare nu
erau foarte bine explicate. Am mai intampinat dificultati si la generarea
corecta a fisierelor de output dar si la compararea rezultatelor mele cu
referintele.

Acest proiect m-a invatat multe lucruri, cel mai important fiind folosirea
design pattern-urilor dar si utilizarea complexa a claselor si obiectelor.