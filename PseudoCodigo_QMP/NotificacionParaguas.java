class NotificacionLlevarParaguas implements NotificacionServicio{
  boolean hayTormenta(List<String> alertas) {
      return alertas.contains("STORM");
  }

  public static void notificar(String mail){
    if(this.hayTormenta(alertas))
      ServicioMail.enviar("Se le recomienda salir con paraguas, alerta de Tormenta", mail);
  }
}