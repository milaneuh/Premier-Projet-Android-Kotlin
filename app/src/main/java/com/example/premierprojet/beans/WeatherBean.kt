package com.example.premierprojet.beans

class WeatherBean {
    var name:String
    get() = field
    set(value){field=value}
    var visibility:Int

    var lgt:Double
    var lat:Double

    constructor(name: String, visibility: Int, lgt: Double, lat: Double) {
        this.name = name
        this.visibility = visibility
        this.lgt = lgt
        this.lat = lat
    }

}