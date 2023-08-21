package pother

import java.util.*

fun main() {
    val allNumbers = (1..9).toSet()

    abstract class AbstractGrid(val row: Int, val column: Int) {
        override fun toString(): String = num?.toString() ?: " "

        abstract val num: Int?

        abstract fun notifyGroupMember(num: Int)

        abstract val inValidNumbers: Set<Int>

        abstract val forceNum: Int?
    }

    abstract class AbstractGroup {
        abstract val desc: String

        abstract operator fun contains(grid: AbstractGrid): Boolean

        abstract operator fun get(pos: Int): AbstractGrid

        fun notifyMustIn(grids: Set<AbstractGrid>, num: Int) {
            if (grids.all { it in this }) {
                repeat(9) {
                    val grid = this[it]

                    if (grid !in grids) {
                        grid.notifyGroupMember(num)
                    }
                }
            }
        }

        fun valid(): Boolean {
            val numbers = hashSetOf<Int>()

            repeat(9) {
                this[it].also {
                    val num = it.num
                    if (num != null && !numbers.add(num) || num == null && it.inValidNumbers.size == 9) {
                        return false
                    }
                }
            }

            return true
        }

        fun deepSearch(): List<Pair<Set<AbstractGrid>, Int>> {
            val result = arrayListOf<Pair<Set<AbstractGrid>, Int>>()
            val possibles = Array(10) { hashSetOf<AbstractGrid>() }

            repeat(9) {
                val grid = this[it]
                (allNumbers - grid.inValidNumbers).forEach {
                    possibles[it].add(grid)
                }
            }

            possibles.forEachIndexed { num, grids ->
                if (grids.isNotEmpty()) {
                    result.add(grids to num)
                }
            }

            return result
        }

        val suggest: Pair<Pair<Int, Int>, Int>?
            get() {
                val possibles = Array(10) { hashSetOf<AbstractGrid>() }

                repeat(9) {
                    val grid = this[it]
                    (allNumbers - grid.inValidNumbers).forEach {
                        possibles[it].add(grid)
                    }
                }

                possibles.forEachIndexed { index, possibleGrids ->
                    if (possibleGrids.size == 1) {
                        return possibleGrids.first().let { it.row to it.column } to index
                    }
                }

                return null
            }
    }

    class Sudoku(init: Array<String>, val guessForbidden: Boolean = false) {
        val log = StringBuilder()

        val gameBoard: Array<Array<AbstractGrid>> = Array(9) { r ->
            Array(9) { c ->
                EmptyGrid(r, c)
            }
        }

        inner class NumGrid(row: Int, column: Int, override val num: Int) : AbstractGrid(row, column) {
            override fun notifyGroupMember(num: Int) {}
            override val inValidNumbers: Set<Int> = allNumbers
            override val forceNum: Int? = null
        }

        inner class EmptyGrid(row: Int, column: Int) : AbstractGrid(row, column) {
            override val num: Int? = null

            override val inValidNumbers = hashSetOf<Int>()

            override fun notifyGroupMember(num: Int) {
                inValidNumbers.add(num)
            }

            override val forceNum: Int?
                get() {
                    return if (inValidNumbers.size == 8) {
                        (allNumbers - inValidNumbers).first()
                    } else {
                        null
                    }
                }
        }

        inner class Cube(val rowIndex: Int, val columnIndex: Int) : AbstractGroup() {
            override val desc: String = "Cube (${rowIndex}, ${columnIndex})"

            override fun contains(grid: AbstractGrid): Boolean {
                return rowIndex == grid.row / 3 && columnIndex == grid.column / 3
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sudoku[rowIndex * 3 + pos / 3, columnIndex * 3 + pos % 3]
            }
        }

        inner class Column(val index: Int) : AbstractGroup() {
            override val desc: String = "Column $index"

            override fun contains(grid: AbstractGrid): Boolean {
                return index == grid.column
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sudoku[pos, index]
            }
        }

        inner class Row(val index: Int) : AbstractGroup() {
            override val desc: String = "Row $index"

            override fun contains(grid: AbstractGrid): Boolean {
                return index == grid.row
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sudoku[index, pos]
            }
        }

        val cubes = Array(3) { r ->
            Array(3) { c ->
                Cube(r, c)
            }
        }

        val columns = Array(9) { Column(it) }

        val rows = Array(9) { Row(it) }

        val allGroups = cubes.map { it.toList() }.flatten() + columns + rows

        val operations = LinkedList<Pair<Int, Int>>()

        init {
            if (init.size != 9 || init.any { it.length != 9 }) {
                throw Exception("Invalid Input")
            }

            if (!valid()) {
                throw Exception("Invalid Input")
            }

            val usedNumbers = IntArray(9)

            init.forEachIndexed { r, row ->
                row.forEachIndexed { c, ch ->
                    when (ch) {
                        in '1'..'9' -> this[r, c] = (ch - '0').also { usedNumbers[it - 1]++ }
                        ' ' -> {}
                        else -> throw Exception("Invalid Input")
                    }
                }
            }

            if (usedNumbers.any { it > 9 } || usedNumbers.count { it == 0 } > 1) {
                throw Exception("Invalid Input")
            }

            operations.clear()
        }

        operator fun get(rowIndex: Int, columnIndex: Int): AbstractGrid {
            return gameBoard[rowIndex][columnIndex]
        }

        operator fun set(rowIndex: Int, columnIndex: Int, num: Int) {
            operations.add(rowIndex to columnIndex)

            val grid = NumGrid(rowIndex, columnIndex, num)
            gameBoard[rowIndex][columnIndex] = grid

            allGroups.forEach { group ->
                if (grid in group) {
                    repeat(9) {
                        group[it].notifyGroupMember(num)
                    }
                }
            }
        }

        fun notifyMustIn(grids: Set<AbstractGrid>, num: Int) {
            allGroups.forEach {
                it.notifyMustIn(grids, num)
            }
        }

        val suggest: Pair<Pair<Int, Int>, Int>?
            get() {
                gameBoard.forEach {
                    it.forEach { grid ->
                        grid.forceNum?.also {
                            log.appendLine("[${grid.row}, ${grid.column}] can be only $it")
                            return grid.row to grid.column to it
                        }
                    }
                }

                allGroups.forEach { group ->
                    group.suggest?.also {
                        log.appendLine("Other cells in ${group.desc} can not be ${it.second} except for [${it.first.first}, ${it.first.second}] ")
                        return it
                    }
                }

                return null
            }

        fun valid(): Boolean {
            return cubes.all { it.all { it.valid() } } && columns.all { it.valid() } && rows.all { it.valid() }
        }

        fun done(): Boolean {
            return gameBoard.all { it.all { it.num != null } }
        }

        override fun toString(): String {
            val lastPos = operations.peekLast()
            val result = StringBuilder()

            result.appendLine("-------------------------")
            gameBoard.forEachIndexed { r, row ->
                result.append("|")

                row.forEachIndexed { c, ch ->
                    when {
                        r == lastPos?.first && c == lastPos.second -> {
                            result.append("[")
                        }

                        r == lastPos?.first && c == lastPos.second + 1 && c % 3 != 0 -> {
                            result.append("]")
                        }

                        else -> {
                            result.append(" ")
                        }
                    }

                    result.append(ch)

                    if (c % 3 == 2) {
                        when {
                            r == lastPos?.first && c == lastPos.second -> {
                                result.append("]")
                            }

                            else -> {
                                result.append(" ")
                            }
                        }

                        result.append("|")
                    }
                }

                result.appendLine()

                if (r == 2 || r == 5) result.appendLine("--------+-------+--------")
            }
            result.appendLine("-------------------------")
            return result.toString()
        }

        fun deepSearch() {
            log.appendLine("Deep Search")
            allGroups.map {
                it.deepSearch()
            }.flatten().forEach { (grids, num) ->
                notifyMustIn(grids, num)
            }
        }

        fun clone(): Sudoku {
            return Sudoku(gameBoard.map { it.joinToString("") }.toTypedArray())
        }

        fun process(): Boolean {
            log.appendLine(this)
            var step = 1

            while (!done() && valid()) {
                log.appendLine("Step ${step}:")
                val (pos, num) = suggest ?: run {
                    deepSearch()
                    suggest
                } ?: run {
                    if (guessForbidden) {
                        log.appendLine("Do not guess numbers")
                        return@run null
                    }

                    val guessCell =
                        gameBoard.map { it.filter { it.inValidNumbers.size < 9 } }.flatten()
                            .maxBy { it.inValidNumbers.size }

                    var guessResult: Pair<Pair<Int, Int>, Int>? = null
                    for (num in allNumbers - guessCell.inValidNumbers) {
                        val newGame = clone()
                        newGame[guessCell.row, guessCell.column] = num

                        if (newGame.process()) {
                            log.appendLine("Guess $num in [${guessCell.row}, ${guessCell.column}]")
                            guessResult = guessCell.row to guessCell.column to num
                            break
                        }
                    }

                    guessResult
                } ?: break

                this[pos.first, pos.second] = num
                log.appendLine(this)

                step++
            }

            return if (done()) {
                log.appendLine("Done")
                true
            } else {
                log.appendLine("Failed")
                false
            }
        }
    }

    val game = Sudoku(game3, false)
    game.process()
    game.log
}

private val game1 = arrayOf(
    " 9   34  ",
    "1 3 4 87 ",
    "4 6 8    ",
    "91    2  ",
    "3 8    17",
    "       9 ",
    "7 4235   ",
    "        2",
    " 3 9     ",
)

private val game2 = arrayOf(
    "   9 5   ",
    "  1  2 54",
    "   3  1  ",
    "96       ",
    " 3    6  ",
    "  8 6    ",
    "  74    3",
    " 85  3  6",
    " 295   4 ",
)

private val game3 = arrayOf(
    "   4 25 3",
    " 4    9  ",
    "71      4",
    "  8     6",
    "   594 87",
    "    3    ",
    " 216 97  ",
    "37   5   ",
    "68   1   ",
)
