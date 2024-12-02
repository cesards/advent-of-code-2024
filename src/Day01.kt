import kotlin.math.abs

fun day01() {
    val lines = readInput("Day01-Input")

    val (left, right) = lines.map { line ->
        val leftCoordinates = line.substringBefore(" ").toInt()
        val rightCoordinates = line.substringAfterLast(" ").toInt()
        leftCoordinates to rightCoordinates
    }.unzip()

    part1(left, right).println()
    part2(left, right).println()
}

private fun part1(first: List<Int>, second: List<Int>) : Int {
    check(first.size == second.size) {
        "Precondition failed. We should have the name number of coordinates."
    }

    return first.sorted().zip(second.sorted()).sumOf { (left, right) ->
        abs(left - right)
    }
}


fun part2(first: List<Int>, second: List<Int>) : Int {
    return first.sumOf { left ->
        left * second.count { left == it }
    }
}

