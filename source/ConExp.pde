class ConExp{
ArrayList<Explosion> ast ;


ConExp(){
ast= new ArrayList<Explosion>();

}


void addExp(int x,int y, PImage a){
  Explosion p = new Explosion(x,y,a);
ast.add(p);
}

 void moviendo(){
   ArrayList<Explosion> asr= new ArrayList<Explosion>();
 for(int i=0;i<ast.size();i++){
  Explosion f=ast.get(i);
  if(f.cont==4){
   asr.add(f);
   
     } 
    f.dibujar();
  f.avanzar();

     }

 
 ast.removeAll(asr);
 }




}