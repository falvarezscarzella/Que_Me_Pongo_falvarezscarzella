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

//---------------PRENDA-------------------//

clase Borrador{
    Tipo tipo;
    Trama trama = LISA;
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

//--------------UNIFORME--------------

clase abtracta Uniforme {

    Prenda parteSup;
    Prenda parteInf;
    Prenda calzado;

    Uniforme(Prenda parteSuperior,Prenda parteInferior,Prenda calzado) {
        verificarUniforme(parteSuperior,parteInferior,calzado);
        this.parteSuperior = parteSuperior;
        this.parteInferior = parteInferior;
        this.calzado = calzado;
    }
}

interface Sastre {
    method fabricarParteSuperior();
    method fabricarParteInferior();
    method fabricarCalzado();
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

//-------------ATUENDO------------//

clase Atuendo{
    Prenda parteSuperior;
    Prenda parteInferior;
    Prenda calzado;
    Prenda accesorio;

    Atuendo(Prenda parteSuperior,Prenda parteInferior,Prenda calzado,Prenda accesorio) {
        requireNotNull(parteSuperior);
        //resto de verificaciones de null
        this.parteSuperior = parteSuperior
        //resto de setteos
    }

}

clase SugeridorDeAtuendos {
    List<Prenda> prendasPosibles; // Seria la lista de prendas del usuario
    Double temperaturaActual;

    SugeridorDeAtuendos(String ciudadDondeSeEncuentra,List<Prenda> prendasPosibles) {
        this.temperaturaActual = ServicioDeClima.getWeather(ciudadDondeSeEncuentra)
            .get(0)
            .get("Temperature")
            .get("Value");
        this.prendasPosibles = prendasPosibles;
    }

    Prenda sugerirParteSuperior() {
        SugeridorDePrendas sugeridorParteSuperior = new SugeridorDePrendas(temperaturaActual,Categoria.PARTE_SUP);
        return sugeridorParteSuperior.sugerirPrenda(prendasPosibles);
    }

    Prenda sugerirParteInferior() {
        SugeridorDePrendas sugeridorParteInferior = new SugeridorDePrendas(temperaturaActual,Categoria.PARTE_INF);
        return sugeridorParteSuperior.sugerirPrenda(prendasPosibles);
    }

    // Y asi con accesorios y calzado

    Atuendo crearAtuendoSugerido() {
        return new Atuendo(
            this.sugerirParteSuperior(),
            this.sugerirParteInferior(),
            this.sugerirCalzado(),
            this.sugerirAccesorio()
        )
    }
}

clase SugeridorDePrendas {
    Double temperatura;
    Categoria categoria;

    SugeridorDePrendas(Double temperatura,Categoria categoria) {
        this.temperatura = temperatura;
        this.categoria = categoria;
    }

    Prenda sugerirPrenda(List<Prenda> prendasPosibles) {
       return prendasPosibles.stream()
       .filter(prenda -> prenda.cumpleCondicionTemperatura(temperatura) && prenda.esDeCategoria(categoria))
       .findAny();
    }
}

class ServicioDeClima {
    ClimaAPI api = new AccuWeatherAPI();

    static void setAPIdelClima(ClimaAPI apiNueva) {
        this.api = apiNueva;
    }

    List<Map<String, Object>> obtenerClima(String ciudad) {
        return api.getWeather(ciudad);
    }
}

interfaz ClimaAPI {
     public final List<Map<String, Object>> getWeather(String ciudad);
}

clase AccuWeatherAPI{

    public final List<Map<String, Object>> getWeather(String ciudad) {
		return Arrays.asList(new HashMap<String, Object>(){{
			put("DateTime", "2019-05-03T01:00:00-03:00");
			put("EpochDateTime", 1556856000);
			put("WeatherIcon", 33);
			put("IconPhrase", "Clear");
			put("IsDaylight", false);
			put("PrecipitationProbability", 0);
			put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
			put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
			put("Temperature", new HashMap<String, Object>(){{
				put("Value", 25);
				put("Unit", "C");
				put("UnitType", 18);
			}});
		}});
	}
}



//Notas:
/*

- Para la temperatura elegi seguir usando el comportamiento del enum para tener bien definida la temperatura
segun su tipo, la cual se settea automaticamnete al instanciar una prenda

- Cambie las unidades del accuweather a celsius para que me sea mas facil

- Para asegurarnos de que cada prenda sea de su categoria correspondiente, al instanciar el sugeridorDePrendas se le manda la 
categoria que necesita para que trabaje con eso y no devuelva una categoria erronea 

- Utilize un factory method para crear estos atuendos sugeridos

- Por ahora y como entiendo que el usuario no puede crear atuendos, la creacion de los mismos se hace unicamente mediante un sugeridor,
especificandole en que lugar del mundo se encuentra para que accuweather pueda indocarle la temperatura actual, y una lista de prendas
posibles que el usuario podria tener ya que suponemos las hbaia creado previamente

- Decidi usar una interface y una clase con un class method para implementar el intercambio de servicios de obtencion del clima (Igualmente tengo
bastantes dudas al respecto de si era eso lo que pedia)
*/

//Cosas que no entendi:
/* 
- El tema de la API me resulto algo confuso para implementar al al igual que toda la parte del stakeholder, asi que hice lo que
entendi y pude
*/