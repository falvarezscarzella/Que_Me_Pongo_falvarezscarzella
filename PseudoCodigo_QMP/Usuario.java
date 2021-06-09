
//----------USUARIO------------//

class Usuario {
    Map<String, Boolean> configuracion = new HashMap<>(); //A la hora de notificar se fija si cumple la configuracion aca.
    List<Guardaropas> listaGuardaropas = new ArrayList<>();
    List<Atuendo> listSugerencias = new ArrayList<>();
    String mail;

    String getEmail() {
        return email;
    }

    void agregarGuardarropas(String nombre) {
        listaGuardaropas.add(new Guardaropas(nombre));
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

    void cargarSugerencias(List <Atuendo> lista){
        listaSugerencias.addAll(lista);
    }
    //etc.
}