
//-------------ATUENDO------------//

class Atuendo{
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

class SugeridorDeAtuendos {
    List<Prenda> prendasPosibles; // Seria la lista de prendas del usuario
    String ciudadDondeSeEncuentra;
    ServicioDeClima servicioDeClima;

    SugeridorDeAtuendos(String ciudadDondeSeEncuentra,List<Prenda> prendasPosibles) {
        this.ciudadDondeSeEncuentra = ciudadDondeSeEncuentra
        this.prendasPosibles = prendasPosibles;
    }

    Prenda sugerirParteSuperior() {
        SugeridorDePrendas sugeridorParteSuperior = new SugeridorDePrendas(temperaturaActual,Categoria.PARTE_SUP);
        return sugeridorParteSuperior.sugerirPrenda(prendasPosibles);
    }

    Prenda sugerirParteInferior() {
        Double temperaturaActual = servicioDeClima.obtenerClima(ciudadDondeSeEncuentra)
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

class SugeridorDePrendas {
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

interface ServicioDeClima{
    Double obtenerClima(String ciudad);
}

class ServicioDeClimaAccuWeather implements ServicioDeClima{
    ClimaAPI api;

    ServicioDeClimaAccuWeather(AccuWeatherAPI apiNueva) {
        this.api = apiNueva;
    }

    Double obtenerClima(String ciudad) {
        return api.getWeather(ciudad)
        .get(0)
        .get("Temperature")
        .get("Value")
        .toDegrees();
    }
}

class AccuWeatherAPI{

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
				put("Value", 85);
				put("Unit", "F");
				put("UnitType", 18);
			}});
		}});
	}
}



//Notas QMP4:
/*

- Para la temperatura elegi seguir usando el comportamiento del enum para tener bien definida la temperatura
segun su tipo, la cual se settea automaticamnete al instanciar una prenda

- Para asegurarnos de que cada prenda sea de su categoria correspondiente, al instanciar el sugeridorDePrendas se le manda la 
categoria que necesita para que trabaje con eso y no devuelva una categoria erronea 

- Utilize un factory method para crear estos atuendos sugeridos

- Por ahora y como entiendo que el usuario no puede crear atuendos, la creacion de los mismos se hace unicamente mediante un sugeridor,
especificandole en que lugar del mundo se encuentra para que accuweather pueda indocarle la temperatura actual, y una lista de prendas
posibles que el usuario podria tener ya que suponemos las hbaia creado previamente
*/

