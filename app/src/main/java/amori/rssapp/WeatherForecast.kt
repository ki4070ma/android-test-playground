package amori.rssapp

enum class Weather {
    SUNNY, CLOUDY, RAINY
}

class WeatherForecast (val satellite: Satellite,
                       val recorder: WeatherRecorder,
                       val formatter: WeatherFormatter) {

    fun shouldBringUmbrella(latitude: Double, longitude: Double): Boolean {
        val weather = satellite.getWeather(latitude, longitude)
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather(latitude: Double, longitude: Double) {
        val weather = satellite.getWeather(latitude, longitude)
        val description = formatter.format(weather)
        recorder.record(Record(description))
    }
}


open class WeatherFormatter {
    open fun format(weather: Weather): String = "Weather is ${weather}"
}

open class Satellite {
    open fun getWeather(latitude: Double, longitude: Double): Weather {
        return Weather.SUNNY  // Temp
    }
}

class Record(val description: String)

open class WeatherRecorder {
    open fun record(record: Record) {
        // some process
    }
}
