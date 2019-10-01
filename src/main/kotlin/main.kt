import calculator.Calculator
import models.Labels
import reader.Reader

fun main() {
    val reader = Reader()
    val calculator = Calculator()

    @Suppress("PrivatePropertyName")
    val TestCSVPath = "/Users/thorwessel/Documents/testCHexport.csv"

    if (!reader.testAndSetPath(TestCSVPath)) println("Check the path, something is wrong!")

    val createdList = reader.numberOfTicketsCreated("9")

    println(calculator.getReport(
        createdList,
        reader.numberOfTicketsCompleted("9")
    ))

    val labelsMap = mutableMapOf<Labels, Int>()
    Labels.values().forEach {
        val listWithLabel = reader.numberOfTicketsWithLabel(it, createdList)
        val result = calculator.getLabelReport(listWithLabel)

        labelsMap[it] = result
    }

    println(labelsMap)

}