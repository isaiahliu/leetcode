fun main() {
    class Solution {
        fun calculateMinimumHP(dungeon: Array<IntArray>): Int {
            val context = Array(dungeon.size) {
                Array(dungeon[it].size) {
                    hashMapOf<Int, Int>()
                }
            }

            val sum = dungeon.size + dungeon[0].size - 2

            if (dungeon[0][0] < 0) {
                context[0][0][0 - dungeon[0][0]] = 0
            } else {
                context[0][0][0] = dungeon[0][0]
            }

            for (i in 1..sum) {
                for (r in 0 until dungeon.size.coerceAtMost(i + 1)) {
                    val c = i - r

                    val cost = dungeon[r].getOrNull(c) ?: continue

                    val up = context.getOrNull(r - 1)?.getOrNull(c)
                    val left = context.getOrNull(r)?.getOrNull(c - 1)

                    val possibilities = hashMapOf<Int, Int>()

                    arrayOf(r - 1 to c, r to c - 1).mapNotNull { context.getOrNull(it.first)?.getOrNull(it.second) }
                        .forEach {
                            it.forEach {
                                var requiredMinHp = it.key
                                var remainingHp = it.value + cost

                                if (remainingHp < 0) {
                                    requiredMinHp -= remainingHp
                                    remainingHp = 0
                                }

                                if ((possibilities[requiredMinHp] ?: -1) < remainingHp) {
                                    possibilities[requiredMinHp] = remainingHp
                                }
                            }

                        }

                    var maxRemainingHp = -1
                    possibilities.entries.sortedBy { it.key }.forEach { (required, remaining) ->
                        if (remaining > maxRemainingHp) {
                            maxRemainingHp = remaining
                            context[r][c][required] = remaining
                        }
                    }
                }
            }

            return context[context.size - 1][context[0].size - 1].keys.min() + 1
        }
    }

    println(
        Solution().calculateMinimumHP(
            arrayOf(
                intArrayOf(-2, -3, 3),
                intArrayOf(-5, -10, 1),
                intArrayOf(10, 30, -5),
            )
        )
    )
}

