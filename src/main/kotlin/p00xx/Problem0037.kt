package p00xx

import java.util.*

fun main() {
    class Solution {
        fun solveSudoku(board: Array<CharArray>) {
            class Possible(var num: Int? = null) {
                val nums = hashSetOf<Int>()

                init {
                    if (num == null) {
                        repeat(9) {
                            nums.add(it + 1)
                        }
                    }
                }

                val empty get() = num == null
            }

            val possibles = Array(9) { r ->
                Array(9) { c ->
                    if (board[r][c] == '.') {
                        Possible()
                    } else {
                        Possible(board[r][c] - '0')
                    }
                }
            }

            val moveStack = LinkedList<Pair<Pair<Int, Int>, Set<Pair<Int, Int>>>>()

            fun setNum(r: Int, c: Int, num: Int) {
                board[r][c] = '0' + num
                possibles[r][c].nums -= num
                possibles[r][c].num = num

                val affectedNodes = hashSetOf<Pair<Int, Int>>()
                repeat(9) {
                    if (possibles[r][it].empty && possibles[r][it].nums.remove(num)) {
                        affectedNodes += r to it
                    }
                    if (possibles[it][c].empty && possibles[it][c].nums.remove(num)) {
                        affectedNodes += it to c
                    }
                }

                val startR = r / 3 * 3
                val startC = c / 3 * 3
                repeat(3) { r1 ->
                    repeat(3) { c1 ->
                        if (possibles[startR + r1][startC + c1].empty && possibles[startR + r1][startC + c1].nums.remove(
                                num
                            )
                        ) {
                            affectedNodes += startR + r1 to startC + c1
                        }

                    }
                }

                moveStack.push((r to c) to affectedNodes)
            }

            fun removeNum(r: Int, c: Int, affectNodes: Set<Pair<Int, Int>>): Int {
                val num = board[r][c] - '0'

                board[r][c] = '.'
                possibles[r][c].nums += num
                possibles[r][c].num = null

                affectNodes.forEach { (r1, c1) ->
                    possibles[r1][c1].nums += num
                }

                return num
            }

            repeat(9) { r ->
                repeat(9) { c ->
                    if (board[r][c] != '.') {
                        setNum(r, c, board[r][c] - '0')
                    }
                }
            }

            fun fillInsured() {
                while (true) {
                    var found = false

                    repeat(9) { r ->
                        repeat(9) { c ->
                            if (possibles[r][c].empty && possibles[r][c].nums.size == 1) {
                                setNum(r, c, possibles[r][c].nums.first())
                                found = true
                            }
                        }
                    }

                    if (!found) {
                        break
                    }
                }
            }

            fillInsured()

            while (moveStack.size < 81) {
                var r = -1
                var c = -1
                loop@ for (r1 in 0 until 9) {
                    for (c1 in 0 until 9) {
                        if (possibles[r1][c1].nums.size == 2) {
                            r = r1
                            c = c1
                        }
                    }
                }

                if (r >= 0) {
                    setNum(r, c, possibles[r][c].nums.min())
                } else {
                    var pre = moveStack.pop()
                    var nextNum: Int
                    while (true) {
                        val p = possibles[pre.first.first][pre.first.second]

                        val num = removeNum(pre.first.first, pre.first.second, pre.second)

                        val next = p.nums.filter { it > num }.minOrNull()

                        if (next == null) {
                            pre = moveStack.pop()
                        } else {
                            nextNum = next
                            break
                        }
                    }

                    setNum(pre.first.first, pre.first.second, nextNum)
                }

                fillInsured()
            }
        }
    }

    Solution().solveSudoku(
        arrayOf(
            charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
            charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
            charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
            charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
            charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
            charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
            charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
            charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
            charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9'),
        )

    )
}

