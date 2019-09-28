package readerTest

import org.junit.jupiter.api.Test
import reader.Reader

class ReaderTest {

    private val path = "C:\\Users\\thor_\\IdeaProjects\\CHMetrics/CHExport.csv"

    @Test
    fun `Reader return false when CSV file path is invalid`() {
        val testReader = Reader()

        assert(!testReader.testAndSetPath(""))
    }

    @Test
    fun `Reader opens CSV and find number of entries minus header row`() {
        // This requires a CSV file to be present!
        // Test file right now is 612 lines in total without the header
        val testReader = Reader()

        assert(testReader.numberOfTickets(path) == 612)
    }

    @Test
    fun `Reader finds number of entries in specific period`() {
        val testReader = Reader()


    }
}