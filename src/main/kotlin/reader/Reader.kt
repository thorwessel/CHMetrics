package reader

import java.nio.file.*
import java.nio.file.Paths
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser


class Reader {
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

    fun numberOfTickets(path: String): Int {
        val reader = Files.newBufferedReader(Paths.get(path))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        var numberOfRows = 0
        csvParser.forEach {
            numberOfRows += 1
        }

        return numberOfRows
    }

}