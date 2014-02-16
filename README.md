tiralabra
=========

Ohjelma löytää lyhimmän polun tiedostoon tallennetusta tai generoidusta labyrintistä.
Toteutettujen algoritmien tehokkuutta voidaan testata benchmark-moodissa.


Kääntäminen:
------------
mvn package


Ajaminen:
---------
java -jar target/tiralabra-1.0-SNAPSHOT.jar


Komentoriviparametrit:
-----------
Benchmark algorithms     -b <width> <height> <freq> <iterations>
Generate random graph    -r <width> <height> <freq>
Solve                    -s <filename>
Solve random graph       -sr <width> <height> <freq>