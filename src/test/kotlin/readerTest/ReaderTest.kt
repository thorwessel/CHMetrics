package readerTest

import exceptions.NoPathSet
import models.Labels
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import reader.Reader

class ReaderTest {
    // Test CSV file
    @Suppress("PrivatePropertyName")
    private val TestCSVPath = "/Users/thorwessel/Documents/testCHexport.csv"
    // MAC: private val TestCSVPath = "/Users/thorwessel/Documents/testCHexport.csv"
    // PC: private val TestCSVPath = "C:\\Users\\thor_\\IdeaProjects\\CHMetrics/CHExport.csv"

    //Test file right now is 612 lines in total without the header
    private val numberOfEntries = 612

    @Test
    fun `Reader return false when CSV file path is invalid`() {
        val testReader = Reader()

        assert(!testReader.testAndSetPath("random"))
    }

    @Test
    fun `Reader opens CSV and return list of entries minus header row`() {
        val testReader = Reader()
        testReader.testAndSetPath(TestCSVPath)

        assert(testReader.numberOfTicketsCreated().count() == numberOfEntries)
    }

    @Test
    fun `Reader throws NoPathSet exception if path has not been set`() {
        val testReader = Reader()

        assertThrows<NoPathSet> { testReader.numberOfTicketsCreated() }
    }

    @Test
    fun `Reader returns list of created tickets within specific month`() {
        val testReader = Reader()
        testReader.testAndSetPath(TestCSVPath)

        assert(testReader.numberOfTicketsCreated(6).count() == 63)
    }

    @Test
    fun `Reader opens CSV and return list of completed tickets minus header row`() {
        val testReader = Reader()
        testReader.testAndSetPath(TestCSVPath)

        assert(testReader.numberOfTicketsCompleted().count() == 604)
    }

    @Test
    fun `Reader returns list of completed tickets with specific month `() {
        val testReader = Reader()
        testReader.testAndSetPath(TestCSVPath)

        assert(testReader.numberOfTicketsCompleted(6).count() == 45)
    }

    @Test
    fun `Reader returns list of tickets with specific label`() {
        val testReader = Reader()
        testReader.testAndSetPath(TestCSVPath)
        val listOfTickets = testReader.numberOfTicketsCreated(1)

        assert(testReader.numberOfTicketsWithLabel(Labels.WALLET_LOAD, listOfTickets).count() == 3)
    }
}