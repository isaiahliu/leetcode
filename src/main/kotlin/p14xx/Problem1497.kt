package p14xx

import util.expect

fun main() {
    class Solution {
        fun canArrange(arr: IntArray, k: Int): Boolean {
            val map = hashMapOf<Int, Int>()

            arr.map {
                ((it % k + k) % k).takeIf { it > 0 }?.also { m ->
                    map[k - m]?.also {
                        if (it == 1) {
                            map.remove(k - m)
                        } else {
                            map[k - m] = it - 1
                        }
                    } ?: run { map[m] = (map[m] ?: 0) + 1 }
                }
            }

            return map.isEmpty()
        }
    }

    expect {
        Solution().canArrange(
            intArrayOf(), 1
        )

    }
}

