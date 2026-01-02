package basics

class ControlFlow {

    /*
    * Kotlin has two conditionals: 'if' and 'when'
    * Kotlin recommends using 'when' over 'if' when applicable because:
    *  - Makes code easier to read.
    *  - Makes it easier to add another branch.
    *  - Leads to fewer mistakes.
    *
    * Kotlin has no ternary operator
    * */
    fun conditionals() {
        // 1. Using if
        var age: Int = 17
        var isUnderage: Boolean

        if (age < 18) {
            isUnderage = true
        } else {
            isUnderage = false
        }

        println(isUnderage) // true

        // 또는 if문의 결과를 변수에 바로 저장
        isUnderage = if (age < 18) {
            true
        } else {
            false
        }

        // 로직이 한 줄일 경우 간소화된 표현 가능
        age = 20
        isUnderage = if (age < 18) true else false

        println(isUnderage) // false

        // 2. Using when (switch문과 유사)
        val animal: String = "dog"

        when (animal) {
            "dog" -> println("mammal")
            "frog" -> println("amphibian")
            else -> println("unknown")
        }

        // when 조건의 결과를 다이렉트로 저장 가능
        val classification: String = when (animal) {
            "dog" -> "mammal"
            "frog" -> "amphibian"
            else -> "unknown"
        }
        println(classification) // mammal

        // 조건 대상 없이 사용 가능
        val size: String = when {
            animal == "dog" -> "big"
            animal == "frog" -> "small"
            else -> "unknown"
        }
        println(size) // big
    }

    /*
    * Kotlin에서는 '..', '..<', 'downTo'로 범위를 표현
    *
    * Inclusive한 범위:
    * 1..4 = 1, 2, 3, 4
    *
    * Exclusive한 범위:
    * 1..<4 = 1, 2, 3
    *
    * 거꾸로 나열:
    * 'z' downTo 'v' = 'z', 'y', 'x', 'w', 'v'
    *
    * n씩 건너뛰기:
    * 1..5 step 2 = 1, 3, 5
    * */
    fun loops() {
        // 1. for 반복문
        for (number in 1..<5) {
            print(number)
        }
        // 출력: 1234
        println()

        for (letter in 'z' downTo 't') {
            print(letter)
        }
        // 출력: zyxwvut
        println()

        val cakes = listOf("carrot", "cheese", "chocolate")

        for (cake in cakes) {
            println("Yummy, it's a $cake cake!")
        }
        // 출력:
        // Yummy, it's a carrot cake!
        // Yummy, it's a cheese cake!
        // Yummy, it's a chocolate cake!

        // 2. while 반복문
        var cakesEaten = 0
        while (cakesEaten < 3) {
            println("Eat a cake")
            cakesEaten++
        }
        // 출력:
        // Eat a cake
        // Eat a cake
        // Eat a cake

        // 3. do-while 반복문
        var cakesBaked = 0
        do {
            println("Bake a cake")
            cakesBaked++
        } while (cakesBaked < cakesEaten)
        // 출력:
        // Bake a cake
        // Bake a cake
        // Bake a cake
    }

}
