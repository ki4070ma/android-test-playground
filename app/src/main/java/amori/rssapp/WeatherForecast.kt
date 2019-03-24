package amori.rssapp

enum class Weather {
    SUNNY, CLOUDY, RAINY
}

class WeatherForecast (val satellite: Satellite,
                       val recorder: WeatherRecorder,
                       val formatter: WeatherFormatter) {

    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather()
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather() {
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }
}


open class WeatherFormatter {
    open fun format(weather: Weather): String = "Weather is ${weather}"
}

class SpyWeatherFormatter: WeatherFormatter() {
    var weather: Weather? = null
    var isCalled = false

    override fun format(weather: Weather): String {
        this.weather = weather
        isCalled = true
        return super.format(weather)
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
    open fun record(weather: String) {
        // some process
    }
}

class MockWeatherRecorder : WeatherRecorder() {
    var weather: String? = null
    var isCalled = false

    override fun record(weather: String) {
        this.weather = weather
        isCalled = true
    }
}
