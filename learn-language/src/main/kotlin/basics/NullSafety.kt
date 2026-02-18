package basics

/*
Kotlin의 null safety 개념에서 사용하는 문법은 자바스크립트의 optional chaining과 유사
*/

fun main() {
    NullSafety().safeCalls()
    NullSafety().casting()
}

class NullSafety {

    fun typing() {
        // neverNull has String type
        var neverNull: String = "This can't be null"
        // Like below, trying to store null in a not-nullable variable throws a compiler error
        // neverNull = null

        // How to type a nullable String
        var nullable: String? = "You can keep a null here"
        // This is OK
        nullable = null

        // By default, null values aren't accepted
        var inferredNonNull = "The compiler assumes non-nullable"
        // Below throws a compiler error because inferred type is not nullable
        // inferredNonNull = null
    }

    fun safeCalls() {
        // Given a nullable DummyClass object
        val dummy: DummyClass? = null

        // Use [?.] to safely access nullable properties or call methods on nullable objects
        val dummyName: String? = dummy?.name // Nested nullable properties can be changed with sequential [?.] syntax
        println(dummyName) // null

        dummy?.sayHi() // Nothing happens, but code still compiles
    }

    fun elvisOperator() {
        /*
        Elvis operator = ?:

        Used to return a default value if the result is null
        */

        val nullString: String? = null
        println(nullString?.length ?: 0) // 0
    }

    fun casting() {
        /*
        Safe cast operator = as?

        Used to safely cast an object to a different type, returning null if the cast is not possible
        */

        val mysteryDummy: DummyClass? = null

        // Safe cast to DummyClass
        val dummyAsDummyClass = mysteryDummy as? DummyClass
        println(dummyAsDummyClass) // null

        // Use with Elvis operator to provide a default value if the cast fails
        val dummyOrDefault = mysteryDummy as? DummyClass ?: DummyClass("Default", 0)
        println(dummyOrDefault.name) // Default
    }

}

class DummyClass(val name: String, val age: Int) {
    fun sayHi() {
        println("hello, world")
    }
}
