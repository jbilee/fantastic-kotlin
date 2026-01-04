package basics

class Functions {

    fun main() {
        val onePlusTwo = sum(1, 2)
        println(onePlusTwo) // 3

        // 파라미터명을 함께 전달하면 인자 순서가 달라도 됨
        val prefixedMessage = addPrefix(message = "Bob", prefix = "[Name]")
        println(prefixedMessage) // [Name] Bob

        val incorrectlyPrefixed = addPrefix("Bob", "[Name]")
        println(incorrectlyPrefixed) // Bob [Name]

        // 파라미터의 default 값 설정 가능
        val somethingHanks = addLastName("Dolores")
        println(somethingHanks) // Dolores Hanks
    }

    /*
    함수 선언 시 파라미터의 타입과 return value의 타입을 반드시 명시해야 함

    함수의 default return 타입은 Unit (unless inferred)
    */
    private fun sum(x: Int, y: Int): Int {
        return x + y;
    }

    /*
    함수가 single-line일 경우 { } 생략 가능
    이때, return type 추론이 가능해서 return type 생략도 가능
    */
    fun sumShortened(x: Int, y: Int) = x + y

    fun addPrefix(prefix: String, message: String): String {
        return "$prefix $message"
    }

    fun addLastName(firstName: String, lastName: String = "Hanks"): String {
        return "$firstName $lastName"
    }

}

fun main() {
    Functions().main()
}
