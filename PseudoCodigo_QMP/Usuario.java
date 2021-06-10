
//----------USUARIO------------//

class Usuario {
    List<Guardaropas> listaGuardaropas = new ArrayList<>();
    List<String> alertasDeInteres = new ArrayList<>();
    Atuendo sugerenciaDiaria;
    String mail;

    void agregarGuardarropas(String nombre) {
        listaGuardaropas.add(new Guardaropas(nombre));
    }

    void agregarAlertaDeInteres(String alerta) {
        alertaDeInteres.add(alerta);
    }

    List<Guardaropas> getGuardarropas() {
        return listaGuardaropas;
    }

    void agregarPrendaAGuardarropa(Guardaropas guardarropas,Prenda prenda) {
        guardarropas.agregarPrenda(prenda);
    }

    List<String> informarsePaginaUltimasAlertas(ServicioDeClima clima){
        return clima.getHistorialAlertas();
    }

    void actualizarSugerenciaDiaria(){
        List<Atuendo> listaSugerencias = new ArrayList<>();
        listaSugerencias.add(new SugeridorSegunAlerta.crearAtuendoSugerido());
        listaSugerencias.add(new SugeridorSegunTemperatura.crearAtuendoSugerido());
        sugerenciaDiaria = listaSugerencias.findAny();
    }

    Boolean alertaDeInteres(String alerta) {
        alertasDeInteres.contains(alerta);
    }

    void notificarAlerta() {
        if (clima.hayNuevaAlerta()) {
            String ultimaAlerta = clima.obtenerAlertas().get(alertas.size()-1);
            if(this.alertaDeInteres(ultimaAlerta)){ 
            ServicioMail.enviar("Se genero una nueva alerta:" + ultimaAlerta, mail);
            }
        }
      }
    }
    //etc.
}