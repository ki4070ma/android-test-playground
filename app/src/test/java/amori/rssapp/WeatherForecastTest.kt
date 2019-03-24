package amori.rssapp

import org.junit.Test

import org.assertj.core.api.Assertions.*
import org.junit.Before

import com.nhaarman.mockitokotlin2.*

class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: MockWeatherRecorder
    lateinit var formatter: SpyWeatherFormatter

    @Before
    fun setup() {
        recorder = MockWeatherRecorder()
        formatter = SpyWeatherFormatter()
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val satellite: Satellite = mock(name = "MockSatellite")
        whenever(satellite.getWeather()).thenReturn(Weather.SUNNY)

        weatherForecast = WeatherForecast(satellite, recorder, formatter)

        assertThat(weatherForecast.shouldBringUmbrella()).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenCloudy_returnFalse() {
        val satellite: Satellite = mock(name = "MockSatellite")
        whenever(satellite.getWeather()).thenReturn(Weather.CLOUDY)

        weatherForecast = WeatherForecast(satellite, recorder, formatter)

        assertThat(weatherForecast.shouldBringUmbrella()).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenRainy_returnTrue() {
        val satellite: Satellite = mock(name = "MockSatellite")
        whenever(satellite.getWeather()).thenReturn(Weather.RAINY)

        weatherForecast = WeatherForecast(satellite, recorder, formatter)

        assertThat(weatherForecast.shouldBringUmbrella()).isTrue()
    }

    @Test
    fun recordCurrentWeather_assertCalled() {
        val satellite = Satellite()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)

        weatherForecast.recordCurrentWeather()

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather)
            .contains("Weather is")
            // TODO contains either Weather.SONNY/CLOUDY/RAINY
    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        val satellite = Satellite()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)

        weatherForecast.recordCurrentWeather()

        val isCalled = formatter.isCalled
        assertThat(isCalled).isTrue()

        val weather = formatter.weather
        assertThat(weather)
            .isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }
}
