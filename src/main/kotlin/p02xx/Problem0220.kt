package p02xx

fun main() {
    class Solution {
        fun containsNearbyAlmostDuplicate(nums: IntArray, indexDiff: Int, valueDiff: Int): Boolean {
            if (indexDiff == 0) {
                return false
            }

            val set = sortedSetOf<Int>()

            fun insertAndCheckValue(num: Int): Boolean {
                if (!set.add(num)) {
                    return true
                }

                if (set.higher(num)?.let { it - num <= valueDiff } == true) {
                    return true
                }

                if (set.lower(num)?.let { num - it <= valueDiff } == true) {
                    return true
                }

                return false
            }

            repeat((indexDiff + 1).coerceAtMost(nums.size)) {
                if (insertAndCheckValue(nums[it])) {
                    return true
                }
            }

            var leftIndex = 0
            var rightIndex = indexDiff + 1

            while (rightIndex < nums.size) {
                set.remove(nums[leftIndex++])

                if (insertAndCheckValue(nums[rightIndex++])) {
                    return true
                }
            }
            return false
        }
    }

    println(
        Solution().containsNearbyAlmostDuplicate(
            intArrayOf(1, 5, 9, 1, 5, 9), 2, 3
        )
    )
}

