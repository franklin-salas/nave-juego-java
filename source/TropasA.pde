class TropasA{



ArrayList<Alien> al ;
PImage imgAst;
int cant;
int cantd;
TropasA(int cantidad , int d){
al= new ArrayList<Alien>();
cant=cantidad;
cantd=d;
}
void imagen(String astr){
  imgAst=loadImage(astr);


}

void addAst(){
for(int i=0;i<cant;i++){
 Alien f= new Alien(); 
 f.imagen(imgAst);
  al.add(f);
}
}
 void moviendo(){
   ArrayList<Alien> as= new ArrayList<Alien>();
 for(int i=0;i<al.size();i++){
  Alien f=al.get(i);
  if(f.posX<0){
   as.add(f);
     } 
  f.avanzar();
  f.ataques();
  f.dibujar();
  f.cargarBala();
  
     }

 
 al.removeAll(as);
 }

boolean cargar(){


if(al.size()==0 && cantd>0){
addAst();
cantd--;
return true;
}
return false;
}
void cargar1(){


if(al.size()<5){
 Alien f= new Alien(); 
 f.imagen(imgAst);
  al.add(f);

}




}

}