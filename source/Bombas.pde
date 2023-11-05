class Bomba {
   int posX,posY;
   int disp;
   PImage imgbom;
   boolean b1;
   boolean b2;
   int impactob1;
   int impactob2;
   
  

Bomba(int x,int y  ){
posX=x;
posY=y;
disp=6;
b1=true;
b2=true;
  impactob1=0;
  impactob2=0;

}

void imagen(PImage nb){
   imgbom= nb;
 //imgNave0=loadImage(nave0);

///imgNave.mask(imgNave0);

}
void dibujar(){
  imageMode(CENTER);
  if(b1){
  image(imgbom,posX,posY-50);
  }
  
 if(b2){
 image(imgbom,posX,posY+50);}
}


void avanzar(){
  posX+=disp;

}
void impacto(int x1 , int y1){
  float dist = sqrt(pow(posX-x1,2)+pow(posY-50-y1,2));
  if(dist<40){
  b1=false;
  
  }
   float dist1 = sqrt(pow(posX-x1,2)+pow(posY+50-y1,2));
  if(dist1<40){
  b2=false;
  
  }



}

boolean impactoAst(int x1 , int y1){
  boolean choq=false;
  boolean choq1=false;
  if(b1){
  float dist = sqrt(pow(posX-x1,2)+pow(posY-50-y1,2));
  if(dist<40){
  impactob1++;
  if(impactob1>4){
  b1=false;
  }
  choq=true;
  }}
  if(b2){
   float dist1 = sqrt(pow(posX-x1,2)+pow(posY+50-y1,2));
  if(dist1<40){
 impactob2++;
  if(impactob2>4){
  b2=false;
  }
   choq1=true;
  }
  }

return  choq||choq1;
}

boolean impactoestrella(int x1 , int y1){
  boolean choq=false;
  boolean choq1=false;
  if(b1){
  float dist = sqrt(pow(posX-x1,2)+pow(posY-50-y1,2));
  if(dist<40){
  
  b1=false;

  choq=true;
  }}
  if(b2){
   float dist1 = sqrt(pow(posX-x1,2)+pow(posY+50-y1,2));
  if(dist1<40){
  b2=false;
  
   choq1=true;
  }
  }

return  choq||choq1;
}






}