//-------------CLIMA------------//

interface ServicioDeClima{
    Double obtenerClima(String ciudad);
    List<String> obtenerAlertas();
}

clase ServicioDeClimaAccuWeather implements ServicioDeClima{
    ServicioDeClima api;
    String ciudad;
    List<String> historialAlertas;

    static void setAPIdelClima(AccuWeatherAPI apiNueva) {
        this.api = apiNueva;
    }

    Double obtenerClima() {
        return api.getWeather(this.ciudad)
        .get(0)
        .get("Temperature")
        .get("Value")
        .toDegrees();
    }

    List<String> obtenerAlertas() {
        Map<String, Object> alertas = this.getAlertas(this.ciudad);
        return (List<String>) alertas.get("CurrentAlerts");
    }

    void actualizarHistorialAlertas() {
        historialAlertas.add(this.obtenerAlertas());
    }

    List<String> getHistorialAlertas() {
       return historialAlertas;
    }

    Boolean hayNuevaAlerta(){
        List<String> historialAlertasTemp = historialAlertas;
        this.actualizarHistorialAlertas();
        return historialAlertasTemp.size() != historialAlertas.size();
    }

    Map<String, Object> getAlertas(String ciudad) {
        return api.getWeather(this.ciudad)
        .get(0)
        .get("CurrentAlerts");
    }
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
				put("Value", 85);
				put("Unit", "F");
				put("UnitType", 18);
            put("CurrentAlerts", Arrays.asList("STORM","HAIL"));
			}});
		}});
	}

}

/*
Notas QMP6:

-Puse como atributo del Servicio del Clima Api la ciudad, ya que se va a usar en obtenerAlertas y en obtenerClima.
Se podria ir cambiando mediante un setter.

-No tengo muy en claro el alcance del requerimiento del guardarropas compartido, pero supongo que simplemente
alcanza con que dos usuarios tengan en su lista de guardarropas el mismo guardarropas ya que ese guardarropas solo
podra ser accedido por quienes lo tengan en su lista, ya sea uno o mas usuarios

 */