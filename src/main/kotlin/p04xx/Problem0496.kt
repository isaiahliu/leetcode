package p04xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
            val numMap = hashMapOf<Int, Int>()

            val set = TreeSet<Int>()

            nums2.forEach {
                var t = set.lower(it)
                while (t != null) {
                    numMap[t] = it
                    set.remove(t)
                    t = set.lower(it)
                }

                set.add(it)
            }

            return IntArray(nums1.size) {
                numMap[nums1[it]] ?: -1
            }
        }
    }

    expect {
        Solution().nextGreaterElement(
            intArrayOf(4, 1, 2), intArrayOf(1, 3, 4, 2)
        ).toList()
    }
}