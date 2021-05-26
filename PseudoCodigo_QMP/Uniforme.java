
//--------------UNIFORME--------------

class Uniforme {

    Prenda parteSup;
    Prenda parteInf;
    Prenda calzado;

    Uniforme(Prenda parteSuperior,Prenda parteInferior,Prenda calzado) {
        this.parteSuperior = parteSuperior;
        this.parteInferior = parteInferior;
        this.calzado = calzado;
    }

    static Uniforme fabricar(Sastre sastre)
       return new Uniforme(
          sastre.fabricarParteSuperior(),
          sastre.fabricarParteInferior(),
          sastre.fabricarCalzado())
}

interface Sastre {
    Prenda fabricarParteSuperior();
    Prenda fabricarParteInferior();
    Prenda fabricarCalzado();
}

class SastreSanJuan implements Sastre {
     Prenda fabricarParteSuperior() {
                borrador = new Borrador(Tipo.CHOMBA);
                borrador.especificarColor(new Color(....),Material.PIQUE);
                return borrador.crearPrenda()
     }           

    Prenda fabricarParteInferior() {
                borrador = new Borrador(Tipo.PANTALON)
                borrador.setAspectoPrincipal(new Color(....),Material.ACETATO)
                borrador.especificarMaterial(ACETATO)
                return borrador.crearPrenda()
    }

    Prenda fabricarCalzado(){
        ........
    }
}

//etc.
