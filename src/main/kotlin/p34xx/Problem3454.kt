package p34xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun separateSquares(squares: Array<IntArray>): Double {
            class SegNode(private val minY: Int, private val maxY: Int, initMap: Map<Int, Int> = emptyMap()) {
                private val children = arrayListOf<SegNode>()

                private val x = TreeMap<Int, Int>()

                var sum = 0L

                init {
                    if (initMap.isNotEmpty()) {
                        x.putAll(initMap)
                        sum = x.entries.sumOf { (it.value - it.key + 1L) * (maxY - minY + 1L) }
                    }
                }

                fun mark(fromY: Int, toY: Int, fromX: Int, toX: Int) {
                    when {
                        toY < minY || fromY > maxY -> Unit
                        fromY <= minY && toY >= maxY && children.isEmpty() -> {
                            var s = fromX
                            var e = toX

                            while (true) {
                                x.lowerEntry(s)?.takeIf { it.value >= s }?.also {
                                    x.remove(it.key)
                                    s = it.key
                                    e = maxOf(e, it.value)
                                } ?: break
                            }

                            while (true) {
                                x.ceilingEntry(s)?.takeIf { it.key <= e }?.also {
                                    x.remove(it.key)
                                    e = maxOf(e, it.value)
                                } ?: break
                            }

                            x[s] = e

                            sum = x.entries.sumOf { (it.value - it.key + 1L) * (maxY - minY + 1L) }
                        }

                        else -> {
                            if (children.isEmpty()) {
                                buildList {
                                    add(minY)

                                    if (fromY > minY) {
                                        add(fromY - 1)
                                    }

                                    if (toY < maxY) {
                                        add(toY)
                                    }
                                    add(maxY)
                                }.reduce { a, b ->
                                    children.add(SegNode(a, b, x))

                                    b + 1
                                }
                            }

                            sum = 0L
                            children.forEach {
                                it.mark(fromY, toY, fromX, toX)
                                sum += it.sum
                            }
                        }
                    }
                }

                fun square(fromY: Int, toY: Int): Long {
                    return when {
                        fromY > maxY || toY < minY -> 0L
                        children.isNotEmpty() -> {
                            children.sumOf {
                                it.square(fromY, toY)
                            }
                        }

                        else -> {
                            (sum / (maxY - minY + 1L) * (minOf(toY, maxY) - maxOf(fromY, minY) + 1L))
                        }
                    }
                }
            }

            val minY = 0
            val maxY = Int.MAX_VALUE - 1

            val root = SegNode(minY, maxY)

            squares.forEach { (x, y, l) ->
                root.mark(y, y + l - 1, x, x + l - 1)
            }

            var midY = 0

            var l = minY
            var r = maxY
            while (l <= r) {
                val m = l + (r - l) / 2

                val part1 = root.square(minY, m - 1)
                val part2 = root.square(m, maxY)

                if (part1 >= part2) {
                    midY = m
                    r = m - 1
                } else {
                    l = m + 1
                }
            }

            val diff1 = root.square(minY, midY - 1) - root.square(midY, maxY)

            var result = midY.toDouble()
            if (diff1 > 0) {
                val diff2 = root.square(minY, midY - 2) - root.square(midY - 1, maxY)

                result -= diff1.toDouble() / (diff1 - diff2)

            }
            return result
        }
    }

    expect {
        Solution().separateSquares(
            arrayOf(
                intArrayOf(999892931, 999974790, 6788622),
                intArrayOf(319710671, 963660807, 5518783),
                intArrayOf(623736653, 934759633, 4248549),
                intArrayOf(234214719, 848813522, 417010),
                intArrayOf(154771654, 645515409, 9370045),
                intArrayOf(965571354, 998982755, 10809560),
                intArrayOf(338822522, 550588284, 12471651),
                intArrayOf(168193362, 682286828, 5173004),
                intArrayOf(459856474, 778674604, 5635628),
                intArrayOf(806653114, 860720237, 1444683),
            )
        )
    }
}
