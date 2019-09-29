package calculatorTest

import calculator.Calculator
import exceptions.NoPathSet
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import models.Labels
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import reader.Reader
import java.nio.file.Files
import java.nio.file.Paths

class CalculatorTest {

    @Suppress("PrivatePropertyName")
    private val TestCSVPath = "C:\\Users\\thor_\\IdeaProjects\\CHMetrics/CHExport.csv"
    // Something is up with the "reader", can't seem to be used twice.
    private val reader1 = Files.newBufferedReader(Paths.get(TestCSVPath))
    private val parsedTest1 = CSVParser(reader1, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    private val reader2 = Files.newBufferedReader(Paths.get(TestCSVPath))
    private val parsedTest2 = CSVParser(reader2, CSVFormat.DEFAULT.withFirstRecordAsHeader())

    private val testCreatedList = parsedTest1.toList()
    private val testCompletedList = parsedTest2.filter { it[6] == "true"}

    @Test
    fun `Returns simple report of created and completed tickets within specific month`() {
        val readerMock = mockk<Reader> {
            every { numberOfTicketsCreated(any()) } returns testCreatedList.toList()
            every { numberOfTicketsCompleted(any()) } returns testCompletedList.toList()
        }
        val testCalculator = Calculator()
        val expectedResult = mapOf(
            "Created" to 612,
            "Completed" to 604
        )

        assert(testCalculator.getReport(readerMock.numberOfTicketsCreated(),
            readerMock.numberOfTicketsCompleted()) == expectedResult)
    }
}