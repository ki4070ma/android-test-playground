package amori.rssapp

import org.junit.Test

import org.assertj.core.api.Assertions.*
import org.junit.Before

class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: MockWeatherRecorder

    @Before
    fun setup() {
        recorder = MockWeatherRecorder()
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val satellite = StubSatellite(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite, recorder)

        assertThat(weatherForecast.shouldBringUmbrella()).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenCloudy_returnFalse() {
        val satellite = StubSatellite(Weather.CLOUDY)
        weatherForecast = WeatherForecast(satellite, recorder)

        assertThat(weatherForecast.shouldBringUmbrella()).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenRainy_returnTrue() {
        val satellite = StubSatellite(Weather.RAINY)
        weatherForecast = WeatherForecast(satellite, recorder)

        assertThat(weatherForecast.shouldBringUmbrella()).isTrue()
    }

    @Test
    fun recordCurrentWeather_assertCalled() {
        val satellite = Satellite()
        weatherForecast = WeatherForecast(satellite, recorder)

        weatherForecast.recordCurrentWeather()

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather)
            .isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)

    }

}