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

    @Test
    fun shouldBringUmbrella_givenCloudy_returnFalse() {
        val satellite = StubSatellite(Weather.CLOUDY)
        weatherForecast = WeatherForecast(satellite)

        assertThat(weatherForecast.shouldBringUmbrella()).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenRainy_returnTrue() {
        val satellite = StubSatellite(Weather.RAINY)
        weatherForecast = WeatherForecast(satellite)

        assertThat(weatherForecast.shouldBringUmbrella()).isTrue()
    }

}