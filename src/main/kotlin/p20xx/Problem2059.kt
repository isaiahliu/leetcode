package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumOperations(nums: IntArray, start: Int, goal: Int): Int {
            val range = 0..1000
            val visited = hashSetOf(start)
            val tasks = LinkedList<Int>()
            tasks.add(start)

            var result = 1
            while (tasks.isNotEmpty()) {
                repeat(tasks.size) {
                    val num = tasks.poll()

                    nums.forEach {
                        arrayOf(num + it, num - it, num xor it).forEach {
                            when (it) {
                                goal -> {
                                    return result
                                }

                                in range -> {
                                    if (visited.add(it)) {
                                        tasks.add(it)
                                    }
                                }
                            }
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    expect {
        Solution().minimumOperations(
            intArrayOf(2, 4, 12), 2, 12
        )
    }
}