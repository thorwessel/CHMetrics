package calculator

import org.apache.commons.csv.CSVRecord

class Calculator {

    fun getReport(createdList: List<CSVRecord>, completedList: List<CSVRecord>): Map<String, Int> {
        return mapOf(
            "Created" to createdList.count(),
            "Completed" to completedList.count()
        )
    }
}