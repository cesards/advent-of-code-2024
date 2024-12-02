import kotlin.math.abs

fun day01() {
    val lines = readInput("Day01-Input")

    // Part 1
    val first =  mutableListOf<Int>()
    val second =  mutableListOf<Int>()
    lines.forEach { line ->
        val coordinates = line.split(" ").filterNot(String::isEmpty).map { it.toInt() }
        first.add(coordinates[0])
        second.add(coordinates[1])
    }

    part1(first, second).println()
    part2(first, second).println()
}

private fun part1(first: MutableList<Int>, second: MutableList<Int>) : Int {
    first.sort()
    second.sort()

    val min = first.size.coerceAtMost(second.size)

    var sum = 0
    for (i in 0..<min) {
        sum += abs(first[i] - second[i])
    }

    return sum
}


fun part2(first: MutableList<Int>, second: MutableList<Int>) : Int {
    return first.sumOf { left ->
        left * second.count { left == it }
    }
}

