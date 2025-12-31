package basics

class Collections {

    fun lists() {
        // Read only list with explicit type declaration
        val readOnlyShapes: List<String> = listOf("triangle", "square", "circle")

        // Not possible:
        // readOnlyShapes.add("trapezoid")

        // Mutable list with explicit type declaration
        val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
        println(shapes) // [triangle, square, circle]
        shapes.add("trapezoid")
        println(shapes) // [triangle, square, circle, trapezoid]

        // Can "lock" a mutable list by storing it inside a variable declared as immutable List
        val lockedShapes: List<String> = shapes

        // Access element using brackets []
        val oneShape: String = readOnlyShapes[1]
        println(oneShape) // square

        // in 키워드로 요소가 리스트에 존재하는지 확인
        println("circle" in readOnlyShapes) // true
        println("parallelogram" in readOnlyShapes) // false
    }

    fun sets() {
        // Read-only set
        val readOnlyFruits: Set<String> = setOf("apple", "banana", "cherry", "cherry")

        // Not possible
        // readOnlyFruits.add("kiwi")

        // Mutable set with explicit type declaration
        val fruits: MutableSet<String> = mutableSetOf("apple", "banana", "cherry", "cherry")

        println(fruits) // [apple, banana, cherry]
        fruits.add("kiwi")
        println(fruits) // [apple, banana, cherry, kiwi]
    }

    fun maps() {
        // Read-only map
        val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)

        // Not possible
        // readOnlyJuiceMenu.put("banana", 50)

        // Mutable map with explicit type declaration
        val juiceMenu: MutableMap<String, Int> = mutableMapOf("apple" to 100, "kiwi" to 190, "orange" to 100)

        println(juiceMenu) // {apple=100, kiwi=190, orange=100}
        juiceMenu.put("banana", 50)
        println(juiceMenu) // {apple=100, kiwi=190, orange=100, banana=50}

        // Access value using []
        val kiwiCount = readOnlyJuiceMenu["kiwi"]
        println(kiwiCount) // 190

        // Map에서 읽어오는 value를 변수에 저장할 때 정적 타이핑을 해줄 경우 ?를 포함시켜야 함
        val orangeCount: Int? = readOnlyJuiceMenu["orange"]
    }

}
