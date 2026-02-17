package intermediate

fun main() {
    val lambda = Lambda()

    lambda.main()
    lambda.syntaxWithoutReceiver()
    lambda.syntaxWithReceiver()
}

class Lambda {

    fun main() {
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
    람다식은 receiver도 가질 수 있음--receiver를 받아 문법에서 반복적인 객체 선언을 줄일 수 있음
    즉, lambda expression can access member functions and properties without having to specify the object
    */
    fun syntaxWithoutReceiver() {
        // draw()라는 람다식을 받을 때, 해당 람다식의 타이핑
        fun render(draw: (Whiteboard) -> Unit) {
            val whiteboard = Whiteboard()
            draw(whiteboard)
        }

        // 람다식을 전달할 때 반드시 Whiteboard를 파라미터로 명시적 표현해야 함
        render { whiteboard ->
            whiteboard.drawCircle() // 🟠 Drawing a circle
            whiteboard.drawText("hi") // Printing text: hi
        }
    }

    fun syntaxWithReceiver() {
        // draw() 람다식을 타이핑할 때 (Whiteboard) -> Unit을 Whiteboard.() -> Unit으로 표현 가능 (receiver 형태)
        fun render(draw: Whiteboard.() -> Unit) {
            val whiteboard = Whiteboard()
            whiteboard.draw()
        }

        // Receiver 형태이기 때문에 파라미터 명시 없이 객체의 메서드만 호출 가능
        render {
            drawCircle() // 🟠 Drawing a circle
            drawText("hi") // Printing text: hi
        }
    }

}

class Whiteboard {
    fun drawCircle() = println("🟠 Drawing a circle")
    fun drawText(text: String) = println("Printing text: $text")
}
