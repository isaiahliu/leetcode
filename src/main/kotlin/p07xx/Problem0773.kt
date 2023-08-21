package p07xx

import util.expect

fun main() {
    class Solution {
        fun slidingPuzzle(board: Array<IntArray>): Int {
            val target = "123450"
            val invalidTarget = "123540"

            val init = board.joinToString("") { it.joinToString("") }

            val visited = hashSetOf(init)
            val tasks = hashSetOf(init)

            var result = 0
            fun String.switch(pos1: Int, pos2: Int): String? {
                if (this[pos1] != '0' && this[pos2] != '0') {
                    return null
                }
                val m = hashMapOf(pos1 to pos2, pos2 to pos1)
                return "${this[m[0] ?: 0]}${this[m[1] ?: 1]}${this[m[2] ?: 2]}${this[m[3] ?: 3]}${this[m[4] ?: 4]}${this[m[5] ?: 5]}"
            }

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach {
                    when (it) {
                        target -> return result
                        invalidTarget -> return -1
                        else -> {
                            val nexts = hashSetOf<String>()

                            it.switch(0, 1)?.also { nexts.add(it) }
                            it.switch(0, 3)?.also { nexts.add(it) }
                            it.switch(1, 2)?.also { nexts.add(it) }
                            it.switch(1, 4)?.also { nexts.add(it) }
                            it.switch(2, 5)?.also { nexts.add(it) }
                            it.switch(3, 4)?.also { nexts.add(it) }
                            it.switch(4, 5)?.also { nexts.add(it) }

                            tasks.addAll(nexts.filter { visited.add(it) })
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    expect {
        Solution().slidingPuzzle(
            arrayOf()
        )
    }
}