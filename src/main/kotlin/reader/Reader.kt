package reader

import exceptions.NoPathSet
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

    fun numberOfTickets(): Int {
        if (CSVPath == "") throw NoPathSet()
        val reader = Files.newBufferedReader(Paths.get(CSVPath))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        var numberOfRows = 0
        csvParser.forEach {
            numberOfRows += 1
        }

        return numberOfRows
    }

}