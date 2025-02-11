package p17xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun canMouseWin(grid: Array<String>, catJump: Int, mouseJump: Int): Boolean {
            val MOUSE_TURN = 0
            val CAT_TURN = 1
            val UNKNOWN = 0
            val MOUSE_WIN = 1
            val CAT_WIN = 2
            val MAX_MOVES = 1000

            val rows = grid.size
            val cols = grid[0].length
            var food = 0
            val dirs = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

            fun getPrevStates(mouse: Int, cat: Int, turn: Int): List<IntArray> {
                val prevStates: MutableList<IntArray> = ArrayList()
                val mouseRow = mouse / cols
                val mouseCol = mouse % cols
                val catRow = cat / cols
                val catCol = cat % cols
                val prevTurn = if (turn == MOUSE_TURN) CAT_TURN else MOUSE_TURN
                val maxJump = if (prevTurn == MOUSE_TURN) mouseJump else catJump
                val startRow = if (prevTurn == MOUSE_TURN) mouseRow else catRow
                val startCol = if (prevTurn == MOUSE_TURN) mouseCol else catCol
                prevStates.add(intArrayOf(mouse, cat, prevTurn))
                for (dir in dirs) {
                    var i = startRow + dir.first
                    var j = startCol + dir.second
                    var jump = 1
                    while (i in 0 until rows && j >= 0 && j < cols && grid[i][j] != '#' && jump <= maxJump) {
                        val prevMouseRow = if (prevTurn == MOUSE_TURN) i else mouseRow
                        val prevMouseCol = if (prevTurn == MOUSE_TURN) j else mouseCol
                        val prevCatRow = if (prevTurn == MOUSE_TURN) catRow else i
                        val prevCatCol = if (prevTurn == MOUSE_TURN) catCol else j
                        val prevMouse = prevMouseRow * cols + prevMouseCol
                        val prevCat = prevCatRow * cols + prevCatCol
                        prevStates.add(intArrayOf(prevMouse, prevCat, prevTurn))
                        i += dir.first
                        j += dir.second
                        jump++
                    }
                }
                return prevStates
            }

            var startMouse = -1
            var startCat = -1
            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    val c = grid[i][j]
                    when (c) {
                        'M' -> {
                            startMouse = i * cols + j
                        }

                        'C' -> {
                            startCat = i * cols + j
                        }

                        'F' -> {
                            food = i * cols + j
                        }
                    }
                }
            }
            val total = rows * cols
            val degrees = Array(total) {
                Array(total) { IntArray(2) }
            }
            val results = Array(total) {
                Array(total) { Array(2) { IntArray(2) } }
            }
            val queue: LinkedList<IntArray> = LinkedList()
            for (mouse in 0 until total) {
                val mouseRow = mouse / cols
                val mouseCol = mouse % cols
                if (grid[mouseRow][mouseCol] == '#') {
                    continue
                }
                for (cat in 0 until total) {
                    val catRow = cat / cols
                    val catCol = cat % cols
                    if (grid[catRow][catCol] == '#') {
                        continue
                    }
                    degrees[mouse][cat][MOUSE_TURN]++
                    degrees[mouse][cat][CAT_TURN]++
                    for (dir in dirs) {
                        var row = mouseRow + dir.first
                        var col = mouseCol + dir.second
                        var jump = 1
                        while (row in 0 until rows && col >= 0 && col < cols && grid[row][col] != '#' && jump <= mouseJump) {
                            val nextMouse = row * cols + col
                            val nextCat = catRow * cols + catCol
                            degrees[nextMouse][nextCat][MOUSE_TURN]++
                            row += dir.first
                            col += dir.second
                            jump++
                        }

                        row = catRow + dir.first
                        col = catCol + dir.second
                        jump = 1
                        while (row in 0 until rows && col >= 0 && col < cols && grid[row][col] != '#' && jump <= catJump) {
                            val nextMouse = mouseRow * cols + mouseCol
                            val nextCat = row * cols + col
                            degrees[nextMouse][nextCat][CAT_TURN]++
                            row += dir.first
                            col += dir.second
                            jump++
                        }
                    }
                }
            }
            for (pos in 0 until total) {
                val row = pos / cols
                val col = pos % cols
                if (grid[row][col] == '#') {
                    continue
                }
                results[pos][pos][MOUSE_TURN][0] = CAT_WIN
                results[pos][pos][MOUSE_TURN][1] = 0
                results[pos][pos][CAT_TURN][0] = CAT_WIN
                results[pos][pos][CAT_TURN][1] = 0
                queue.offer(intArrayOf(pos, pos, MOUSE_TURN))
                queue.offer(intArrayOf(pos, pos, CAT_TURN))
            }
            // 猫和食物在同一个单元格，猫获胜
            for (mouse in 0 until total) {
                val mouseRow = mouse / cols
                val mouseCol = mouse % cols
                if (grid[mouseRow][mouseCol] == '#' || mouse == food) {
                    continue
                }
                results[mouse][food][MOUSE_TURN][0] = CAT_WIN
                results[mouse][food][MOUSE_TURN][1] = 0
                results[mouse][food][CAT_TURN][0] = CAT_WIN
                results[mouse][food][CAT_TURN][1] = 0
                queue.offer(intArrayOf(mouse, food, MOUSE_TURN))
                queue.offer(intArrayOf(mouse, food, CAT_TURN))
            }
            for (cat in 0 until total) {
                val catRow = cat / cols
                val catCol = cat % cols
                if (grid[catRow][catCol] == '#' || cat == food) {
                    continue
                }
                results[food][cat][MOUSE_TURN][0] = MOUSE_WIN
                results[food][cat][MOUSE_TURN][1] = 0
                results[food][cat][CAT_TURN][0] = MOUSE_WIN
                results[food][cat][CAT_TURN][1] = 0
                queue.offer(intArrayOf(food, cat, MOUSE_TURN))
                queue.offer(intArrayOf(food, cat, CAT_TURN))
            }

            while (!queue.isEmpty()) {
                val state = queue.poll()
                val mouse = state[0]
                val cat = state[1]
                val turn = state[2]
                val result = results[mouse][cat][turn][0]
                val moves = results[mouse][cat][turn][1]
                val prevStates = getPrevStates(mouse, cat, turn)
                for (prevState in prevStates) {
                    val prevMouse = prevState[0]
                    val prevCat = prevState[1]
                    val prevTurn = prevState[2]
                    if (results[prevMouse][prevCat][prevTurn][0] == UNKNOWN) {
                        val canWin =
                            result == MOUSE_WIN && prevTurn == MOUSE_TURN || result == CAT_WIN && prevTurn == CAT_TURN
                        if (canWin) {
                            results[prevMouse][prevCat][prevTurn][0] = result
                            results[prevMouse][prevCat][prevTurn][1] = moves + 1
                            queue.offer(intArrayOf(prevMouse, prevCat, prevTurn))
                        } else {
                            degrees[prevMouse][prevCat][prevTurn]--
                            if (degrees[prevMouse][prevCat][prevTurn] == 0) {
                                val loseResult =
                                    if (prevTurn == MOUSE_TURN) CAT_WIN else MOUSE_WIN
                                results[prevMouse][prevCat][prevTurn][0] = loseResult
                                results[prevMouse][prevCat][prevTurn][1] = moves + 1
                                queue.offer(intArrayOf(prevMouse, prevCat, prevTurn))
                            }
                        }
                    }
                }
            }
            return results[startMouse][startCat][MOUSE_TURN][0] == MOUSE_WIN && results[startMouse][startCat][MOUSE_TURN][1] <= MAX_MOVES
        }
    }


    expect {
        Solution().canMouseWin(
            arrayOf(
                "####F",
                "#C...",
                "M...."
            ),
            1,
            2
        )

//        Solution().canMouseWin(
//            arrayOf("M.C...F"), 1, 4
//        )
    }
}
