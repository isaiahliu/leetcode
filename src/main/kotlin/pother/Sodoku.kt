package pother

import java.util.*

fun main() {
    val allNums = (1..9).toSet()

    abstract class AbstractGrid(val row: Int, val column: Int) {
        override fun toString(): String = num?.toString() ?: " "

        abstract val num: Int?

        abstract fun notifyAdd(num: Int)

        abstract fun notifyRemove(num: Int)

        abstract val inValidNums: Set<Int>

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
                        grid.notifyAdd(num)
                    }
                }
            }
        }

        fun valid(): Boolean {
            val nums = hashSetOf<Int>()

            repeat(9) {
                this[it].also {
                    val num = it.num
                    if (num != null && !nums.add(num) || num == null && it.inValidNums.size == 9) {
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

        val suggest: Pair<Pair<Int, Int>, Int>?
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
                        return possibleGrids.first().let { it.row to it.column } to index
                    }
                }

                return null
            }
    }

    class Sodoku(init: Array<String>, val hints: Array<Pair<Pair<Int, Int>, Int>> = emptyArray()) {
        val gameBoard: Array<Array<AbstractGrid>> = Array(9) { r ->
            Array(9) { c ->
                EmptyGrid(r, c)
            }
        }

        inner class NumGrid(row: Int, column: Int, override val num: Int) : AbstractGrid(row, column) {
            override fun notifyAdd(num: Int) {}
            override fun notifyRemove(num: Int) {}
            override val inValidNums: Set<Int> = allNums
            override val forceNum: Int? = null
        }

        inner class EmptyGrid(row: Int, column: Int) : AbstractGrid(row, column) {
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
            override val desc: String = "Cube (${rowIndex}, ${columnIndex})"

            override fun contains(grid: AbstractGrid): Boolean {
                return rowIndex == grid.row / 3 && columnIndex == grid.column / 3
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sodoku[rowIndex * 3 + pos / 3, columnIndex * 3 + pos % 3]
            }
        }

        inner class Column(val index: Int) : AbstractGroup() {
            override val desc: String = "Column ${index}"

            override fun contains(grid: AbstractGrid): Boolean {
                return index == grid.column
            }

            override fun get(pos: Int): AbstractGrid {
                return this@Sodoku[pos, index]
            }
        }

        inner class Row(val index: Int) : AbstractGroup() {
            override val desc: String = "Row ${index}"

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

            allGroups.forEach { group ->
                if (grid in group) {
                    repeat(9) {
                        group[it].notifyAdd(num)
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
                            println("[${grid.row}, ${grid.column}] can be only ${it}")
                            return grid.row to grid.column to it
                        }
                    }
                }

                allGroups.forEach { group ->
                    group.suggest?.also {
                        println("Other cells in ${group.desc} can not be ${it.second} except for [${it.first.first}, ${it.first.second}] ")
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
            println("Deep Search")
            allGroups.map {
                it.deepSearch()
            }.flatten().forEach { (grids, num) ->
                notifyMustIn(grids, num)
            }
        }

        fun process() {
            var hintIndex = 0

            println(this)

            var step = 1
            while (!done()) {
                println("Step ${step}:")
                val (pos, num) = suggest ?: run {
                    deepSearch()
                    suggest
                } ?: run {
                    hints.getOrNull(hintIndex++)?.also { (pos, num) ->
                        println("Use Hint [${pos.first}, ${pos.second}] set ${num}")
                    }
                } ?: break

                this[pos.first, pos.second] = num
                println(this)

                val a = 1
                step++
            }

            if (done()) {
                println("Done")
            } else if (valid()) {
                println("Pending")
            } else {
                println("Failed")
            }
        }
    }

    Sodoku(
        game1,
        arrayOf(0 to 2 to 7)
    ).process()
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
