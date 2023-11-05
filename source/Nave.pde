class Nave{
  int posX,posY;
  PImage imgNave;
    PImage imgNave1;
  PImage imgbom;
  boolean adelan;
  boolean atras;
  boolean arriba;
  boolean abajo;
  int cantBala;
  int cargaBala;
  int cargabombas; 
  ArrayList<Bala> balasd ;
   ArrayList<Bomba> bom ;
   
Nave(){
posX=50;
posY=300;
adelan=false;
arriba =false;
  atras=false;
abajo=false;
balasd = new ArrayList<Bala>();
bom = new ArrayList<Bomba>();


}
void imagen(String nave,String nave1,String imgbo){
   imgNave=loadImage(nave);
     imgNave1=loadImage(nave1);
   imgbom = loadImage(imgbo);
   CagarMuniciones();
 //imgNave0=loadImage(nave0);

///imgNave.mask(imgNave0);

}
void CagarMuniciones(){
  cantBala=50;
   cargaBala=5;
   cargabombas=10;
}

void dibujar(){
  imageMode(CENTER);
 image(imgNave,posX,posY);
 //ellipse(posX,posY,40,40);

}
void dibujar1(){
  imageMode(CENTER);
 image(imgNave1,posX,posY);
 //ellipse(posX,posY,40,40);

}

void avanzar(){
  if(posX<20){
  atras=false;
  }
  if(posX>880){
    adelan=false;
  }
  if(posY<20){
  arriba=false;
  }
  if(posY>580){
  abajo=false;
  }
 
  
 if(adelan){
 posX+=4;
 }
 if(atras){
   posX-=4;
 }
  if(arriba){
 posY-=4;
 }
 if(abajo){
   posY+=4;
 }
 

}

void teclaApretada(int tecla){
  if(tecla==39 ){
  adelan=true;
  }
  if(tecla==37 ){
  atras=true;
  }
  if(tecla==38 ){
  arriba=true;
  }
   if(tecla==40){
  abajo=true;
  }
  if(tecla==86 && municiones()){
    Bala d = new Bala(posX+50,posY);
    
   balasd.add(d);
  }
  if(tecla==66 && bombas()){
  Bomba b = new Bomba(posX,posY);
  b.imagen(imgbom);
  bom.add(b);
  }
  
}
  
  
void teclaSotada(int tecla){
    if(tecla==39 ){
  adelan=false;
  }
  if(tecla==37 ){
  atras=false;
  }
   if(tecla==38){
  arriba=false;
  }
   if(tecla==40 ){
  abajo=false;
  }
}

//int cantBala=100;
  //int cargaBala=5;
  //int cragabombas=10;
boolean municiones(){
  if(cargaBala>0){
cantBala--;
if(cantBala==0){
cargaBala--;
if(cargaBala>0){
cantBala=50;}
} return true;
}
 return false; 
}
boolean bombas(){
if(cargabombas>0){
  cargabombas--;
  return true;
}
return false;
}

void disparar(){
  ArrayList<Bala> ba= new ArrayList<Bala>();
for(int i=0; i<balasd.size();i++){
  Bala x = balasd.get(i);
x.avanzar();
x.dibujar();
  if(x.posX>890){
ba.add(x);
}
}
balasd.removeAll(ba);

}
void bombardear(){
   ArrayList<Bomba> ba= new ArrayList<Bomba>();
for(int i=0; i<bom.size();i++){
  Bomba x = bom.get(i);
x.avanzar();
x.dibujar();
  if(x.posX>890){
ba.add(x);
}
}
balasd.removeAll(ba);


}


}