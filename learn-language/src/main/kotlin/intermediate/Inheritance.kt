package intermediate

/*
상속은 [:] 문법을 사용 -> class ChildClass : ParentClass { ... }

클래스는 single inheritance(단일 상속)만 지원하고, 인터페이스는 다수 상속 지원

모든 클래스의 최상위 부모: Any
 - Any는 기본으로 toString() 메서드를 지원

class 키워드만 가진 클래스는 final이 자동으로 붙기 때문에, 명시적으로 "open"이나 "abstract" 키워드를 추가해야 다른 클래스가 상속할 수 있음
Note: "open" 키워드로 상속이 가능하도록 열어둔 클래스의 멤버 함수는 기본적으로 override가 불가함--멤버 함수에도 "open" 키워드를 포함시켜야 자식 클래스가 override할 수 있음
*/

fun main() {
    val dog = Dog()
    dog.makeSound() // woof

    val cat = Cat("meowww")
    cat.makeSound() // meowww

    val bus = Bus("Tom Hanks")
    bus.makeSound() // vrooom
    bus.printOwner() // Owned by: Tom Hanks

    val canvasSession = CanvasSession(PenTool())
    canvasSession.draw("square") // Drawing square using a pen in black
}

/*
abstract 클래스 활용법
 */
abstract class Animal {
    abstract val sound: String

    abstract fun makeSound()
}

// abstract class 상속
class Dog : Animal() {
    override val sound: String = "woof"

    override fun makeSound() {
        println(sound)
    }
}

// 또는 constructor injection도 가능 (constructor에 다이렉트로 프로퍼티 값 전달)
// But this is a potential design smell: Why is the caller allowed to decide what sound the Cat makes?
class Cat(override val sound: String = "meow") : Animal() {
    override fun makeSound() {
        println(sound)
    }
}

/*
interface 활용법
 */
interface Vehicle {
    fun makeSound()
}

interface Asset {
    val owner: String
    fun printOwner()
}

class Bus(override val owner: String) : Vehicle, Asset {
    override fun makeSound() {
        println("vrooom")
    }

    override fun printOwner() {
        println("Owned by: $owner")
    }
}

/*
interface delegation 활용법

Delegation uses the "by" keyword to allow a class to delegate function calls to its delegate that implements the same interface
*/

// Given an interface
interface DrawingTool {
    val color: String
    fun draw(shape: String)
    fun erase(area: String)
    fun getToolInfo(): String
}

// PenTool will function as the delegate
class PenTool : DrawingTool {
    override val color: String = "black"

    override fun draw(shape: String) {
        println("Drawing $shape using a pen in $color")
    }

    override fun erase(area: String) {
        println("Erasing $area with pen tool")
    }

    override fun getToolInfo(): String {
        return "PenTool(color=$color)"
    }
}

class CanvasSession(val tool: DrawingTool) : DrawingTool by tool {
    override val color: String = "blue"

    // CanvasSession doesn't need to implement the functions described by DrawingTool--calling these functions will
    // automatically call PenTool's implementations
}

/*
What the above avoids = meaningless boilerplate code

Example below is without using delegation--CanvasSession has to override all the interface functions, but they're simply
calling the same functions implemented by `tool`

class CanvasSession(val tool: DrawingTool) : DrawingTool {
    override val color: String = "blue"

    override fun draw(shape: String) {
        tool.draw(shape)
    }

    override fun erase(area: String) {
        tool.erase(area)
    }

    override fun getToolInfo(): String {
        return tool.getToolInfo()
    }
}
*/
