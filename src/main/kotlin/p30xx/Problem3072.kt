package p30xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun resultArray(nums: IntArray): IntArray {
            val min = nums.min()
            val max = nums.max()

            class SegNode(val l: Int, val r: Int) {
                val children by lazy {
                    val mid = l + (r - l) / 2
                    arrayOf(SegNode(l, mid), SegNode(mid + 1, r))
                }

                var size = 0

                val pendings = LinkedList<Int>()

                fun add(num: Int) {
                    if (num in l..r) {
                        size++

                        if (l < r) {
                            pendings.add(num)
                        }
                    }
                }

                fun greaterCount(num: Int): Int {
                    return when {
                        size == 0 -> 0
                        num < l -> size
                        num >= r -> 0
                        else -> {
                            while (pendings.isNotEmpty()) {
                                val pending = pendings.poll()
                                children.forEach {
                                    it.add(pending)
                                }
                            }

                            children.sumOf { it.greaterCount(num) }
                        }
                    }
                }
            }

            val segs = Array(2) { index ->
                SegNode(min, max).also {
                    it.add(nums[index])
                }
            }

            val results = Array(2) {
                arrayListOf(nums[it])
            }

            for (index in 2 until nums.size) {
                val num = nums[index]
                val (count1, count2) = segs.map { it.greaterCount(num) }

                val addIndex = when {
                    count1 > count2 -> 0
                    count1 < count2 -> 1
                    results[0].size < results[1].size -> 0
                    results[0].size > results[1].size -> 1
                    else -> 0
                }

                segs[addIndex].add(num)
                results[addIndex].add(num)
            }

            return (results[0] + results[1]).toIntArray()
        }
    }

    expect {
        Solution().resultArray(
            intArrayOf(2, 1, 3, 3)
        )
    }
}
