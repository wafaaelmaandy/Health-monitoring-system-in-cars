#include <SoftwareSerial.h>
    SoftwareSerial b(8,9);

void setup() {
  // put your setup code here, to run once:
    b.begin(9600);
    Serial.begin(9600);
    pinMode(13, OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:
  while (b.available() > 0) {
    int a = analogRead(A0); 
    String  data = b.readString();
    if (data== "1") 
     b.println(a);
    }
    delay(1000);
  }

