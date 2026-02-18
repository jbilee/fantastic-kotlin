package basics

fun main() {
    exampleWithContact()
    exampleWithPet()
    exampleWithVehicle()
}

fun exampleWithContact() {
    // 인스턴스화
    val contact = Contact("Tom", "tom@gmail.com")

    // 프로퍼티 값 접근
    println(contact.name) // Tom
    println(contact.email) // tom@gmail.com

    // 프로퍼티 값 변경
    contact.email = "tomhanks@gmail.com"
    println(contact.email) // tomhanks@gmail.com
}

fun exampleWithPet() {
    val petDog = Pet("Buddy", "Sheltie")

    // private 프로퍼티는 접근 불가
    // println(petDog.title)

    // Member function(메서드) 호출
    petDog.printTitle() // Buddy the Sheltie

    petDog.printAge() // 0
    petDog.increaseAge()
    petDog.printAge() // 1
}

fun exampleWithVehicle() {
    val myPet = Pet("Dolly", "Ragdoll")
    val myCar = Vehicle("Cayenne", "Porsche")

    // Data class comes with predefined member functions: toString(), equals()/==, copy()
    println(myCar.toString()) // Vehicle(model=Cayenne, manufacturer=Porsche)
    println(myPet.toString()) // (Prints reference to the class instance)

    val anotherMyCar = Vehicle("Cayenne", "Porsche")
    val differentCar = Vehicle("Macan", "Porsche")

    // Equality check
    println(myCar.equals(anotherMyCar)) // true
    println(myCar.equals(differentCar)) // false
    println(myCar == anotherMyCar) // true
    println(myCar == differentCar) // false

    // copy() 함수
    println(myCar.copy()) // Vehicle(model=Cayenne, manufacturer=Porsche)
    // copy할 때 프로퍼티 값을 바꿀 수 있음
    val copiedCar = myCar.copy("Panamera")
    println(copiedCar) // Vehicle(model=Panamera, manufacturer=Porsche)
}

/*
클래스를 설계하는 다양한 방법
*/
class Contact(val name: String, var email: String) // Class header만 정의하는 방식, can omit braces { } (Kotlin이 생성자 만들어줌)

// Class header 대신 클래스 본문에서 프로퍼티 선언하는 방식 (same effect as Contact above)
// 객체 생성할 때 validation logic이 필요한 경우 유용
class Contact2(name: String, email: String) {
    val name: String = name
    var email: String = email
}

class Pet(val name: String, val species: String) {
    // { } 안에 객체의 상태(프로퍼티)를 선언할 경우 초기화를 해줘야 함
    private var age: Int = 0 // 이렇게 하면 사용자가 직접 age 값을 지정할 수 없게 됨
    private val title: String

    // init 블럭에서 프로퍼티 초기화 가능
    init {
        title = "$name the $species" // 인자로 받은 생성자 값을 활용해 상태로 저장
    }

    fun printTitle() {
        println(title)
    }

    fun printAge() {
        println(age)
    }

    fun increaseAge() {
        age++
    }
}

// 데이터 저장 전용 클래스
data class Vehicle(val model: String, val manufacturer: String)
