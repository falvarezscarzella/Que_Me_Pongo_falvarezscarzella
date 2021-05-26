
//----------USUARIO------------

class Usuario {
    List<Guardaropas> listaGuardaropas = new ArrayList<>();

    void agregarGuardarropas(String nombre) {
        listaGuardaropas.add(new Guardaropas(nombre));
    }

    List<Guardaropas> getGuardarropas() {
        return listaGuardaropas;
    }

    void agregarPrendaAGuardarropa(Guardaropas guardarropas,Prenda prenda) {
        guardarropas.agregarPrenda(prenda);
    }

    //etc.
}