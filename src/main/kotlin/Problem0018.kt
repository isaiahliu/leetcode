fun main() {
    class Solution {
        val result = arrayListOf<List<Int>>()

        var numCounts: Map<Long, Int> = emptyMap()

        var entries: List<Map.Entry<Long, Int>> = emptyList()

        var targetNum = 0L

        fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
            result.clear()

            numCounts = nums.toList().groupingBy { it.toLong() }.eachCount()
            entries = numCounts.entries.sortedBy { it.key }

            targetNum = target.toLong()

            for (i in entries.indices) {
                find(0, emptyList(), 0, i)
            }

            return result
        }

        fun find(processedCount: Int, existingNums: List<Int>, existingSum: Long, startIndex: Int) {
            val (currentNum, count) = entries.getOrNull(startIndex) ?: return

            if (existingSum > targetNum && currentNum > 0) {
                return
            }

            val remainingNumCount = 4 - processedCount

            if (count >= remainingNumCount) {
                if (existingSum + currentNum * remainingNumCount == targetNum) {
                    result += existingNums + List(remainingNumCount) { currentNum.toInt() }
                }
            }

            if (count >= remainingNumCount - 1) {
                val tempSum = existingSum + currentNum * (remainingNumCount - 1)
                (targetNum - tempSum).takeIf { it > currentNum && it in numCounts }?.also {
                    result += existingNums + List(remainingNumCount - 1) { currentNum.toInt() } + it.toInt()
                }
            }

            for (useCount in 1..(remainingNumCount - 2).coerceAtMost(count)) {
                for (newStart in startIndex + 1 until entries.size) {
                    find(
                        processedCount + useCount,
                        existingNums + List(useCount) { currentNum.toInt() },
                        existingSum + currentNum * useCount,
                        newStart
                    )
                }
            }
        }
    }

    println(Solution().fourSum(intArrayOf(-1000000000, -1000000000, 1000000000, -1000000000, -1000000000), 294967296))
}

