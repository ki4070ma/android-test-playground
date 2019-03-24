package amori.rssapp

import org.junit.Test

import org.assertj.core.api.Assertions.*
import org.junit.Before

import com.nhaarman.mockitokotlin2.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class WeatherForecastTest {
    @Mock
    lateinit var satellite: Satellite
    @Mock
    lateinit var recorder: WeatherRecorder
    @Spy
    lateinit var formatter: WeatherFormatter

    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        satellite = mock(name = "MockSatellite") {
            on { getWeather(any(), any()) } doReturn Weather.CLOUDY
            on { getWeather(eq(37.580006), eq(-122.345106)) } doReturn Weather.SUNNY
            on { getWeather(eq(37.792872), eq(-122.396915)) } doReturn Weather.RAINY
        }
        recorder = mock(name = "MockRecorder")
        formatter = spy(WeatherFormatter())
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
    fun recordCurrentWeather_assertRecorderCalled() {
        weatherForecast.recordCurrentWeather(37.580006, -122.345106)
//        verify(recorder, times(1)).record(any())
        argumentCaptor<Record>().apply {
            verify(recorder, times(1)).record(capture())
            assertThat(firstValue.description).isEqualTo("Weather is SUNNY")
        }

    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        weatherForecast.recordCurrentWeather(37.580006, -122.345106)
        verify(formatter, times(1)).format(any())
    }
}
