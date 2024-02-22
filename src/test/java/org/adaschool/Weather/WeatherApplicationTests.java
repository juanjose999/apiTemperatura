package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherApplicationTests {
	@Mock
	WeatherReportService weatherReportService;

	@InjectMocks
	WeatherReportController weatherReportController;

	@BeforeEach
	void setUp() {
		weatherReportController = new WeatherReportController(weatherReportService);
	}

	@Test
	void testReturnDataWeatherReport() {
		double latitude = 37.8267;
		double longitude = -122.4233;

		// Instancia de la clase
		WeatherReport expectedWeatherReport = new WeatherReport();
		expectedWeatherReport.setTemperature(25.0);
		expectedWeatherReport.setHumidity(70.0);

		when(weatherReportService.getWeatherReport(anyDouble(), anyDouble()))
				.thenReturn(expectedWeatherReport);

		WeatherReport actualWeatherReport = weatherReportController.getWeatherReport(latitude, longitude);

		assertEquals(expectedWeatherReport, actualWeatherReport);
	}

}
