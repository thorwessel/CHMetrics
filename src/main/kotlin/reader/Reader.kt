package reader

import exceptions.NoPathSet
import java.nio.file.*
import java.nio.file.Paths
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
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

    fun numberOfTicketsCreated(month: String = ""): Int {
        val csvParser = getParsedCsv()

        return if (month == "") {
            csvParser.count()
        } else {
            getNumberOfTicketsInMonth(csvParser, month, createdAtRowIndex)
        }
    }

    fun numberOfTicketsCompleted(month: String = ""): Int {
        val csvParser = getParsedCsv()

        return if (month == "") {
            csvParser.filter {
                it[5] == "true"
            }.count()
        } else {
            getNumberOfTicketsInMonth(csvParser, month, completedAtRowIndex)
        }
    }

    private fun getParsedCsv(): CSVParser {
        if (CSVPath == "") throw NoPathSet()
        val reader = Files.newBufferedReader(Paths.get(CSVPath))
        return CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
    }

    private fun getNumberOfTicketsInMonth(csvParser: CSVParser, period: String, row: Int): Int {
        var numberOfRows = 0
        val rowMonthParser = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        csvParser.forEach {
            try {
                val dateFromRow = rowMonthParser.parse(it[row])
                if (DateTime(dateFromRow).monthOfYear().get() == period.toInt()) numberOfRows += 1
            } catch (e: ParseException) {
                //Date field is likely empty
            }
        }

        return numberOfRows
    }

}