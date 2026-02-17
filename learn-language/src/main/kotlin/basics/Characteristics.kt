package basics

/*
Kotlin은:
 - 타입 추론 가능
 - 세미콜론 생략 가능
 - 개발자가 클래스를 명시적으로 생성하지 않아도 됨 (함수만 작성해도 컴파일러가 클래스를 대신 만들어줌)
   - Kotlin 컴파일러가 파일명을 기반으로 클래스를 생성함 (예: Characteristics.kt -> CharacteristicsKt.class 생성)
*/

// 클래스 없이 함수만 있는 .kt 파일 작성 가능
fun main() {
    println("hello, world")
}

// 한 .kt 파일 안에 자유롭게 함수와 클래스 구현 가능
class Hello() {
    fun sayHi() {
        println("hello, world")
    }
}
