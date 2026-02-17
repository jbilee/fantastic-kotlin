package intermediate

/*
"Scope functions allow you to create a temporary scope around an object."

There are 5 scope functions: let, apply, run, also, with
Scope functions (except [with]) are also extension functions.

왜 사용하나?
 - 스코프 내에서는 객체의 변수명을 반복해서 사용할 필요 없음, this나 it으로 대체
 - 객체를 다루는 함수 호출에 semantic meaning을 더해주고 코드 자체를 간소화시키는 것이 가능
*/

fun main() {
    UsingLet().main()
    UsingApply().main()
    UsingRun().main()
    UsingAlso().main()
    UsingWith().main()
}

/*
let

Nullable한 값의 null checking 방식을 간소화하고, 해당 값을 다루는 작업의 결과값을 변수에 저장해두고 싶을 때 유용
*/
class UsingLet {
    fun sendNotification(recipientAddress: String): String {
        println("Yo $recipientAddress!")
        return "Notification sent!"
    }

    fun getNextAddress(): String {
        return "sebastian@jetbrains.com"
    }

    fun main() {
        // Given a nullable value
        var address: String? = getNextAddress()
        // Below doesn't work, because sendNotification() doesn't accept nullable:
        // sendNotification(address)

        // 그렇다고 nullable한 값을 null대로 사용하지 않고 "null인 경우 다른 default String value로 대체"하게 되면 null의 의미가 사라짐
        // 🤔 address가 null일 경우 빈 문자열("")로 대체해서 non-nullable한 함수에 전달하는게 처음부터 nullable를 허용하는 의도에 부합하는가?

        // 따라서 다른 작업에서 반환한 nullable 값을 not-nullable한 인자로 전달하고 싶을 때, null checking 로직을 let으로 대체할 수 있음
        val firstConfirmation = address?.let {
            // let 스코프에서는 [it]을 사용 ([this] 사용 불가)
            sendNotification(it) // Yo sebastian@jetbrains.com!
        }
        // let 스코프에서 반환한 결과값을 firstConfirmation처럼 변수에 저장해두고 사용 가능
        println(firstConfirmation) // Notification sent!

        address = null
        val secondConfirmation = address?.let {
            sendNotification(it) // 이번엔 address 값이 null이기 때문에 본 함수가 invoke되지 않음
        }
        println(secondConfirmation) // null
    }
}

/*
apply

객체를 인스턴스화할 때 처리해야 하는 후속 작업들을 apply 스코프 내에서 처리하여, 프로퍼티 접근이나 함수 호출 시 인스턴스 변수를 포함시키지 않아도 되도록 함
*/
class UsingApply {
    fun main() {
        withoutApply()
        withApply()
    }

    fun withoutApply() {
        // 객체 생성 후
        val client = Client()

        // 프로퍼티 값 설정 및 메서드 호출에 [client.] 부분이 반복됨
        client.token = "token"
        client.connect() // connected!
        client.authenticate() // authenticated!
    }

    fun withApply() {
        // apply 스코프 내에서 전부 처리, [client.] 필요 없음
        val client = Client().apply {
            token = "token"
            connect() // connected!
            authenticate() // authenticated!
        }
    }
}

class Client {
    var token: String? = null
    fun connect() = println("connected!")
    fun authenticate() = println("authenticated!")
    fun getData(): String = "sample"
}

/*
run

apply와 유사, 인스턴스의 메서드들을 한 스코프 내에 묶어서 처리하도록 함
스코프 내에서 발생한 결과값 반환 가능
반복적으로 호출해야 하는 메서드를 그룹핑하기에 용이
*/
class UsingRun {
    val client = Client()

    fun main() {
        val result: String = client.run {
            connect() // connected!
            authenticate() // authenticated!
            getData()
        }
        println(result) // sample
    }
}

/*
also

타겟 객체를 활용하는 추가적인 작업을 수행하고, 해당 객체를 다시 반환해야 할 때 유용
예: 체인 작업을 수행하는 도중 로깅을 해야 할 때

스코프 내에서 this가 아닌 it을 사용 (this는 상위 caller의 객체를 가리킴--아래 예시에서는 UsingAlso 인스턴스가 this가 됨)
*/
class UsingAlso {
    fun main() {
        val medals: List<String> = listOf("Gold", "Silver", "Bronze")
        val reversedLongUppercaseMedals: List<String> =
            medals
                .map { it.uppercase() }
                .also { println(it) } // [GOLD, SILVER, BRONZE]
                .filter { it.length > 4 }
                .also { println(it) } // [SILVER, BRONZE]
                .reversed()
        println(reversedLongUppercaseMedals) // [BRONZE, SILVER]
    }
}

/*
with

멤버 함수를 호출할 때 멤버(객체)를 반복해서 작성할 필요 없도록 함
Extension function이 아니어서 대상을 인자로 전달해야 함
with 스코프에서는 this를 사용
*/
class UsingWith {
    fun main() {
        withoutWith()
        withWith()
    }

    fun withoutWith() {
        val mainMonitorPrimaryBufferBackedCanvas = Canvas()

        mainMonitorPrimaryBufferBackedCanvas.text(10, 10, "Foo") // 10, 10, Foo
        mainMonitorPrimaryBufferBackedCanvas.rect(20, 30, 100, 50) // 20, 30, 100, 50
        mainMonitorPrimaryBufferBackedCanvas.circ(40, 60, 25) // 40, 60, 25
    }

    fun withWith() {
        val mainMonitorPrimaryBufferBackedCanvas = Canvas()

        with(mainMonitorPrimaryBufferBackedCanvas) {
            text(10, 10, "Foo") // 10, 10, Foo
            rect(20, 30, 100, 50) // 20, 30, 100, 50
            circ(40, 60, 25) // 40, 60, 25
        }
    }
}

class Canvas {
    fun rect(x: Int, y: Int, w: Int, h: Int): Unit = println("$x, $y, $w, $h")
    fun circ(x: Int, y: Int, rad: Int): Unit = println("$x, $y, $rad")
    fun text(x: Int, y: Int, str: String): Unit = println("$x, $y, $str")
}
