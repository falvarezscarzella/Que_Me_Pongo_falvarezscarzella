
//------------GUARDARROPAS----------------

class Guardarropas {
    String nombre;
    List<Prenda> listaPrendas = new ArrayList<>();
    List<Solicitud> listaSolicitudes = new ArrayList<>();
    HistorialSolicitudes historial = new HistorialSolicitudes();

    Guardarropas(String nombre) {
        this.nombre = nombre;
    }

    String getNombre() {
        return nombre;
    }

    List<Prenda> getPrendas() {
        return listaPrendas;
    }

    List<Solicitud> getHistorial() {
        return historial.getHistorial();
    }

    void agregarPrenda(Prenda prenda) {
        listaPrendas.add(prenda);
    }

    void agregarSolicitud(Solicitud solicitud) {
        listaSolicitudes.add(solicitud);
    }

    void deshacerSolicitud(Solicitud solicitud) {
        if(historial.getHistorial().contains(solicitud))
            solicitud.deshacerCambioEn(this);
            historial.historialSolicitudes.remove(solicitud);
        else
            throw excepcionSolicitudNoExiste("Esa solicitud no esta en el historial de aceptadas");
    }

    List<Solicitud> verSolicitudesPendientes() {
        return listaSolicitudes;
    }

    void aceptarSolicitud(Solicitud solicitud) {
        solicitud.aplicarEn(this);
        listaSolicitudes.remove(solicitud);
        historial.historialSolicitudes.add(solicitud);
    }

    void rechazarSolicitud(Solicitud solicitud) {
        listaSolicitudes.remove(solicitud);
    }
}

//-----------HISTORIAL--------------------

class HistorialSolicitudes {
    List<Solicitud> historialSolicitudes = new ArrayList<>();

    List<Solicitud> getHistorial() {
        return historialSolicitudes;
    }
}

//-----------SOLICITUDES------------------

interface Solicitud {
    Prenda prenda;

    void aplicarEn(Guardarropas guardarropas);
    void deshacerCambioEn(Guardarropas guardarropas);
}

class SolicitudParaAgregar implements Solicitud {

    SolicitudParaAgregar(Prenda prenda) {
        this.prenda = prenda;
    }

    void aplicarEn(Guardarropas guardarropas) {
        guardarropas.listaPrendas.add(prenda);
    }

    void deshacerCambioEn(Guardarropas guardarropas) {
        guardarropas.listaPrendas.remove(prenda);
    }
}

class SolicitudParaQuitar implements Solicitud {

    SolicitudParaQuitar(Prenda prenda) {
        this.prenda = prenda;
    }

    void aplicarEn(Guardarropas guardarropas) {
        guardarropas.listaPrendas.remove(prenda);
    }

    void deshacerCambioEn(Guardarropas guardarropas) {
        guardarropas.listaPrendas.add(prenda);
    }
}

/*
Notas QMP5:

-Hice un prototipo de clase usuario como para poder yo visualizar ciertos comportamientos
o atributos que deberia tener especialmente enfocados a esta iteracion

-No tengo muy en claro el alcance del requerimiento del guardarropas compartido, pero supongo que simplemente
alcanza con que dos usuarios tengan en su lista de guardarropas el mismo guardarropas ya que ese guardarropas solo
podra ser accedido por quienes lo tengan en su lista, ya sea uno o mas usuarios

 */