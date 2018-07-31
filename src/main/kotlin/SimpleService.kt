class SimpleService {
    fun action1(): String = "_action1"
    fun action2(param1: String) = param1 + "_action2"
    fun action3(param1: Number) = param1.toString() + "_action3"
    fun action4() {
        subaction1()
        subaction2()
        subaction1()
    }

    fun subaction1() {
        println("do something")
    }

    fun subaction2() {
        println("do other something")
    }
}