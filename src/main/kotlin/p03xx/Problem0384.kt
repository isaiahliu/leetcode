package p03xx

import kotlin.random.Random
import util.expect

fun main() {
    class Solution(private val nums: IntArray) {
        fun reset(): IntArray {
            return nums
        }

        fun shuffle(): IntArray {
            val list = nums.toMutableList()

            return IntArray(nums.size) {
                val i = Random.nextInt(list.size)

                list[i].also { list.removeAt(i) }
            }
        }
    }

    expect {
        Solution(intArrayOf())
    }
}

