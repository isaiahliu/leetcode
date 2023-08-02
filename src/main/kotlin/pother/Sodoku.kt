package pother

import java.util.*

fun main() {
    val allNums = (1..9).toSet()

    abstract class AbstractGrid(val row: Int, val column: Int) {
        abstract override fun toString(): String

        abstract val num: Int?

        abstract fun notifyAdd(num: Int)

        abstract fun notifyRemove(num: Int)

        abstract val inValidNums: Set<Int>

        abstract val forceNum: Int?
    }

    abstract class AbstractGroup {
        abstract operator fun contains(grid: AbstractGrid): Boolean

        abstract operator fun get(pos: Int): AbstractGrid

        fun notifyMustIn(grids: Set<AbstractGrid>, num: Int) {
            if (grids.all { it in this }) {
                repeat(9) {
                    val grid = this[it]

                    if (grid !in grids) {
                        grid.notifyAdd(num)
                    }
                }
            }
        }

        fun valid(): Boolean {
            val nums = hashSetOf<Int>()

            repeat(9) {
                this[it].num?.also {
                    if (!nums.add(it)) {
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
                (allNums - grid.inValidNums).forEach {
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

        val suggest: Pair<AbstractGrid, Int>?
            get() {
                val possibles = Array(10) { hashSetOf<AbstractGrid>() }

                repeat(9) {
                    val grid = this[it]
                    (allNums - grid.inValidNums).forEach {
                        possibles[it].add(grid)
                    }
                }

                possibles.forEachIndexed { index, possibleGrids ->
                    if (possibleGrids.size == 1) {
                        return possibleGrids.first() to index
                    }
                }

                return null
            }
    }

    abstract class AbstractLine : AbstractGroup()

    class Sodoku(init: Array<String>) {
        val gameBoard: Array<Array<AbstractGrid>> = Array(9) { r ->
            Array(9) { c ->
                EmptyGrid(r, c)
            }
        }

        inner class NumGrid(row: Int, column: Int, override val num: Int) : AbstractGrid(row, column) {
            override fun toString() = num.toString()
            override fun notifyAdd(num: Int) {}
            override fun notifyRemove(num: Int) {}
            override val inValidNums: Set<Int> = allNums
            override val forceNum: Int? = null
        }

        inner class EmptyGrid(row: Int, column: Int) : AbstractGrid(row, column) {
            override fun toString() = " "

            override val num: Int? = null

            override val inValidNums = hashSetOf<Int>()

            override fun notifyAdd(num: Int) {
                inValidNums.add(num)
            }

            override fun notifyRemove(num: Int) {
                inValidNums.remove(num)
            }

            override val forceNum: Int?
                get() {
                    return if (inValidNums.size == 8) {
                        (allNums - inValidNums).first()
                    } else {
                        null
                    }
                }
        }

        inner class Cube(val rowIndex: Int, val columnIndex: Int) : AbstractGroup() {
            override fun contains(grid: AbstractGrid): Boolean {
                return rowIndex == grid.row / 3 && columnIndex == grid.column / 3
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sodoku[rowIndex * 3 + pos / 3, columnIndex * 3 + pos % 3]
            }
        }

        inner class VerticalLine(val index: Int) : AbstractLine() {
            override fun contains(grid: AbstractGrid): Boolean {
                return index == grid.column
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sodoku[pos, index]
            }
        }

        inner class HorizontalLine(val index: Int) : AbstractLine() {
            override fun contains(grid: AbstractGrid): Boolean {
                return index == grid.row
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sodoku[index, pos]
            }
        }

        val cubes = Array(3) { r ->
            Array(3) { c ->
                Cube(r, c)
            }
        }

        val verticalLines = Array(9) { VerticalLine(it) }

        val horizontalLines = Array(9) { HorizontalLine(it) }

        val operations = LinkedList<Pair<Int, Int>>()

        init {
            if (init.size != 9 || init.any { it.length != 9 }) {
                throw Exception("Invalid Input")
            }

            if (!valid()) {
                throw Exception("Invalid Input")
            }

            val usedNums = IntArray(9)

            init.forEachIndexed { r, row ->
                row.forEachIndexed { c, ch ->
                    when (ch) {
                        in '1'..'9' -> this[r, c] = (ch - '0').also { usedNums[it - 1]++ }
                        ' ' -> {}
                        else -> throw Exception("Invalid Input")
                    }
                }
            }

            if (usedNums.any { it > 9 } || usedNums.count { it == 0 } > 1) {
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

            cubes.forEach {
                it.forEach { group ->
                    if (grid in group) {
                        repeat(9) {
                            group[it].notifyAdd(num)
                        }
                    }
                }
            }

            verticalLines.forEach { group ->
                if (grid in group) {
                    repeat(9) {
                        group[it].notifyAdd(num)
                    }
                }
            }

            horizontalLines.forEach { group ->
                if (grid in group) {
                    repeat(9) {
                        group[it].notifyAdd(num)
                    }
                }
            }
        }

        fun notifyMustIn(grids: Set<AbstractGrid>, num: Int) {
            cubes.forEach {
                it.forEach {
                    it.notifyMustIn(grids, num)
                }
            }

            verticalLines.forEach {
                it.notifyMustIn(grids, num)
            }

            horizontalLines.forEach {
                it.notifyMustIn(grids, num)
            }
        }

        val suggest: Pair<AbstractGrid, Int>?
            get() {
                gameBoard.forEach {
                    it.forEach { grid ->
                        grid.forceNum?.also {
                            return grid to it
                        }
                    }
                }

                cubes.forEach {
                    it.forEach {
                        it.suggest?.also {
                            return it
                        }
                    }
                }

                verticalLines.forEach {
                    it.suggest?.also {
                        return it
                    }
                }

                horizontalLines.forEach {
                    it.suggest?.also {
                        return it
                    }
                }
                return null
            }

        fun valid(): Boolean {
            return cubes.all { it.all { it.valid() } } && verticalLines.all { it.valid() } && horizontalLines.all { it.valid() }
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
                    if (r == lastPos?.first && c == lastPos.second) {
                        result.append("(")
                    } else if (r == lastPos?.first && c == lastPos.second + 1 && c % 3 != 0) {
                        result.append(")")
                    } else {
                        result.append(" ")
                    }

                    result.append(ch)

                    if (c % 3 == 2) {
                        if (r == lastPos?.first && c == lastPos.second) {
                            result.append(")")
                        } else {
                            result.append(" ")
                        }

                        result.append("|")
                    }
                }
                result.appendLine()
                if (r % 3 == 2) {
                    if (r < 8) {
                        result.appendLine("--------+-------+--------")
                    } else {
                        result.appendLine("-------------------------")
                    }
                }
            }

            return result.toString()
        }

        fun deepSearch() {
            val searches = arrayListOf<Pair<Set<AbstractGrid>, Int>>()

            cubes.forEach {
                it.forEach {
                    searches += it.deepSearch()
                }
            }

            verticalLines.forEach {
                searches += it.deepSearch()
            }

            horizontalLines.forEach {
                searches += it.deepSearch()
            }

            searches.forEach { (grids, num) ->
                notifyMustIn(grids, num)
            }
        }
    }

    val game = Sodoku(game1)
    println(game)

    var step = 1
    while (!game.done()) {
        val (grid, num) = game.suggest ?: run {
            game.deepSearch()
            game.suggest
        } ?: break

        game[grid.row, grid.column] = num

        println("Step ${step}:")
        println(game)
        val a = 1
        step++
    }

    println(game.valid())
}

private val game1 = arrayOf(
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

private val game2 = arrayOf(
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
