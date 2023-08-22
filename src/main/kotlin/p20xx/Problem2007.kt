package p20xx

import util.expect

fun main() {
    class Solution {
        fun findOriginalArray(changed: IntArray): IntArray {
            changed.sort()

            val doubleMap = hashMapOf<Int, Int>()

            val result = arrayListOf<Int>()
            changed.forEach { num ->
                doubleMap[num]?.also {
                    if (it == 1) {
                        doubleMap.remove(num)
                    } else {
                        doubleMap[num] = it - 1
                    }
                } ?: run {
                    result.add(num)
                    doubleMap[num * 2] = (doubleMap[num * 2] ?: 0) + 1
                }
            }

            return result.takeIf { it.size * 2 == changed.size }?.toIntArray() ?: intArrayOf()
        }
    }

    expect {
        Solution().findOriginalArray(
            intArrayOf(1, 2, 2, 1)
        )
    }
}