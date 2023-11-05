
  
  
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

void setup() {
  size(900, 600);
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

void dispararAliens(){
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

void esenario() {
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
void draw() {
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
void keyPressed() {
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


void keyReleased() {
  n.teclaSotada(keyCode);
}

void ColisionesAstConBalas() {
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
void ColisionesAstConBombas() {
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

void ColisionesNaveConAst() {
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
 void ColisionesNaveConEstrella() { //************************************************************************************
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
  void ColisionesAlConBombas() {
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
  void ColisionesbalEnConBombas() {  //estrella
  ArrayList<Bomba> ba= new ArrayList<Bomba>();
  ArrayList<Bala> asr= new ArrayList<Bala>();
  for (Bomba b : n.bom) {
    for ( Bala a : estrella.balasd) {

      if (b.impactoAst( a.posX,int (a.yy))) {
         exp.addExp(a.posX, int (a.yy),imgExplo);
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
 void ColisionesBalEnConBalas() {////estrella
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
  void ColisionesNaveConBalasEnx() { // estrella
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
  void ColisionesEstrllaConBalas() {
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
  void ColisionesEstrellaConBombas() {
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
  void ColisionesbalConBombas() {
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

   void ColisionesBalConBalas() {
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
  
  void ColisionesAlConBalas() {
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
  void ColisionesNaveConBalas() {
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






  void ColisionesNaveConAl() {
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