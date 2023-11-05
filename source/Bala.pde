class Bala {
  int posX;
  int posY;
   ///PImage imgAst;
  float vely;
  float yy;
  

Bala(int x,int y  ){
posX=x;
posY=y;
vely=0;
yy=y;
}

void dibujar(){
  strokeWeight(8);
 stroke(100,150,255);
ellipse(posX,posY,20,12);
}
void dibujar2(){
  strokeWeight(6);
 stroke(70,200,100);
ellipse(posX,posY,15,8);
}
void dibujar3(){
  strokeWeight(6);
 stroke(255,0,0);
ellipse(posX,yy,15,8);
}

void dirbala(float x, float y){
if(posY<y){
x=x-posX;
y=y-posY;
vely=y/x;
vely=vely*-1;
}else{
if(posY>y){
y=posY-y;
x=x-posX;
vely=(y/x);

}
}

}


void avanzar(){
 posX+=5;

 
 

}
void avanzarA(){
 posY--;

}
void avanzarB(){
 posX-=4;
 if(vely !=0){
 yy=yy+(vely*4);
 }
}





}