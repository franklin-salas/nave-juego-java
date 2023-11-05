class CAsteroide{
ArrayList<Asteroide> ast ;
PImage imgAst;
int cant;
int cantd;
CAsteroide(int cantidad , int d){
ast= new ArrayList<Asteroide>();
cant=cantidad;
cantd=d;
}
void imagen(String astr){
  imgAst=loadImage(astr);


}

void addAst(){
for(int i=0;i<cant;i++){
  Asteroide f= new Asteroide(int(random(0,601))); 
 f.imagen(imgAst);
  ast.add(f);
}
}
 void moviendo(){
   ArrayList<Asteroide> asr= new ArrayList<Asteroide>();
 for(int i=0;i<ast.size();i++){
  Asteroide f=ast.get(i);
  if(f.posX<0){
   asr.add(f);
   
     } 
  f.avanzar();
  f.dibujar();
     }

 
 ast.removeAll(asr);
 }

boolean cargar(){


if(ast.size()==0 && cantd>0){
addAst();
cantd--;
return true;
}
return false;
}


}