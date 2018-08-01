class ExempleService {
    lateinit var subservice1: ExampleSubService1
    lateinit var subservice2: ExampleSubService2

    fun concat() = subservice1.subservice1Action() + "_" + subservice2.subservice2Action()
}

class ExampleSubService1 {
    fun subservice1Action() = "subservice1Action"
}

class ExampleSubService2 {
    fun subservice2Action() = "subservice2Action"
}
