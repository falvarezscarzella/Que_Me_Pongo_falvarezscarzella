
//---------------PRENDA-------------------//

clase Borrador{
    Tipo tipo;
    Trama trama = Trama.LISA;
    Material material;
    Color colorPrincipal;
    Color colorSecundario;

 Borrador(Tipo tipo){
        requireNotNull(tipo);
        this.tipo = tipo;
    }

    boolean materialValido(Material materialPrenda){
        return tipo.getMaterialesDisponibles().contains(materialPrenda);
    }

    //Setteo obligatorio (Color Principal y Material)

    void setAspectoPrincipal(Color colorPrincipalPrenda,Material materialPrenda){
        if(!materialValido(materialPrenda)){
            tirar excepcionMaterialInvalido("El material no esta disponible para ese tipo")
        }
        requireNotNull(colorPrincipalPrenda):
        requireNotNull(materialPrenda);
        material = materialPrenda;
        colorPrincipal = colorPrincipalPrenda;
    }

    //Setteo opcional (Trama y Color Secundario)

    void setColorSecundario(Color colorSecundarioPrenda){
        requireNotNull(colorSecundarioPrenda);
        colorSecundario = colorSecundarioPrenda;
    }
    void setTrama(Trama tramaPrenda){
        requireNotNull(tramaPrenda);
        trama = tramaPrenda;
    }

    //Crear prenda con las caracteristicas elegidas

    Prenda crearPrenda()
        Prenda nuevaPrenda = new Prenda();
        nuevaPrenda.setCaracteristicas(tipo, trama, material, colorPrincipal, colorSecundario);
        return nuevaPrenda;
    }   
}

clase Prenda{
    Tipo tipo;
    Trama trama;
    Categoria categoria;
    Double temperaturaMax;
    Material material;
    Color colorPrincipal;
    Color colorSecundario;
    
    void setCaracteristicas(Tipo tipo,Trama trama,Material material,Color colorPrincipal,Color colorSecundario) {
        this.tipo = tipo;
        this.categoria = tipo.getCategoria();
        this.temperaturaMax = tipo.getTemperaturaMax();
        this.trama = trama;
        this.material = material;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
    }

    Categoria getCategoria() {
        return categoria;
    }

    Boolean cumpleCondicionTemperatura(Double temperatura) {
        return temperaturaMax <= temperatura;
    }

    Boolean esDeCategoria(Categoria OtraCategoria) {
        return OtraCategoria == categoria;
    }
}

/*  
Notas QMP2: 

    En este caso use un builder para poder identificar cuando una prenda no estaba
terminada, ya que cuando lo esta se crea utilizando un metodo que la buildea. Por eso mismo
prenda no tiene constructor pero si setters. 
    Por otro lado la otra duda importante que me quedo era la diferencia entre los uniformes y un 
"atuendo comun", no termine de entender bien ese requerimiento
    Por lo demas a mi se me ocurrio plantearlo asi pero puede estar horriblemente ejecutado, con cosas
de las que ni yo estaba muy seguro como dejar en Null algunos atributos.
*/



