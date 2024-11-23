package p06xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun smallestRange(nums: List<List<Int>>): IntArray {
            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

            val indices = IntArray(nums.size)

            nums.indices.forEach { index ->
                queue.add(nums[index][0] to index)
            }

            val numList = LinkedList<Pair<Int, Int>>()

            val counts = IntArray(nums.size)
            var size = 0

            val result = intArrayOf(0, Int.MAX_VALUE)

            while (queue.isNotEmpty()) {
                val (num, index) = queue.poll()

                nums[index].getOrNull(++indices[index])?.also {
                    queue.add(it to index)
                }

                counts[index]++
                if (counts[index] == 1) {
                    size++
                }

                numList.add(num to index)

                while (size == nums.size && numList.isNotEmpty()) {
                    val min = numList.peekFirst().first
                    val max = numList.peekLast().first

                    if (max - min < result[1] - result[0]) {
                        result[0] = min
                        result[1] = max
                    }

                    numList.poll().second.also {
                        counts[it]--
                        if (counts[it] == 0) {
                            size--
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().smallestRange(
            listOf(
                listOf(4, 10, 15, 24, 26), listOf(0, 9, 12, 20), listOf(5, 18, 22, 30)
            )
        ).toList()

    }
}