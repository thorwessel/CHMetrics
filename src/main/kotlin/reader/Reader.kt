package reader

import exceptions.NoPathSet
import java.nio.file.*
import java.nio.file.Paths
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.joda.time.DateTime
import java.text.SimpleDateFormat


class Reader {
    private val createdAtRowIndex = 7

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

    fun numberOfTickets(month: String = ""): Int {
        if (CSVPath == "") throw NoPathSet()

        val reader = Files.newBufferedReader(Paths.get(CSVPath))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())

        return if (month == "") {
            csvParser.count()
        } else {
            getNumberOfTicketsInMonth(csvParser, month)
        }
    }

    private fun getNumberOfTicketsInMonth(csvParser: CSVParser, period: String): Int {
        var numberOfRows = 0
        val rowMonthParser = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        csvParser.forEach {
            val dateFromRow = rowMonthParser.parse(it[createdAtRowIndex])
            if (DateTime(dateFromRow).monthOfYear().get() == period.toInt()) numberOfRows += 1
        }

        return numberOfRows
    }

}