package p10xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun duplicateZeros(arr: IntArray): Unit {
            val nums = LinkedList<Int>()

            arr.forEachIndexed { index, i ->
                nums.add(i)

                if (i == 0) {
                    nums.add(i)
                }

                arr[index] = nums.pop()
            }
        }
    }

    expect {
        Solution().duplicateZeros(
            intArrayOf()
        )
    }
}
