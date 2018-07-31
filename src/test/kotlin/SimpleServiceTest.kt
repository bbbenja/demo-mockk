import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import jdk.nashorn.internal.ir.annotations.Ignore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * https://github.com/mockk/mockk
 */
class SimpleServiceTest {

    private val simpleService = SimpleService()

    @MockK
    lateinit var mockSimpleService: SimpleService

    @BeforeEach
    fun reset() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `assert action1 return right value`() {
        assertThat(simpleService.action1()).isEqualTo("_action1")
    }

    @Test
    fun `assert action2 return right value`() {
        assertThat(simpleService.action2("test")).isEqualTo("test_action2")
    }

    @Test
    @Ignore
    fun `assert action1 with mock - failure`() {
        assertThat(mockSimpleService.action1()).isEqualTo("_action1Mock")
    }

    @Test
    fun `assert action1 with mock`() {
        every { mockSimpleService.action1() } returns "_action1Mock"
        assertThat(mockSimpleService.action1()).isEqualTo("_action1Mock")

    }

    @Test
    fun `assert action2 with strict mock`() {
        every { mockSimpleService.action2("test") } returns "test_action2Mock"
        assertThat(mockSimpleService.action2("test")).isEqualTo("test_action2Mock")
    }

    @Test
    fun `assert action2 with any mock`() {
        every { mockSimpleService.action2(any()) } returns "any_action2Mock"
        assertThat(mockSimpleService.action2("any")).isEqualTo("any_action2Mock")
    }

    @Test
    fun `assert action2 with captured mock`() {
        val slot = slot<String>()
        every { mockSimpleService.action2(capture(slot)) } answers { slot.captured + "_action2Mock" }
        assertThat(mockSimpleService.action2("slot")).isEqualTo("slot_action2Mock")
    }

    /**
     * https://github.com/mockk/mockk#matchers
     */
    @Test
    fun `assert action3 with partial argument mock`() {
        every { mockSimpleService.action3(more(30)) } returns "moreThan30_action3Mock"
        every { mockSimpleService.action3(less(30)) } returns "lessThan30_action3Mock"
        assertThat(mockSimpleService.action3(44)).isEqualTo("moreThan30_action3Mock")
        assertThat(mockSimpleService.action3(22)).isEqualTo("lessThan30_action3Mock")
    }
}