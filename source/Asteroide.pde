

class Asteroide {
  int posX, posY;
  PImage imgAst;
  int velocidad;
  boolean ataq ;


  Asteroide(int y  ) {
    posX=int(random(885, 1500));
    posY=y;
    velocidad=int(random(3
    ,5));
    ataq=false;
  }
  void imagen(PImage aste) {
    imgAst = aste;
  }
  void dibujar() {
    imageMode(CENTER);
    image(imgAst, posX, posY);
  }

  void avanzar() {
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
  void acelerar() {
    velocidad++;
  }
  void frenar() {
    velocidad--;
  }
  boolean perseguir() {
    int coin = int(random(0, 800));
    if (coin >200 && coin<250) {
      return true;
    }
    return false;
  }
  void ataqPicada() {
    int coin = int(random(0, 1000));
    if (coin ==100) {
      ataq= true;
    }
  }
}