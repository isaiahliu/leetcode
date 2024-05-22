package p28xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun longestEqualSubarray(nums: List<Int>, k: Int): Int {
            var result = 1

            val map = hashMapOf<Int, LinkedList<Int>>()

            nums.forEachIndexed { index, num ->
                val list = map.computeIfAbsent(num) { LinkedList() }

                list.add(index)

                while (index - list.peek() - list.size >= k) {
                    list.poll()
                }

                result = maxOf(result, list.size)
            }

            return result
        }
    }

    expect {
        Solution().longestEqualSubarray(
            listOf(), 1
        )
    }
}
