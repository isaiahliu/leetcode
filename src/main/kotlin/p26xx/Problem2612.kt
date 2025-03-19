package p26xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minReverseOperations(n: Int, p: Int, banned: IntArray, k: Int): IntArray {
            val result = IntArray(n) { -1 }

            val maps = arrayOf(TreeSet<Int>(), TreeSet<Int>())
            val bannedSet = banned.toSet()

            repeat(n) {
                if (it !in bannedSet && it != p) {
                    maps[it % 2] += it
                }
            }

            val queue = LinkedList<Int>()
            queue += p

            var step = 0
            while (queue.isNotEmpty()) {
                repeat(queue.size) {
                    val pos = queue.poll()
                    result[pos] = step

                    val (left, right) = intArrayOf(maxOf(pos - k + 1, 0), minOf(n - k, pos)).map {
                        it * 2 + k - pos - 1
                    }
                    while (true) {
                        queue += maps[left % 2].ceiling(left)?.takeIf { it <= right }?.also {
                            maps[left % 2].remove(it)
                        } ?: break
                    }
                }
                step++
            }

            return result
        }
    }

    expect {
        Solution().minReverseOperations(
            4, 0, intArrayOf(1, 2), 4
        )
    }
}
