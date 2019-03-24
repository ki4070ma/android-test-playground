package amori.rssapp

import org.junit.Test

import org.assertj.core.api.Assertions.*
import org.junit.Before

import com.nhaarman.mockitokotlin2.*

class WeatherForecastTest {
    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: MockWeatherRecorder
    lateinit var formatter: SpyWeatherFormatter

    @Before
    fun setup() {
        satellite = mock(name = "MockSatellite")
        whenever(satellite.getWeather(any(), any()))
            .thenReturn(Weather.CLOUDY)
        whenever(satellite.getWeather(eq(37.580006), eq(-122.345106)))
            .thenReturn(Weather.SUNNY)
        whenever(satellite.getWeather(eq(37.792872), eq(-122.396915)))
            .thenReturn(Weather.RAINY)
        recorder = MockWeatherRecorder()
        formatter = SpyWeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        whenever(satellite.getWeather(any(), any())).thenReturn(Weather.SUNNY)

        assertThat(weatherForecast.shouldBringUmbrella(any(), any())).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenCloudy_returnFalse() {
        whenever(satellite.getWeather(any(), any())).thenReturn(Weather.CLOUDY)

        assertThat(weatherForecast.shouldBringUmbrella(any(), any())).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenRainy_returnTrue() {
        whenever(satellite.getWeather(any(), any())).thenReturn(Weather.RAINY)

        assertThat(weatherForecast.shouldBringUmbrella(any(), any())).isTrue()
    }

    @Test
    fun recordCurrentWeather_assertCalled() {
        weatherForecast.recordCurrentWeather(any(), any())

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather)
            .contains("Weather is")
            // TODO contains either Weather.SONNY/CLOUDY/RAINY
    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        weatherForecast.recordCurrentWeather(any(), any())

        val isCalled = formatter.isCalled
        assertThat(isCalled).isTrue()

        val weather = formatter.weather
        assertThat(weather)
            .isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }
}
