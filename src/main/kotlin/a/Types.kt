package a

class Types {

    fun strings() {
        // Implicitly typed
        val firstName = "Tom"
        // Explicitly typed
        val lastName: String = "Hanks"

        // String template 사용
        println("His name is $firstName.")

        println("His full name is ${firstName + " " + lastName}.")
        // or
        println("His full name is ${"$firstName $lastName"}.")
    }

}
