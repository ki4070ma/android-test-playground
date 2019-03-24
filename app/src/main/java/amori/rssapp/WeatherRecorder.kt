package amori.rssapp

open class WeatherRecorder {
    open fun record(weather: Weather) {

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