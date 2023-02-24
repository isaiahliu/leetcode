package p02xx

fun main() {
    class Solution {
        fun majorityElement(nums: IntArray): List<Int> {
            val map = hashMapOf<Int, Int>()

            nums.forEach {
                if (it in map) {
                    map[it] = (map[it] ?: 0) + 1
                } else if (map.size < 2) {
                    map[it] = 1
                } else {
                    map.toList().forEach { (key, value) ->
                        if (value == 1) {
                            map.remove(key)
                        } else {
                            map[key] = value - 1
                        }
                    }
                }
            }

            val remaining = map.keys.toList()

            if (remaining.isEmpty()) {
                return emptyList()
            }

            val counts = IntArray(remaining.size)

            nums.forEach { num ->
                remaining.forEachIndexed { index, i ->
                    if (num == i) {
                        counts[index]++
                    }
                }
            }

            return counts.mapIndexed { index, i ->
                if (i > nums.size / 3) {
                    remaining[index]
                } else {
                    null
                }
            }.filterNotNull()
        }
    }

    println(
        Solution().majorityElement(
            intArrayOf(3, 2, 3)
        )
    )
}

