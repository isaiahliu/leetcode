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

            val counts = hashMapOf<Int, Int>()

            val result = intArrayOf(0, Int.MAX_VALUE)

            while (queue.isNotEmpty()) {
                val (num, index) = queue.poll()

                nums[index].getOrNull(++indices[index])?.also {
                    queue.add(it to index)
                }

                counts[index] = (counts[index] ?: 0) + 1

                numList.add(num to index)

                while (counts.size == nums.size && numList.isNotEmpty()) {
                    val min = numList.peekFirst().first
                    val max = numList.peekLast().first

                    if (max - min < result[1] - result[0]) {
                        result[0] = min
                        result[1] = max
                    }

                    numList.poll().second.also {
                        counts[it]?.also { count ->
                            if (count == 1) {
                                counts -= it
                            } else {
                                counts[it] = count - 1
                            }
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