
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

  Prenda sugerirParteSuperior() {}
  Prenda sugerirParteInferior() {}
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

class SugeridorSegunTemperatura implements SugeridorDeAtuendos {
    Double temperaturaActual;

    SugeridorSegunTemperatura(String ciudadDondeSeEncuentra,List<Prenda> prendasPosibles) {
      super(ciudadDondeSeEncuentra, prendasPosibles);
      this.temperaturaActual = servicioDeClima.obtenerClima(ciudadDondeSeEncuentra);
    }

    @Override
    Prenda sugerirParteSuperior() {
        SugeridorDePrendas sugeridorParteSuperior = new SugeridorDePrendas(temperaturaActual,Categoria.PARTE_SUP);
        return sugeridorParteSuperior.sugerirPrenda(prendasPosibles);
    }

    @Override
    Prenda sugerirParteInferior() {
        SugeridorDePrendas sugeridorParteInferior = new SugeridorDePrendas(temperaturaActual,Categoria.PARTE_INF);
        return sugeridorParteSuperior.sugerirPrenda(prendasPosibles);
    }
}

class SugeridorSegunAlerta implements SugeridorDeAtuendos {
    List<String> alertasRecientes;

    SugeridorSegunTemperatura(String ciudadDondeSeEncuentra,List<Prenda> prendasPosibles) {
      super(ciudadDondeSeEncuentra, prendasPosibles);
      this.alertasRecientes = servicioDeClima.obtenerAlertas(ciudadDondeSeEncuentra);
    }

    @Override
    Prenda sugerirParteSuperior() {
          SugeridorDePrendas sugeridorParteSuperior = new SugeridorDePrendas(Categoria.PARTE_SUP);
          sugeridorParteSuperior.set(Alertas alerta)
          return sugeridorParteSuperior.sugerirPrenda(prendasPosibles);
    }

    @Override
    Prenda sugerirParteInferior() {
        SugeridorDePrendas sugeridorParteInferior = new SugeridorDePrendas(temperaturaActual,Categoria.PARTE_INF);
        return sugeridorParteSuperior.sugerirPrenda(prendasPosibles);
    }
}

clase SugeridorDePrendas {
    Double temperatura;
    List<String> alertasList = new ArrayList<>();
    Categoria categoria;

    SugeridorDePrendas(Double temperatura,Categoria categoria) {
        this.temperatura = temperatura;
        this.categoria = categoria;
    }

    SugeridorDePrendas(List<String> alertas,Categoria categoria) {
      this.alertasList = alertas;
      this.categoria = categoria;
    }

    //Se podria hacer lo mismo que con el sugeridor de atuendos y separarlo en dos clases

    Prenda sugerirPrendaSegunTemperatura(List<Prenda> prendasPosibles) {
       return prendasPosibles.stream()
          .filter(prenda -> prenda.cumpleCondicionTemperatura(temperatura) && prenda.esDeCategoria(categoria))
          .findAny();
    }

    Prenda sugerirPrendaSegunAlerta(List<Prenda> prendasPosibles) {
        return prendasPosibles.stream()
          .filter(prenda -> prenda.sePuedeUsarEnEsaAlerta() && prenda.esDeCategoria(categoria))
          .findAny();
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

