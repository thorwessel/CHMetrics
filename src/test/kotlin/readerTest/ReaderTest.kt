package readerTest

import exceptions.NoPathSet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import reader.Reader
import java.text.SimpleDateFormat;
import java.util.Date;

class ReaderTest {
    // Test CSV file
    @Suppress("PrivatePropertyName")
    private val TestCSVPath = "C:\\Users\\thor_\\IdeaProjects\\CHMetrics/CHExport.csv"
    //Test file right now is 612 lines in total without the header
    private val numberOfEntries = 612

    @Test
    fun `Reader return false when CSV file path is invalid`() {
        val testReader = Reader()

        assert(!testReader.testAndSetPath(""))
    }

    @Test
    fun `Reader opens CSV and find number of entries minus header row`() {
        val testReader = Reader()
        testReader.testAndSetPath(TestCSVPath)

        assert(testReader.numberOfTickets() == numberOfEntries)
    }

    @Test
    fun `Reader throws NoPathSet exception if path has not been set`() {
        val testReader = Reader()

        assertThrows<NoPathSet> { testReader.numberOfTickets() }
    }

    @Test
    fun `Reader returns number of tickets within specific month`() {
        val testReader = Reader()
        testReader.testAndSetPath(TestCSVPath)
        println(testReader.numberOfTickets("Jun"))


        assert(testReader.numberOfTickets("Jun") == 12)
    }
}