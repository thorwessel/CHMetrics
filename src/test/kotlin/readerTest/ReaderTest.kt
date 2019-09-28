package readerTest

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import reader.Reader

class ReaderTest {

    @Test
    fun `Reader throws error when CSV file is not found`() {
        val testReader = Reader()

        assertThrows<java.nio.file.AccessDeniedException> { testReader.testPath("") }
    }
}