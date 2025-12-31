package a

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

        // 람다식 선언 (파라미터를 괄호에 래핑하지 않음)
        val upperCaseString = { text: String -> text.uppercase() }
        // 람다식 사용
        val upperCased = upperCaseString("hello")
        println(upperCased) // HELLO

        // 파라미터가 없는 람다식
        val shoutout = {
            println("Kotlin")
            println("is")
            println("fantastic!")
        }
        shoutout()
        // 출력:
        // Kotlin
        // is
        // fantastic!

        // Typing a lambda expression (타입 선언에서는 괄호 필수, 람다식의 실제 파라미터에는 괄호 X)
        val multiply: (Int, Int) -> Int = { x: Int, y: Int -> x * y }
        // 파라미터가 없는 경우 타입 선언에서는 empty 괄호로 표현
        val returnHello: () -> String = { "Hello" }

        // 람다식 즉시 실행 (람다식 끝에 괄호 추가)
        println({ text: String -> text.uppercase() }("hello")) // HELLO

        // Trailing lambda = 람다식을 인자로 전달할 때, 해당 람다식이 유일한 함수형 파라미터일 경우 괄호 생략 가능
        val numbers = listOf(1, 10, 3, 6)
        val filteredNumbers = numbers.filter { x -> x > 5 } // filter()에서 괄호 생략됨
        println(filteredNumbers) // [10, 6]
    }

    /*
    * 함수 선언 시 파라미터의 타입과 return value의 타입을 반드시 명시해야 함
    *
    * 함수의 default return 타입은 Unit (unless inferred)
    * */
    private fun sum(x: Int, y: Int): Int {
        return x + y;
    }

    /*
    * 함수가 single-line일 경우 { } 생략 가능
    * 이때, return type 추론이 가능해서 return type 생략도 가능
    * */
    fun sumShortened(x: Int, y: Int) = x + y

    fun addPrefix(prefix: String, message: String): String {
        return "$prefix $message"
    }

    fun addLastName(firstName: String, lastName: String = "Hanks"): String {
        return "$firstName $lastName"
    }

}
