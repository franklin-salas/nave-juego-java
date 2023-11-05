class Alien{

 int posX,posY;
  PImage imgNave;
  PImage imgbom;
  boolean adelan;
    boolean picada;
   int vida; 
  boolean arriba;

  int cantBala;
  int cargaBala;
  int cargabombas; 
 
   
Alien(){
posX=int(random(890,1800));
posY=int(random(-100,700));
adelan=true;

  arriba=false;
vida=4;
picada=false;

}
void imagen(PImage nave){
   imgNave=nave;
   

}


void dibujar(){
  imageMode(CENTER);
 image(imgNave,posX,posY);
 //ellipse(posX,posY,40,40);

}

void avanzar(){
  if(picada){
  if(n.posY<posY){posY-=3;}else{if(n.posY<posY){posY+=3;}  }
  
  
  posX-=3;
  
  }else{

  if(adelan){
 posX-=3;
 }

if(posY<0  ){
  arriba=false;
}
if(posY>600  ){
  arriba=true;
}
//--------------------------
  if(arriba){
 posY-=3;
 }else{
   posY+=3;
 }

if(n.posX-posX>100){
adelan=true;
}}


 

}
void cargarBala(){
  int coin = int(random(0,300));
  if(coin==5){
Bala d = new Bala(posX,posY);
baA.add(d);}
}


  

  
void ataques(){
  int coin = int(random(0, 1000));
    if (coin ==100) {
      if(n.posY<posY){
        arriba = true;
    }else{arriba=false;}
       

    }
if(coin<5){
     float dist = sqrt(pow(n.posX-posX, 2)+pow(n.posY-posY, 2));
      if (dist<500) {
        adelan=false;
  }} 
   int coin2 = int(random(0, 100));
  
 if(coin2 ==10 && !adelan ){
 picada =true;
 
 } 

}
















}