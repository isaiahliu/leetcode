package p12xx

fun main() {
    class Solution {
        fun maxEqualFreq(nums: IntArray): Int {
            if (nums.size <= 3) {
                return nums.size
            }

            val positions = IntArray(30001)

            val maps = hashMapOf<Int, Int>()

            var result = 0
            nums.forEachIndexed { index, i ->
                positions[i]++

                val count = positions[i]

                maps[count] = (maps[count] ?: 0) + 1

                maps[count - 1]?.also {
                    when (it) {
                        1 -> {
                            maps.remove(count - 1)
                        }

                        else -> {
                            maps[count - 1] = it - 1
                        }
                    }
                }

                when (maps.size) {
                    1 -> {
                        if (1 in maps || 1 in maps.values) {
                            result = index + 1
                        }
                    }

                    2 -> {
                        val (key1, key2) = maps.keys.sorted()
                        if ((key1 + 1 == key2 && maps[key2] == 1) || maps[1] == 1) {
                            result = index + 1
                        }
                    }
                }
            }

            return result
        }
    }
}

