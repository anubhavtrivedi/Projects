#include<MFRC522.h>
#include<SPI.h>
#define SS_PIN 10
#define RST_PIN 9
int redLED= 4;
int yellowLED=3;
int greenLED=2;
int statusBit=10;
int received;
String uid;
/*    status bit values
 *     10    -   RESET
 *     1    -   Allow All
 *     2    -   Freeze System
*/
MFRC522 rfid(SS_PIN, RST_PIN);    //instance of class passing pins
MFRC522::MIFARE_Key key;
int code[]={228 ,186 ,101 ,152};
int codeRead=0;
String uidString;
int code1[]={165,144,213,101};
int code2[]={36,116,33,111};
void setup() {
  Serial.begin(9600);
  SPI.begin();  //Init spi bus
  rfid.PCD_Init();
  pinMode(4,OUTPUT);
  pinMode(3,OUTPUT);
  pinMode(2,OUTPUT);
}

void loop() {
 String text =Serial.readString();        //reading input from Serial COMM
 received=text.toInt();

 if(received !=0){
  statusBit=received;
 }

 digitalWrite(yellowLED,HIGH);
 if( rfid.PICC_IsNewCardPresent()){       //looking for Rfid card
  digitalWrite(yellowLED,LOW);
  readRFID();
 }
 sendUID();
}

void sendUID(){
 for(int j=0;j<20;j++){
  delay(10);
  Serial.println(uid);
 }
  
}

void readRFID(){
 rfid.PICC_ReadCardSerial();
 uid=String(rfid.uid.uidByte[0])+","+String(rfid.uid.uidByte[1])+","+String(rfid.uid.uidByte[2])+","+String(rfid.uid.uidByte[3]);
 int i=0;
 boolean match=true;
 while(i<rfid.uid.size)
 {
  if(!(rfid.uid.uidByte[i]==code[i])){
    match= false;
    break;
  }
  i++;
 }i=0;
 boolean match1=true;
  while(i<rfid.uid.size)
 {
  if(!(rfid.uid.uidByte[i]==code1[i])){
    match1= false;
    break;
  }
  i++;
 }i=0;
 boolean match2=true;
  while(i<rfid.uid.size)
 {
  if(!(rfid.uid.uidByte[i]==code2[i])){
    match2= false;
    break;
  }
  i++;
 }
 if(statusBit==1)
 {
  match=true;
 }
 if(statusBit==2)
 {
  match=match1=match2=false;
 }
 if(match==true||(match2==true||match1==true)){
  for(i=0;i<7;i++){
    delay(50);
    digitalWrite(greenLED,HIGH);
    delay(50);
    digitalWrite(greenLED,LOW);
    
  }
 }
 
 else{
  for(i=0;i<7;i++){
    delay(50);
    digitalWrite(redLED,HIGH);
    delay(50);
    digitalWrite(redLED,LOW);
    
  }
 }
}