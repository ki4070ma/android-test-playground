package amori.rssapp

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