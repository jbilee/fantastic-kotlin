package intermediate

/*
Kotlin has built-in support for singletons, using keyword `object`. Object declaration이라고 함

Kotlin objects are lazy 즉, object에 실제로 접근할 때 생성됨
*/

// Object declaration을 하는 방법:
object DoAuth {
    fun takeParams(username: String, password: String) {
        println("input Auth parameters = $username:$password")
    }
}

// Data object 만들기:
data object AppConfig {
    var appName: String = "My Application"
    var version: String = "1.0.0"
}

/*
Companion object = An object that belongs to a class (only one companion object per class allowed)

Any properties or functions declared inside a companion object are shared across all class instances.

Companion objects are created when the class containing it is referenced for the first time
Class can access the companion object's properties and functions

Companion objects don't require names--default is named "Companion"
*/
class BigBen {

    // The name Bonger is optional--can be omitted
    companion object Bonger {
        val sound: String = "BONG"

        fun getBongs(nTimes: Int) {
            repeat(nTimes) { print("$sound ") }
        }
    }

}

// companion object 응용:
data class Temperature(val celsius: Double) {
    val fahrenheit: Double = celsius * 9 / 5 + 32

    companion object {
        fun fromFahrenheit(fahrenheit: Double): Temperature = Temperature((fahrenheit - 32) * 5 / 9)
    }
}

fun main() {
    // Object 사용 (이때 실제로 생성됨)
    DoAuth.takeParams("jbilee", "pass") // input Auth parameters = jbilee:pass

    println(AppConfig) // AppConfig
    println(AppConfig.appName) // My Application

    println(BigBen.sound) // BONG
    BigBen.getBongs(3) // BONG BONG BONG

    val fahrenheit = 90.0
    val temp = Temperature.fromFahrenheit(fahrenheit)
    println("${temp.celsius}°C is $fahrenheit °F") // 32.22222222222222°C is 90.0 °F
}
