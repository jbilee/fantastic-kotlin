package a

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

}
