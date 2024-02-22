package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherReportService {

    private static final String API_KEY = "f2a49fad26dcaa60f14251fc4815fb9d";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherReport getWeatherReport(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        WeatherReport report = new WeatherReport();
        WeatherApiResponse.Main main = response.getMain();

        double temperaturaCelcius = main.getTemperature() - 273.15;
        report.setTemperature(temperaturaCelcius);

        report.setHumidity(main.getHumidity());


        // prueba por que me esta devolviendo una temperatua en 0.0
        System.out.println("URL de la solicitud: " + url);
        System.out.println("Respuesta de la API: " + response);


        return report;
    }
}
