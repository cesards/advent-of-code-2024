fun main() {
    val lines = readInput("Day04-Input")
    part01(lines)
    part02(lines)
}

private fun part01(lines: List<String>) {
    val board = Array(lines.size) { row ->
        val chars = lines[row].toCharArray()
        Array(chars.size) { col ->
            chars[col]
        }
    }

    var sum = 0
    board.forEachIndexed { row, chars ->
        chars.forEachIndexed { column, char ->
            sum += part01search(row, column, board, 1)
        }
    }

    println(sum)
    // 2390
}

private fun part01search(row: Int, column: Int, board: Array<Array<Char>>, step: Int, part01Pattern: Part01Pattern? = null): Int {
    return when {
        (row < 0 || row >= board.size) -> 0
        (column < 0 || column >= board[row].size) -> 0
        (step == 1 && board[row][column] == 'X') ||
                (step == 2 && board[row][column] == 'M') ||
                (step == 3 && board[row][column] == 'A') -> {

            when (part01Pattern) {
                Part01Pattern.FROM_LEFT -> part01search(row, column + 1, board, step + 1, part01Pattern)
                Part01Pattern.FROM_RIGHT -> part01search(row, column - 1, board, step + 1, part01Pattern)
                Part01Pattern.FROM_TOP -> part01search(row + 1, column, board, step + 1, part01Pattern)
                Part01Pattern.FROM_BOTTOM -> part01search(row - 1, column, board, step + 1, part01Pattern)
                Part01Pattern.FROM_TOP_LEFT -> part01search(row + 1, column + 1, board, step + 1, part01Pattern)
                Part01Pattern.FROM_TOP_RIGHT -> part01search(row + 1, column - 1, board, step + 1, part01Pattern)
                Part01Pattern.FROM_BOTTOM_RIGHT -> part01search(row - 1, column - 1, board, step + 1, part01Pattern)
                Part01Pattern.FROM_BOTTOM_LEFT -> part01search(row - 1, column + 1, board, step + 1, part01Pattern)
                null -> {
                    part01search(row + 1, column + 1, board, step + 1, Part01Pattern.FROM_TOP_LEFT) +
                            part01search(row - 1, column - 1, board, step + 1, Part01Pattern.FROM_BOTTOM_RIGHT) +
                            part01search(row - 1, column + 1, board, step + 1, Part01Pattern.FROM_BOTTOM_LEFT) +
                            part01search(row + 1, column - 1, board, step + 1, Part01Pattern.FROM_TOP_RIGHT) +
                            part01search(row, column - 1, board, step + 1, Part01Pattern.FROM_RIGHT) +
                            part01search(row, column + 1, board, step + 1, Part01Pattern.FROM_LEFT) +
                            part01search(row + 1, column, board, step + 1, Part01Pattern.FROM_TOP) +
                            part01search(row - 1, column, board, step + 1, Part01Pattern.FROM_BOTTOM)
                }
            }
        }

        (step == 4 && board[row][column] == 'S') -> 1

        else -> 0
    }
}

private enum class Part01Pattern {
    FROM_LEFT,
    FROM_RIGHT,
    FROM_TOP,
    FROM_BOTTOM,
    FROM_TOP_LEFT,
    FROM_TOP_RIGHT,
    FROM_BOTTOM_RIGHT,
    FROM_BOTTOM_LEFT,
}


private fun part02(lines: List<String>) {

}