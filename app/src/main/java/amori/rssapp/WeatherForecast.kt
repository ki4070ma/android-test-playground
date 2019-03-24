package amori.rssapp

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

enum class Weather {
    SUNNY, CLOUDY, RAINY
}