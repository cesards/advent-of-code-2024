
fun main() {
    val lines = readInput("Day03-Input")
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