class Enemigo{
 float posX,posY,x,y;
  PImage imgNave;
  float radio;
   int vida;  
  float dir;
  float dx,dy;
   ArrayList<Bala> balasd ;
Enemigo(){
posX= 800;
posY=310;
vida=100;
dir=0;
radio=150;
balasd = new ArrayList<Bala>();
}
void imagen(PImage nave){
   imgNave=nave;
   

}


void dibujar(){
  imageMode(CENTER);
x=posX+radio*cos(dir);
y=posY+radio*sin(dir);
dx=x-30;
dy=y-70;
 image(imgNave,x,y);
//  ellipse(dx,dy,10,10);

}


void cargarBala(){
  int coin = int(random(0,150));
  if(coin==5){
Bala d = new Bala((int)x,(int)y);
Bala d2 = new Bala((int)x-30,(int)y-30);
Bala d3 = new Bala((int)x+30,(int)y+30);
d.dirbala(n.posX,n.posY);
d2.dirbala(n.posX,n.posY-20);
d3.dirbala(n.posX,n.posY+20);
balasd.add(d);
balasd.add(d2);
balasd.add(d3);
}
}

void avanzar(){
dir=dir+0.02;
  
  
}


  void disparar(){
  ArrayList<Bala> ba= new ArrayList<Bala>();
for(int i=0; i<balasd.size();i++){
  Bala x = balasd.get(i);
x.avanzarB();
x.dibujar3();
  if(x.posX<0){
ba.add(x);
}
}
balasd.removeAll(ba);

}

  
}