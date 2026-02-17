package intermediate

fun main() {
    openClasses()
    enums()
    inlineValueClasses()
}

/*
Open class: A class that can be inherited from. By default, classes in Kotlin are final (cannot be inherited).
To allow inheritance, you need to mark the class with the 'open' keyword.
*/
fun openClasses() {
    val me = BackendEngineer("Julie")
    me.work() // Working on server development.
}

open class Engineer(val name: String, val title: String) {
    // Function has to be marked open as well to be overridden
    open fun work() {
        println("Working on something.")
    }
}

// Inheriting open class `Engineer`
class BackendEngineer(name: String) : Engineer(name, "Backend Engineer") {
    override fun work() {
        println("Working on server development.")
    }
}

/*
Sealed class: Special abstract class that restricts inheritance.
Subclasses inheriting a sealed class must be declared in the same package as the sealed class.
*/
sealed class Food(val cuisine: String, val name: String)

// Does not work if Food is in a different package
class Bibimbap(cuisine: String, name: String) : Food(cuisine, name)

/*
Enum
*/
enum class Direction {
    NORTH, SOUTH, EAST, WEST
}

enum class Planet(val mass: Double, val radius: Double) {
    MERCURY(3.3011e23, 2439.7),
    VENUS(4.8675e24, 6051.8),
    EARTH(5.97237e24, 6371.0),
    MARS(6.4171e23, 3389.5);

    fun isHabitable(): Boolean {
        return this == EARTH
    }
}

fun enums() {
    println(Planet.MARS.radius) // 3389.5
    println(Planet.EARTH.mass) // 5.97237E24
    println(Planet.VENUS.isHabitable()) // false
}

/*
Inline value class: Briefly used class that can only contain one value.
Used to avoid performance impact of wrapping a value in an object--optimized by the compiler to avoid object allocation.
Value classes must have exactly one property initialized in the primary constructor and cannot have backing fields.
*/
@JvmInline
value class Email(val address: String)

fun inlineValueClasses() {
    // Object is not actually created
    val email = Email("a@gmail.com")
    println(email.address) // a@gmail.com
}
