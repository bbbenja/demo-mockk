class ComplexService {
    fun obj(): Obj = Obj("test")

    fun publicObj() = privateObj()
    private fun privateObj() = "private result"

    fun objFromlist() = "value1"

    inner class Obj(
            private val objValue: String
    ) {
        fun objAction1(): String = this.objValue + "_objAction1"
    }
}