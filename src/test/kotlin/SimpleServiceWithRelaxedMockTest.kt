import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SimpleServiceWithRelaxedMockTest {

    @RelaxedMockK
    lateinit var mockSimpleService: SimpleService

    @SpyK
    var spySimpleService = SimpleService()

    @Test
    fun `assert action1 with no mock do not fail`() {
        assertThat(mockSimpleService.action2("any")).isEqualTo("")
    }

    @Test
    fun `verify action4 calls`() {
        every { spySimpleService.subaction1() } just Runs
        every { spySimpleService.subaction2() } just Runs

        spySimpleService.action4()

        verify(exactly = 2) { spySimpleService.subaction1() }
        verify { spySimpleService.subaction2() }
    }

    @Test
    fun `verify action4 calls order`() {
        spySimpleService.action4()

        verifyOrder {
            spySimpleService.subaction1()
            spySimpleService.subaction2()
            spySimpleService.subaction1()
        }
    }


}