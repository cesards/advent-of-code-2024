import java.util.regex.Pattern

fun main() {
    val lines = readInput("Day03-Input")
    part01(lines)
    part02(lines)
}

private fun part01(lines: List<String>) {
    val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    var sum = 0L
    lines.map { line ->
        regex.findAll(line).forEach {
            val aha = it.value.substringBefore(",").substringAfter("(").toInt() *
                    it.value.substringAfter(",").substringBefore(")").toInt()
            sum += aha
        }
    }
    println(sum)
}

private fun part02(lines: List<String>) {
    val donts = Regex("don't\\(\\)(.*?)do\\(\\)")
    val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")

    // We need to put lines together if we want to make this work.
    val word = StringBuilder()
    lines.forEach { line -> word.append(line) }

    val pattern = Pattern.compile(donts.pattern)
    val processedLine = pattern.matcher(word.toString()).replaceAll("")

    var sum = 0L
    regex.findAll(processedLine).forEach {
        val aha = it.value.substringBefore(",").substringAfter("(").toInt() *
                it.value.substringAfter(",").substringBefore(")").toInt()
        sum += aha
    }
    println(sum)
}