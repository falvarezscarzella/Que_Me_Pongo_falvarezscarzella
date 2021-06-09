
//---------------ENUMS-------------------

enum Tipo{
    CAMISA(Categoria.PARTE_SUP, [ALGODON,SEDA],40),
    PANTALON(Categoria.PARTE_INF,[JEAN,ALGODON,ACETATO],22),
    ZAPATOS(Categoria.CALZADO,[CUERO],25),
    ANTEOJOS_DE_SOL(Categoria.ACCESORIOS,[PLASTICO,MADERA],40),
    CHOMBA(Categoria.PARTE_SUP,[PIQUE],30),
    SHORT(Categoria.PARTE_INF,[ALGODON,NYLON],40),
    CINTURON(Categoria.ACCESORIOS,[CUERO],40);

    Categoria categoria;
    Double temperaturaMax; //En celsius
    Lista<Material> materialesDisponibles; // En los enums lo puse entre corchetes para que sea mas facil de leer

    Tipo(Categoria categoria,Lista<Material> materialesDisponibles,Double temperaturaMax){
        this.categoria=categoria;
        this.materialesDisponibles = materialesDisponibles;
        this.temperaturaMax = temperaturaMax;
    }

    Categoria getCategoria(){return categoria;}
    Double getTemperaturaMax(){return temperaturaMax;}
    Lista<Material> getMaterialesDisponibles(){return materialesDisponibles;}
}

enum Categoria{
    PARTE_SUP,PARTE_INF,CALZADO,ACCESORIOS;
}

enum Material{
    ALGODON,NYLON,SEDA,JEAN,PLASTICO,MADERA,PIQUE,ACETATO;
}

enum Trama{
    LISA,RAYADA,LUNARES,CUADROS,ESTAMPADA;
}

enum Alerta {
    STORM, HAIL;
}