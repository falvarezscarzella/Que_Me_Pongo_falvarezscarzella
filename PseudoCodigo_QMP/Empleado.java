class Empleado {
  List<Usuario> usuarios; //Podria ser una clase repositorio de usuarios

  void sugerirUsuarios(){
    usuarios.forEach(usuario -> {usuario.actualizarSugerenciaDiaria()});
  }

  void cargarListaDeSugerenciasDiarias(){
    usuarios.stream().forEach(usuario -> {usuario.cargarSugerencias(listaAtuendos)});
  }

  void alertasPublicadas(ServicioDeClima clima){
    // Se podria hacer un hashMap Date,Alerta para poder gestionar cuando se publican
    clima.getHistorialAlertas();
  }
}