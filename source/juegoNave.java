import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class juegoNave extends PApplet {


  
  
//import ddf.minim.*;
//Minim minim;
//Minim minim1;
//import processing.sound.*;
//SoundFile player;
//SoundFile player1;
//AudioPlayer player;
//AudioPlayer player1;
//AudioInput input;

int x=0;
int x1 =1066;
int tiempo=0;
int tmusica=0;
int ved =0;
ArrayList<Bala> baA= new ArrayList<Bala>();
Enemigo estrella = new Enemigo();
Nave n = new Nave();
CAsteroide astr = new CAsteroide(20, 5);
ConExp  exp = new ConExp();
TropasA  aliens = new TropasA(10,4);

int vida=6;
boolean vivo=true;
boolean inicio= false;
PImage imginicio;
PImage imgEsp;
PImage imgEsp0; 
PImage imgExplo;
PImage imgFinal;
PImage imgComp;
PImage imgEstrell;
int faces=1;

public void setup() {
  
  imgEsp= loadImage("espacio.jpg");
  imgEsp0= loadImage("espacio0.jpg");
  imginicio= loadImage("inicio.jpg");
  imgExplo= loadImage("explocion.png");
  imgFinal= loadImage("final.jpg");
  imgEstrell=loadImage("estrella.png");
  imgComp=loadImage("complet.png");
  
  image(imgEsp, 0, 0, 1066, 600);
 noStroke();
  n.imagen("nave.gif","nave1.gif", "bomba.gif");
  astr.imagen("cometa.png");
 estrella.imagen(imgEstrell);
  aliens.imagen("navea.gif");
  //minim1 =new Minim(this);
  //minim = new Minim(this);
  //player = minim.loadFile("01-the military.mp3");
  // player = new SoundFile(this, "/data/01-the-military.mp3");
   //player1= minim.loadFile("12-assault them.mp3");
    //player1 = new SoundFile(this,"/data/12-assault-them.mp3");
 // input = minim.getLineIn();
   //player.play();
  
}

public void dispararAliens(){
  ArrayList<Bala> ba= new ArrayList<Bala>();
for(int i=0; i<baA.size();i++){
  Bala x = baA.get(i);
x.avanzarB();
x.dibujar2();
  if(x.posX>890){
ba.add(x);
}
}
baA.removeAll(ba);

}

public void esenario() {
  if (x==-1066) {
    x=1066;
  }
  if (x1==-1066) {
    x1=1066;
  }
  
    imageMode(CORNER);
    image(imgEsp, x, 0, 1066, 600);
    //int x1= 1066-x;
    image(imgEsp0, x1, 0, 1066, 600);
  
    x--;
    x1--;
}

// 86 v 66 B
public void draw() {
  if (!inicio) {
    imageMode(CORNER);
    image(imginicio, 0, 0);
  } else {
    if(vida>0){
    esenario();
  
    int r = millis()-tmusica;
    if(r/1000>153 && ved==0){
    //player1.rewind();
  //  player1.loop();
   // player1.play();
    ved++;
    }
  //  -----------------------------------------
   
  astr.moviendo();
     
       
    if (vivo) { 
   
      if(faces==1){
      astr.cargar();
      if(astr.ast.size()==0 && !astr.cargar()){
      faces++;
      }
      
      }
      
   
        if(faces==2){
      aliens.cargar();
      
      if(aliens.al.size()==0 && !aliens.cargar()){
      faces++;
      aliens.cant=5;
      }
      
      }
      
      if(faces==3){
        if(estrella.vida>0){
      aliens.cargar1();  
       estrella.dibujar();
       estrella.avanzar();
       estrella.cargarBala();
       estrella.disparar();
      ColisionesNaveConEstrella();
      ColisionesEstrllaConBalas();
      ColisionesEstrellaConBombas() ;}else{
      if(aliens.al.size()==0){
      faces++;}}
      }
      
        
      
      
      n.dibujar();
 n.avanzar(); 

    ColisionesNaveConAst();
     ColisionesNaveConAl();
       ColisionesNaveConBalas();
 ColisionesNaveConBalasEnx();    
      
    }else{ 
       if(faces==3){
         if(estrella.vida>0){
       aliens.cargar1();
       estrella.dibujar();
       estrella.avanzar();
   ColisionesEstrllaConBalas(); 
   ColisionesEstrellaConBombas() ;
    estrella.cargarBala();
       estrella.disparar();}
   }
    int total = millis() - tiempo;
    if(total/1000<2){
     n.dibujar1();
      n.avanzar();
     
     
    }else{vivo=true;}
    }
    
    n.disparar();
    n.bombardear();
   if(faces==4){imageMode(CENTER);
    image( imgComp, 450, 300);}
    aliens.moviendo();
       dispararAliens();
    ColisionesAstConBalas();
    ColisionesAstConBombas();
    ColisionesAlConBalas();
    ColisionesAlConBombas();
    ColisionesBalConBalas();
    ColisionesbalConBombas();
     exp.moviendo();
      ColisionesbalEnConBombas();
  ColisionesBalEnConBalas();



    fill(255);
    textSize(12);
    text(" Balas : "+n.cantBala, 100, 50);
    text(" Carga : "+n.cargaBala, 40, 50);
    text(" Bombas : "+n.cargabombas, 40, 80);
    text(" Vidas : "+vida, 130, 80);
    // print(n.ast.size());
  }else{
    imageMode(CENTER);
    image(imgFinal, 450, 300);
     if(vida==0){
   //  player1.close(); 
  // player1.stop();
   vida--;}
  
             }          }
}
public void keyPressed() {
  if (keyCode==10) {
    
    if(!inicio){
      //player.close();
    //  player1.loop(); 
   // player1.play();
     tmusica= millis();}
    inicio = true;
    
     
  }
  

   
      n.teclaApretada(keyCode); 
      print(keyCode);
   
  
}


public void keyReleased() {
  n.teclaSotada(keyCode);
}

public void ColisionesAstConBalas() {
  ArrayList<Bala> ba= new ArrayList<Bala>();
  ArrayList<Asteroide> asr= new ArrayList<Asteroide>();
  for (Bala b : n.balasd) {
    for ( Asteroide a : astr.ast) {
      float dist = sqrt(pow(b.posX-a.posX, 2)+pow(b.posY-a.posY, 2));
      if (dist<20) {
         exp.addExp(a.posX, a.posY,imgExplo);
      //  image(imgExplo, a.posX, a.posY);

        ba.add(b);

        asr.add(a);
      }
    }
  }

  n.balasd.removeAll(ba) ;
  astr.ast.removeAll(asr);
}
public void ColisionesAstConBombas() {
  ArrayList<Bomba> ba= new ArrayList<Bomba>();
  ArrayList<Asteroide> asr= new ArrayList<Asteroide>();
  for (Bomba b : n.bom) {
    for ( Asteroide a : astr.ast) {

      if (b.impactoAst( a.posX, a.posY)) {
         exp.addExp(a.posX, a.posY,imgExplo);
        //image(imgExplo, a.posX, a.posY);
        if (!b.b1&&!b.b2) {
          ba.add(b);
        }

        asr.add(a);
      }
    }
  }
  n.bom.removeAll(ba) ;
  astr.ast.removeAll(asr);
}

public void ColisionesNaveConAst() {
  // ArrayList<Asteroide> asr= new ArrayList<Asteroide>();
  for ( Asteroide a : astr.ast) {
    float dist = sqrt(pow(n.posX-a.posX, 2)+pow(n.posY-a.posY, 2));
    if (dist<40) {
        exp.addExp(n.posX, n.posY,imgExplo);
      vivo= false;
      vida--;
      tiempo = millis();
     n.CagarMuniciones();      //print("muerto");
      //asr.add(a);
    }
  }}
 public void ColisionesNaveConEstrella() { //************************************************************************************
  // ArrayList<Asteroide> asr= new ArrayList<Asteroide>();
 
    float dist = sqrt(pow(n.posX-estrella.x, 2)+pow(n.posY-estrella.y, 2));
    if (dist<155) {
        exp.addExp(n.posX, n.posY,imgExplo);
      vivo= false;
      vida--;
      tiempo = millis();
     n.CagarMuniciones();      //print("muerto");
      //asr.add(a);
    }
  }
  public void ColisionesAlConBombas() {
  ArrayList<Bomba> ba= new ArrayList<Bomba>();
  ArrayList<Alien> asr= new ArrayList<Alien>();
  for (Bomba b : n.bom) {
    for ( Alien a : aliens.al) {

      if (b.impactoAst( a.posX, a.posY)) {
         exp.addExp(a.posX, a.posY,imgExplo);
       // image(imgExplo, a.posX, a.posY);
        if (!b.b1&&!b.b2) {
          ba.add(b);
        }

        asr.add(a);
      }
    }
  }
  n.bom.removeAll(ba) ;
  aliens.al.removeAll(asr);
}
  public void ColisionesbalEnConBombas() {  //estrella
  ArrayList<Bomba> ba= new ArrayList<Bomba>();
  ArrayList<Bala> asr= new ArrayList<Bala>();
  for (Bomba b : n.bom) {
    for ( Bala a : estrella.balasd) {

      if (b.impactoAst( a.posX,PApplet.parseInt (a.yy))) {
         exp.addExp(a.posX, PApplet.parseInt (a.yy),imgExplo);
       // image(imgExplo, a.posX, a.posY);
        if (!b.b1&&!b.b2) {
          ba.add(b);
        }

        asr.add(a);
      }
    }
  }
  n.bom.removeAll(ba) ;
  estrella.balasd.removeAll(asr);
}
 public void ColisionesBalEnConBalas() {////estrella
  ArrayList<Bala> ba= new ArrayList<Bala>();
  ArrayList<Bala> asr= new ArrayList<Bala>();
  for (Bala b : n.balasd) {
    for (  Bala  a : estrella.balasd) {
      float dist = sqrt(pow(b.posX-a.posX, 2)+pow(b.posY-(int)a.yy, 2));
      if (dist<15) {
         exp.addExp(b.posX, b.posY,imgExplo);
       // image(imgExplo, a.posX, a.posY);
        asr.add(a);
         ba.add(b); 
      }
    }
  }
  n.balasd.removeAll(ba) ;
  estrella.balasd.removeAll(asr);
}
  public void ColisionesNaveConBalasEnx() { // estrella
  ArrayList<Bala> ba= new ArrayList<Bala>();
  for (Bala b : estrella.balasd) {
   
      float dist = sqrt(pow(b.posX-n.posX, 2)+pow(b.yy-n.posY, 2));
      if (dist<20) {
          exp.addExp(n.posX, n.posY,imgExplo);
        ba.add(b);
         vivo= false;
         vida--;
         tiempo = millis();
       n.CagarMuniciones(); 
      }
    
  }

  estrella.balasd.removeAll(ba) ;
  
}
  public void ColisionesEstrllaConBalas() {
  ArrayList<Bala> ba= new ArrayList<Bala>();
  for (Bala b : n.balasd) {
    
      float dist = sqrt(pow(b.posX-estrella.dx, 2)+pow(b.posY-estrella.dy, 2));
      if (dist<20) {      
           estrella.vida--;       
             exp.addExp((int)estrella.dx, (int)estrella.dy,imgExplo);
                  ba.add(b); 
      }
    
  }

  n.balasd.removeAll(ba) ;
  
}
  public void ColisionesEstrellaConBombas() {
  ArrayList<Bomba> ba= new ArrayList<Bomba>();
  for (Bomba b : n.bom) {
      if (b.impactoestrella( (int)estrella.dx, (int)estrella.dy)) {
         exp.addExp((int)estrella.dx,(int)estrella.dy,imgExplo);
        estrella.vida=  estrella.vida-15;
       // image(imgExplo, a.posX, a.posY);
        if (!b.b1&&!b.b2) {
          ba.add(b);
        }

     
      }
  }
  n.bom.removeAll(ba) ;
}
  public void ColisionesbalConBombas() {
  ArrayList<Bomba> ba= new ArrayList<Bomba>();
  ArrayList<Bala> asr= new ArrayList<Bala>();
  for (Bomba b : n.bom) {
    for ( Bala a : baA) {

      if (b.impactoAst( a.posX, a.posY)) {
         exp.addExp(a.posX, a.posY,imgExplo);
       // image(imgExplo, a.posX, a.posY);
        if (!b.b1&&!b.b2) {
          ba.add(b);
        }

        asr.add(a);
      }
    }
  }
  n.bom.removeAll(ba) ;
  baA.removeAll(asr);
}

   public void ColisionesBalConBalas() {
  ArrayList<Bala> ba= new ArrayList<Bala>();
  ArrayList<Bala> asr= new ArrayList<Bala>();
  for (Bala b : n.balasd) {
    for (  Bala  a : baA) {
      float dist = sqrt(pow(b.posX-a.posX, 2)+pow(b.posY-a.posY, 2));
      if (dist<15) {
         exp.addExp(a.posX, a.posY,imgExplo);
       // image(imgExplo, a.posX, a.posY);
        asr.add(a);
         ba.add(b); 
      }
    }
  }

  n.balasd.removeAll(ba) ;
  baA.removeAll(asr);
}
  
  public void ColisionesAlConBalas() {
  ArrayList<Bala> ba= new ArrayList<Bala>();
  ArrayList<Alien> asr= new ArrayList<Alien>();
  for (Bala b : n.balasd) {
    for (  Alien  a : aliens.al) {
      float dist = sqrt(pow(b.posX-a.posX, 2)+pow(b.posY-a.posY, 2));
      if (dist<20) {
          if(a.vida>0){
           a.vida--;
          } else{
             exp.addExp(a.posX, a.posY,imgExplo);
       // image(imgExplo, a.posX, a.posY);
        asr.add(a);
    }
    
              ba.add(b); 
      }
    }
  }

  n.balasd.removeAll(ba) ;
  aliens.al.removeAll(asr);
}
  public void ColisionesNaveConBalas() {
  ArrayList<Bala> ba= new ArrayList<Bala>();
  for (Bala b : baA) {
   
      float dist = sqrt(pow(b.posX-n.posX, 2)+pow(b.posY-n.posY, 2));
      if (dist<20) {
          exp.addExp(n.posX, n.posY,imgExplo);
        ba.add(b);
         vivo= false;
         vida--;
         tiempo = millis();
          n.CagarMuniciones(); 
      
      }
    
  }

  baA.removeAll(ba) ;
  
}






  public void ColisionesNaveConAl() {
  // ArrayList<Asteroide> asr= new ArrayList<Asteroide>();
  for ( Alien a : aliens.al) {
    float dist = sqrt(pow(n.posX-a.posX, 2)+pow(n.posY-a.posY, 2));
    if (dist<50) {
 exp.addExp(n.posX, n.posY,imgExplo);
      vivo= false;
      vida--;
      tiempo = millis();
     n.CagarMuniciones();      //print("muerto");
      //asr.add(a);
    }
  }
  //astr.ast.removeAll(asr);


////-----------------------------------






}
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
posX=PApplet.parseInt(random(890,1800));
posY=PApplet.parseInt(random(-100,700));
adelan=true;

  arriba=false;
vida=4;
picada=false;

}
public void imagen(PImage nave){
   imgNave=nave;
   

}


public void dibujar(){
  imageMode(CENTER);
 image(imgNave,posX,posY);
 //ellipse(posX,posY,40,40);

}

public void avanzar(){
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
public void cargarBala(){
  int coin = PApplet.parseInt(random(0,300));
  if(coin==5){
Bala d = new Bala(posX,posY);
baA.add(d);}
}


  

  
public void ataques(){
  int coin = PApplet.parseInt(random(0, 1000));
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
   int coin2 = PApplet.parseInt(random(0, 100));
  
 if(coin2 ==10 && !adelan ){
 picada =true;
 
 } 

}
















}


class Asteroide {
  int posX, posY;
  PImage imgAst;
  int velocidad;
  boolean ataq ;


  Asteroide(int y  ) {
    posX=PApplet.parseInt(random(885, 1500));
    posY=y;
    velocidad=PApplet.parseInt(random(3
    ,5));
    ataq=false;
  }
  public void imagen(PImage aste) {
    imgAst = aste;
  }
  public void dibujar() {
    imageMode(CENTER);
    image(imgAst, posX, posY);
  }

  public void avanzar() {
    if (ataq) {
      if (n.posY>posY) {   /// n variable de Nave
        posY=posY +3;    //// x = x + vel * cos(dir);
      } 
      if(n.posY<posY){
        posY-=3 ;
      }
       posX-=velocidad+1;
    } else {
      if (perseguir()) {
        if (n.posY>posY) {   /// n variable de Nave
          posY=posY +3;    //// x = x + vel * cos(dir);
          // y =
        } 
        if(n.posY<posY){
          posY-=3;
        }
      }
       posX-=velocidad;
    }

    //posX-=velocidad;
    ataqPicada();
  }
  public void acelerar() {
    velocidad++;
  }
  public void frenar() {
    velocidad--;
  }
  public boolean perseguir() {
    int coin = PApplet.parseInt(random(0, 800));
    if (coin >200 && coin<250) {
      return true;
    }
    return false;
  }
  public void ataqPicada() {
    int coin = PApplet.parseInt(random(0, 1000));
    if (coin ==100) {
      ataq= true;
    }
  }
}
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

public void dibujar(){
  strokeWeight(8);
 stroke(100,150,255);
ellipse(posX,posY,20,12);
}
public void dibujar2(){
  strokeWeight(6);
 stroke(70,200,100);
ellipse(posX,posY,15,8);
}
public void dibujar3(){
  strokeWeight(6);
 stroke(255,0,0);
ellipse(posX,yy,15,8);
}

public void dirbala(float x, float y){
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


public void avanzar(){
 posX+=5;

 
 

}
public void avanzarA(){
 posY--;

}
public void avanzarB(){
 posX-=4;
 if(vely !=0){
 yy=yy+(vely*4);
 }
}





}
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

public void imagen(PImage nb){
   imgbom= nb;
 //imgNave0=loadImage(nave0);

///imgNave.mask(imgNave0);

}
public void dibujar(){
  imageMode(CENTER);
  if(b1){
  image(imgbom,posX,posY-50);
  }
  
 if(b2){
 image(imgbom,posX,posY+50);}
}


public void avanzar(){
  posX+=disp;

}
public void impacto(int x1 , int y1){
  float dist = sqrt(pow(posX-x1,2)+pow(posY-50-y1,2));
  if(dist<40){
  b1=false;
  
  }
   float dist1 = sqrt(pow(posX-x1,2)+pow(posY+50-y1,2));
  if(dist1<40){
  b2=false;
  
  }



}

public boolean impactoAst(int x1 , int y1){
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

public boolean impactoestrella(int x1 , int y1){
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
class ConExp{
ArrayList<Explosion> ast ;


ConExp(){
ast= new ArrayList<Explosion>();

}


public void addExp(int x,int y, PImage a){
  Explosion p = new Explosion(x,y,a);
ast.add(p);
}

 public void moviendo(){
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
public void imagen(String astr){
  imgAst=loadImage(astr);


}

public void addAst(){
for(int i=0;i<cant;i++){
  Asteroide f= new Asteroide(PApplet.parseInt(random(0,601))); 
 f.imagen(imgAst);
  ast.add(f);
}
}
 public void moviendo(){
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

public boolean cargar(){


if(ast.size()==0 && cantd>0){
addAst();
cantd--;
return true;
}
return false;
}


}
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

public void dibujar(){
image(imgE,posX,posY);
}



public void avanzar(){
  if(cont==1){posX--;}
  if(cont==2){posX++;}
  if(cont==3){posY--;}
  if(cont==4){posY++;}
   cont++;
 

}


}
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
public void imagen(PImage nave){
   imgNave=nave;
   

}


public void dibujar(){
  imageMode(CENTER);
x=posX+radio*cos(dir);
y=posY+radio*sin(dir);
dx=x-30;
dy=y-70;
 image(imgNave,x,y);
//  ellipse(dx,dy,10,10);

}


public void cargarBala(){
  int coin = PApplet.parseInt(random(0,150));
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

public void avanzar(){
dir=dir+0.02f;
  
  
}


  public void disparar(){
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
public void imagen(String nave,String nave1,String imgbo){
   imgNave=loadImage(nave);
     imgNave1=loadImage(nave1);
   imgbom = loadImage(imgbo);
   CagarMuniciones();
 //imgNave0=loadImage(nave0);

///imgNave.mask(imgNave0);

}
public void CagarMuniciones(){
  cantBala=50;
   cargaBala=5;
   cargabombas=10;
}

public void dibujar(){
  imageMode(CENTER);
 image(imgNave,posX,posY);
 //ellipse(posX,posY,40,40);

}
public void dibujar1(){
  imageMode(CENTER);
 image(imgNave1,posX,posY);
 //ellipse(posX,posY,40,40);

}

public void avanzar(){
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

public void teclaApretada(int tecla){
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
  
  
public void teclaSotada(int tecla){
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
public boolean municiones(){
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
public boolean bombas(){
if(cargabombas>0){
  cargabombas--;
  return true;
}
return false;
}

public void disparar(){
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
public void bombardear(){
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
public void imagen(String astr){
  imgAst=loadImage(astr);


}

public void addAst(){
for(int i=0;i<cant;i++){
 Alien f= new Alien(); 
 f.imagen(imgAst);
  al.add(f);
}
}
 public void moviendo(){
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

public boolean cargar(){


if(al.size()==0 && cantd>0){
addAst();
cantd--;
return true;
}
return false;
}
public void cargar1(){


if(al.size()<5){
 Alien f= new Alien(); 
 f.imagen(imgAst);
  al.add(f);

}




}

}
  public void settings() {  size(900, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "juegoNave" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
