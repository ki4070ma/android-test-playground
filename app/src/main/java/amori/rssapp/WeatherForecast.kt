package amori.rssapp

enum class Weather {
    SUNNY, CLOUDY, RAINY
}

class WeatherForecast (val satellite: Satellite,
                       val recorder: WeatherRecorder) {

    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather()
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather() {
        val weather = satellite.getWeather()
        recorder.record(weather)
    }
}

open class Satellite {
    open fun getWeather(): Weather {
        return Weather.SUNNY  // Temp
    }
}

class StubSatellite(val anyWeather: Weather): Satellite() {
    override fun getWeather(): Weather {
        return anyWeather
    }
}

open class WeatherRecorder {
    open fun record(weather: Weather) {
        // some process
    }
}

class MockWeatherRecorder : WeatherRecorder() {
    var weather: Weather? = null
    var isCalled = false

    override fun record(weather: Weather) {
        this.weather = weather
        isCalled = true
    }
}
