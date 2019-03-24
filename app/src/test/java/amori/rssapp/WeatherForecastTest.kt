package amori.rssapp

import org.junit.Test

import org.assertj.core.api.Assertions.*

class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val satellite = StubSatellite(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite)

        assertThat(weatherForecast.shouldBringUmbrella()).isFalse()
    }
}