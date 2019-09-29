package reader

import exceptions.NoPathSet
import java.nio.file.*
import java.nio.file.Paths
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.joda.time.DateTime
import java.text.ParseException
import java.text.SimpleDateFormat

class Reader {
    private val createdAtRowIndex = 7
    private val completedAtRowIndex = 11

    @Suppress("PrivatePropertyName")
    private var CSVPath = ""

    fun testAndSetPath(path: String): Boolean {
        try {
            Files.newBufferedReader(Paths.get(path))
            CSVPath = path
            return true
        } catch(e: Exception) {
            println(e.message)
        }
        return false
    }

    fun numberOfTicketsCreated(month: String = ""): List<CSVRecord> {
        val csvParser = getParsedCsv()

        return if (month == "") {
            csvParser.toList()
        } else {
            getNumberOfTicketsInMonth(csvParser, month, createdAtRowIndex)
        }
    }

    fun numberOfTicketsCompleted(month: String = ""): List<CSVRecord> {
        val csvParser = getParsedCsv()

        return if (month == "") {
            csvParser.filter { it[5] == "true" }
        } else {
            getNumberOfTicketsInMonth(csvParser, month, completedAtRowIndex)
        }
    }

    private fun getParsedCsv(): CSVParser {
        if (CSVPath == "") throw NoPathSet()
        val reader = Files.newBufferedReader(Paths.get(CSVPath))
        return CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    }

    private fun getNumberOfTicketsInMonth(csvParser: CSVParser, period: String, row: Int): List<CSVRecord> {
        val numberOfRows = mutableListOf<CSVRecord>()
        val rowMonthParser = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        csvParser.forEach {
            try {
                val dateFromRow = rowMonthParser.parse(it[row])
                if (DateTime(dateFromRow).monthOfYear().get() == period.toInt()) numberOfRows.add(it)
            } catch (e: ParseException) {
                //Date field is likely empty
            }
        }

        return numberOfRows.toList()
    }

}