class NotificacionEvitarSalirAuto implements NotificacionServicio{
  boolean hayGranizo(List<String> alertas) {
     return alertas.contains("HAIL");
  }

  void notificar(String mail) {
    if(this.hayGranizo(alertas))
      ServicioMail.enviar("Se le recomienda no salir con el auto, alerta de granizo",mail);
  }

}