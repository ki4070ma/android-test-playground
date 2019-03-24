package amori.rssapp

class WeatherForecast (val satellite: Satellite) {

    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather()
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }
}

enum class Weather {
    SUNNY, CLOUDY, RAINY
}