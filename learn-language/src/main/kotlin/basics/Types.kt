package basics

class Types {

    fun strings() {
        // Implicitly typed
        val firstName = "Tom"
        // Explicitly typed
        val lastName: String = "Hanks"

        // String template 사용
        println("His name is $firstName.") // His name is Tom.

        println("His full name is ${firstName + " " + lastName}.") // His name is Tom Hanks.
        // or
        println("His full name is ${"$firstName $lastName"}.") // His name is Tom Hanks.
    }

    fun numbers() {
        // Signed (8, 16, 32, 64 bits)
        val byte: Byte = 127
        val short: Short = 10_000
        val int: Int = 30
        val long: Long = 100_000L

        // Unsigned
        val uByte: UByte = 255u
        val uShort: UShort = 10_000u
        val uInt: UInt = 30u
        val uLong: ULong = 1_000_000_000u

        // Decimals
        val float: Float = 1.5f
        val double: Double = 11.55
    }

    fun others() {
        val trueFalse: Boolean = true
        val alphabet: Char = 'a'
    }

}

fun main() {
    Types().strings()
}
