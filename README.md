MEMS-Navigation
===============

Privater Branch um die Accerlerometer Daten, welcher ein Flyduino über einen virtuellen COM-Port an einen Raspberry Pi sendet,
auslesen soll. Der ursprüngliche Code wurde im Rahmen einer Projekteranstaltung an der HAW entwickelt und wird hier nun für den
Einsatz auf dem Raspberry Pi umgebaut. 
Ziel ist ein Wecker, welcher die Bewegungen in der Nacht aufzeichnet. Der Weckruf soll zu einem Zeitpunkt stattfinden, an dem
ein leichter Schlaf festgestellt wird. Die Daten sollen zunächst gespeichert werden. 
Zu einem späteren Zeitpunkt sollen die Daten an eine Android App gesendet werden. Im Gegensatz zu anderen Wecker-Apps wird erreicht, dass das Handy den Großteil der Nacht im Deep-Sleep Zustand läuft. 

RXTX-Bibliothek für Java:
http://mfizz.com/oss/rxtx-for-java

Flyduino
http://flyduino.net/Flyduino-Pro-Micro-16Mhz-5V-Schwarz-16mm-PCB

MEMS-Chip:
http://flyduino.net/MPU6050-Break-Out-onboard-33V-reg
