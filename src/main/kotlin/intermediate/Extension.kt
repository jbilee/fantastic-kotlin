package intermediate

/*
Extension function = add-on functions that we can declare ourselves to any existing class

사용법:
 - 일반적인 함수를 작성하는 것처럼 fun 키워드 사용
 - Extension function을 추가하고자 하는 클래스명에 원하는 함수명을 .로 덧붙임
 - 해당 함수의 동작을 작성

Extension function은 해당 클래스의 내부 메서드가 되는게 아니라, static 메서드로 추가됨 (this를 쓸 수 있는 건 인스턴스를 인자로 받아서 가능)
따라서 extension function은 클래스의 private/protected 프로퍼티나 메서드에 접근할 수 없음
 ㄴ 왜 그렇게 설계됐는가? -> 기존 API를 break 혹은 exploit하지 않고 추가적인 기능을 더할 수 있도록 하기 위해
*/

class Developer(val name: String) {
    fun writeCode() {
        println("$name has written some code")
    }
}

// Extended function
fun Developer.deployCode(): Unit {
    println("${this.name} has deployed the code")
}

// Extended function as a one-liner
fun Developer.getTitle() = "$name the Developer" // this 키워드 없이 다이렉트로 프로퍼티 접근 가능 (private/protected일 경우 접근 불가)

fun main() {
    val dev = Developer("Tom")

    println(dev.getTitle()) // Tom the Developer
    dev.writeCode() // Tom has written some code
    dev.deployCode() // Tom has deployed the code
}
