import kotlin.math.abs

//fun main() {
//    val lines = readInput("Day02-Input")
//
//    part1(lines)
//    part2(lines)
//}


fun isReportSafe(levels: Sequence<Int>): Boolean {
    require(levels.count() >= 2)
    val deltas = levels
        .windowed(2)
        .map { (current, next) -> next - current }
    val firstDelta = deltas.first()
    return deltas.all { (abs(it) in 1..3) && (it >= 0 == firstDelta >= 0) }
}

fun canReportBeMadeSafe(levels: Sequence<Int>): Boolean {
    return (0 until levels.count())
        .any { i -> levels.filterIndexed { index, _ -> index != i }.let(::isReportSafe) }
}

fun main() {
    val lines = readInput("Day02-Input")
    listOf(::isReportSafe, ::canReportBeMadeSafe)
        .forEachIndexed { index, validator ->
            lines
                .count { it.split(" ").map(String::toInt).asSequence().let(validator) }
                .let { "Part ${index + 1}: $it" }
                .also(::println)
        }
}

private fun part1(lines: List<String>) {
    lines.count { line ->
        val temp = line.split(" ").filterNot(String::isEmpty).map { it.toInt() }

        var up = true
        var down = true
        temp.distinct().size == temp.size && temp.foldIndexed(true, { index: Int, acc: Boolean, i: Int ->
            when {
                index == 0 -> true
                !acc -> false
                up && temp[index - 1] < i && abs(i - temp[index - 1]) <= 3 -> {
                    down = false
                    true
                }

                down && temp[index - 1] > i && abs(i - temp[index - 1]) <= 3 -> {
                    up = false
                    true
                }

                else -> false
            }
        })
    }.println()
}

private fun part2(lines: List<String>) {
    lines.count { line ->
        val temp = line.split(" ").filterNot(String::isEmpty).map { it.toInt() }.toMutableList().iterator()

        val first = temp.next()
        var up = true
        var down = true
        var removed = false
        val added = mutableListOf(first)
        var valid = true

        do {
            val current = temp.iterator().next()
            val last = added.last()
            if (up && last < current && abs(current - last) <= 3 && !removed) {
                down = false
                added.add(current)
            } else if (down && last > current && abs(current - last) <= 3 && !removed) {
                up = false
                added.add(current)
            } else if (!removed) {
                removed = true
                temp.remove()
            } else {
                valid = false
            }
        } while (temp.iterator().hasNext() && valid)

      valid
    }.println()
}

