package p24xx

import util.expect

fun main() {
    class Solution {
        fun dividePlayers(skill: IntArray): Long {
            val sum = skill.fold(0L) { a, b -> a + b }
            val teamCount = skill.size / 2

            if (sum % teamCount != 0L) {
                return -1
            }

            val teamSum = sum / teamCount

            val counts = hashMapOf<Long, Int>()

            var result = 0L
            skill.forEach { s ->
                if (teamSum - s in counts) {
                    counts[teamSum - s]?.also {
                        if (it == 1) {
                            counts.remove(teamSum - s)
                        } else {
                            counts[teamSum - s] = it - 1
                        }
                    }

                    result += s * (teamSum - s)
                } else {
                    counts[s.toLong()] = (counts[s.toLong()] ?: 0) + 1
                }
            }

            return result.takeIf { counts.isEmpty() } ?: -1
        }
    }

    expect {
        Solution().dividePlayers(
            intArrayOf()
        )
    }
}