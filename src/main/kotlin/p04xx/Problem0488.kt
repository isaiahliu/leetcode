package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMinStep(board: String, hand: String): Int {
            if (board.length == 1) {
                return if (hand.count { it == board[0] } >= 2) 2 else -1
            }

            val regex = "R{3,}|G{3,}|B{3,}|Y{3,}|W{3,}".toRegex()

            fun String.fade(): String {
                var t = this
                while (true) {
                    t.replace(regex, "").takeIf { it != t }?.also { t = it } ?: break
                }

                return t
            }

            val sortedHand = hand.toCharArray().let {
                it.sort()
                String(it)
            }

            val tasks = hashSetOf(board to sortedHand)
            val visited = hashSetOf(board to sortedHand)

            var step = 0

            while (tasks.isNotEmpty()) {
                tasks.toList().also { tasks.clear() }.forEach { (b, h) ->
                    if (b.isEmpty()) {
                        return step
                    }

                    val handVisited = hashSetOf<Char>()

                    for (t in h) {
                        if (handVisited.add(t)) {
                            val newHand = h.replaceFirst(t.toString(), "")

                            for (i in 1 until b.length) {
                                val newBoard = (b.substring(0, i) + t + b.substring(i)).fade()

                                if (b[i] != t && b[i - 1] != t && b[i] != b[i - 1]) {
                                    continue
                                }

                                if (t !in newHand && newBoard.count { it == t } in 1..2) {
                                    continue
                                }

                                (newBoard to newHand).also {
                                    if (visited.add(it)) {
                                        tasks.add(it)
                                    }
                                }
                            }
                        }
                    }
                }

                step++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().findMinStep(
            "RRGGBBYYWWRRGGBB",
            "RGBYW"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}