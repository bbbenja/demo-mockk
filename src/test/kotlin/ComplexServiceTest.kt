import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ComplexServiceTest {

    @MockK
    lateinit var mockComplexService: ComplexService

    @SpyK
    var spyComplexService = ComplexService()

    @Test
    fun `assert chained mock`() {
        every { mockComplexService.obj().objAction1() } returns "myTest_objAction1Mock"
        assertThat(mockComplexService.obj().objAction1()).isEqualTo("myTest_objAction1Mock")
    }

    @Test
    fun `assert private fun is accessible`() {
        every { spyComplexService["privateObj"]() } returns "private_mock"
        assertThat(spyComplexService.publicObj()).isEqualTo("private_mock")
    }

    @Test
    fun `assert list returns different values`() {
        every { mockComplexService.objFromlist() } returnsMany listOf("value1","value2","value3")
        assertThat(mockComplexService.objFromlist()).isEqualTo("value1")
        assertThat(mockComplexService.objFromlist()).isEqualTo("value2")
        assertThat(mockComplexService.objFromlist()).isEqualTo("value3")
    }


}