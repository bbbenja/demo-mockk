import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ExempleServiceTest {
    @MockK
    lateinit var subService1: ExampleSubService1

    @MockK
    lateinit var subService2: ExampleSubService2

    @InjectMockKs
    var exampleService = ExempleService()

    @Test
    fun `assert that subservices are mockked`(){
        every { subService1.subservice1Action() } returns "1"
        every { subService2.subservice2Action() } returns "2"

        assertThat(exampleService.concat()).isEqualTo("1_2")
    }

}