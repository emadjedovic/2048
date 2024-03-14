# 2048

Implementacija popularne igre 2048 u programskom jeziku Java.

## Pokretanje

Za pokretanje aplikacije potrebno je imati odgovarajuće razvojno okruženje (IDE): Eclipse, IntelliJ IDEA, NetBeans.

1. Otvorite folder ema2048 u vašem odabranom IDE-u.
2. Za GUI verziju igre pokrenite klasu `MainGUI.java` (package "main").
3. Za konzolnu verziju igre pokrenite klasu "MainKonzola.java" (package "main").

## Pravila

2048 je slagalica koja se igra na ploči veličine 4x4, gdje se igrač trudi kombinirati pločice s istim brojevima kako bi dobio pločicu s dvostrukim brojem.

1. Igra se na ploči od 4x4 polja, gdje su polja prazna ili sadrže pločice s brojevima.
2. Pločice s brojevima mogu biti potencije broja 2 (2, 4, 8, 16, 32, itd.).
3. Pločice se mogu pomicati prema gore, dolje, lijevo ili desno. Sve pločice na ploči pomaknut će se u odabrani smjer dok ne dođu do kraja ploče ili se spoje s drugom pločicom istog broja.
4. Ako se dvije pločice istog broja sudare prilikom pomicanja, spojit će se u jednu pločicu s dvostrukim brojem. Na primjer, pločice 2 i 2 spajaju se u pločicu 4.
5. Cilj je dobiti pločicu s brojem 2048, ali igra se može nastaviti i nakon toga.

## Autor

Ema Djedović

Profesor: Dr Damir Hasić

Asistent: Mr Vedad Letić

Odsjek za matematičke i kompjuterske nauke - smjer Kompjuterske nauke
PMF Sarajevo

02/2024
