class Explosion {
  int posX,posY;
   PImage imgE;
   int cont;
  

Explosion(int x,int y,PImage e  ){
posX=x;
posY=y;
imgE=e;
cont=1;
}

void dibujar(){
image(imgE,posX,posY);
}



void avanzar(){
  if(cont==1){posX--;}
  if(cont==2){posX++;}
  if(cont==3){posY--;}
  if(cont==4){posY++;}
   cont++;
 

}


}