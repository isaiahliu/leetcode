fun main() {
    class Solution {
        fun threeSum(nums: IntArray): List<List<Int>> {
            val result = arrayListOf<List<Int>>()

            val numMap = nums.toList().groupingBy { it }.eachCount()

            if ((numMap[0] ?: 0) > 2) {
                result += listOf(0, 0, 0)
            }

            val numEntries = numMap.entries.sortedBy { it.key }.toTypedArray()

            numEntries.forEach { (key, value) ->
                if (key != 0 && value > 1) {
                    if (0 - key * 2 in numMap) {
                        result += listOf(key, key, 0 - key * 2)
                    }
                }
            }

            for (i in numEntries.indices) {
                for (j in i + 1 until numEntries.size) {
                    val left = numEntries[i].key
                    val right = numEntries[j].key

                    (0 - left - right).takeIf { it > right && it in numMap }?.also {
                        result += listOf(
                            left,
                            right,
                            it
                        )
                    }
                }
            }

            return result
        }
    }
}

