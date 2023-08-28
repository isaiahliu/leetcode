package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun executeInstructions(n: Int, startPos: IntArray, s: String): IntArray {
            val rowMap = hashMapOf<Int, LinkedList<Int>>()
            val columnMap = hashMapOf<Int, LinkedList<Int>>()

            var offsetR = startPos[0]
            var offsetC = startPos[1]

            s.forEachIndexed { index, instruction ->
                when (instruction) {
                    'U' -> {
                        offsetR--
                        rowMap.computeIfAbsent(offsetR) { LinkedList() }.add(index)
                    }

                    'D' -> {
                        offsetR++
                        rowMap.computeIfAbsent(offsetR) { LinkedList() }.add(index)
                    }

                    'L' -> {
                        offsetC--
                        columnMap.computeIfAbsent(offsetC) { LinkedList() }.add(index)
                    }

                    'R' -> {
                        offsetC++
                        columnMap.computeIfAbsent(offsetC) { LinkedList() }.add(index)
                    }
                }
            }

            offsetR = 0
            offsetC = 0
            return IntArray(s.length) { index ->
                var result = s.length - index

                arrayOf(
                    rowMap[-1 + offsetR],
                    rowMap[n + offsetR],
                    columnMap[-1 + offsetC],
                    columnMap[n + offsetC]
                ).forEach {
                    it?.also {
                        while (it.isNotEmpty() && it.peek() < index) {
                            it.poll()
                        }

                        it.peek()?.also {
                            result = result.coerceAtMost(it - index)
                        }
                    }
                }

                when (s[index]) {
                    'U' -> {
                        offsetR--
                    }

                    'D' -> {
                        offsetR++
                    }

                    'L' -> {
                        offsetC--
                    }

                    'R' -> {
                        offsetC++
                    }
                }

                result
            }
        }
    }

    expect {
        Solution().executeInstructions(
            3, intArrayOf(0, 1), "RRDDLU"
        ).toList()
    }
}