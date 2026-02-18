package intermediate

/*
Properties don't always store data. Getter로 받아올 때마다 값을 그때그때 computing해 반환하는 것도 property에 해당
값을 실제로 저장하고 있는 property는 backing field를 가지며, backing field는 field라는 특별한 identifier로 접근 가능
 - field는 property의 getter와 setter에서만 사용 가능
 - Backing field를 가지는 property는 Kotlin이 자동으로 get(), set()을 생성해줌--getter, setter는 유저가 customize 가

A property that doesn't have a backing field is called a computed property.
*/
fun main() {
    val user = User("Tom", 45)

    // 문법상으로는 그냥 property에 접근하지만 실제로는 해당 property의 get() 메서드가 호출되고 있음
    println(user.name) // Tom

    user.name = "Jerry" // 마찬가지로 실제로는 name의 set() 메서드가 호출되고 있음
    println(user.name) // Jerry
}

// Syntax for property getter and setter
class User(name: String, val age: Int) { // age의 경우 val이기 때문에 Kotlin이 get()만 자동으로 생성해줌
    // 아래 get(), set()은 Kotlin default
    var name: String = name
        get() = field
        set(value) {
            field = value
        }
}

/*
get(), set()은 언제 유용한가?
Validation 로직이 필요할 때 혹은 가공된 값을 반환해야 할 때
*/
class CustomizedUser(name: String, val age: Int) {
    var name: String = name
        // Always ensures the returned name is capitalized
        get() = field.replaceFirstChar { firstChar -> firstChar.uppercase() }
        // Validates that the name is not blank before setting it
        set(value) {
            require(value.isNotBlank())
            field = value
        }
}

class UserWithComputedProperty(var name: String, val age: Int) {
    // 이 property는 access될 때 age가 18 이상인지 여부를 계산해서 반환 (따라서 backing field가 없고 initialization이 필요 없음)
    val isAdult: Boolean
        get() = age >= 18
}
