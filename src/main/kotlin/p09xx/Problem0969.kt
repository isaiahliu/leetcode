package p09xx

import util.expect

fun main() {
    class Solution {
        fun pancakeSort(arr: IntArray): List<Int> {
            val pos = IntArray(arr.size)
            arr.forEachIndexed { index, i ->
                pos[i - 1] = index
            }
            val result = arrayListOf<Int>()

            fun switch(length: Int) {
                result.add(length)

                for (i in 0..(length - 1) / 2) {
                    val left = arr[i]
                    val right = arr[length - 1 - i]

                    arr[i] = right
                    arr[length - 1 - i] = left

                    pos[left - 1] = length - 1 - i
                    pos[right - 1] = i
                }
            }

            for (num in arr.lastIndex downTo 1) {
                if (pos[num] == num) {
                    continue
                }

                if (pos[num] > 0) {
                    switch(pos[num] + 1)
                }

                switch(num + 1)
            }

            return result
        }
    }

    expect {
        Solution().pancakeSort(
            intArrayOf(3, 2, 4, 1)
        )
    }
}
