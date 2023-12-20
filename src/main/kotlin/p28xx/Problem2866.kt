package p28xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumSumOfHeights(maxHeights: List<Int>): Long {
            val maxes = Array(2) { LongArray(maxHeights.size) }

            arrayOf(maxHeights.indices, maxHeights.indices.reversed()).forEachIndexed { direction, indices ->
                var sum = 0L
                val stack = LinkedList<Pair<Int, Int>>()
                indices.forEach {
                    val height = maxHeights[it]

                    var popSize = 0
                    while (stack.isNotEmpty() && stack.peek().first > height) {
                        val (h, s) = stack.poll()
                        popSize += s
                        sum -= 1L * h * s
                    }

                    sum += 1L * height * popSize

                    maxes[direction][it] = sum

                    sum += height
                    stack.push(height to popSize + 1)
                }
            }

            return maxHeights.indices.maxOf { index -> maxes.sumOf { it[index] } + maxHeights[index] }
        }
    }

    expect {
        Solution().maximumSumOfHeights(
            listOf(
                314324228,
                526196638,
                971780775,
                141382951,
                44825730,
                92939243,
                869702460,
                692214717,
                396184442,
                271863091,
                452818943,
                124554145,
                194393992,
                813279621,
                476977123,
                291285997,
                195696382,
                80619001,
                296691434,
                24194433,
                834306546,
                337273583,
                612960339,
                252148987,
                498162770,
                641751698,
                580675254,
                66186200,
                192009966,
                590634046,
                590252844,
                510204257,
                235020771,
                606202644,
                338253570,
                224352005,
                183647397,
                867961176,
                521468453,
                365745792,
                508222499,
                360685429,
                851354307,
                177768509,
                955097078,
                227459453,
                644376561,
                467834249,
                594236609,
                319781404,
                648225233,
                524439197,
                532203513,
                463002246,
                498592686,
                691351312,
                208635346,
                155682966,
                294639403,
                341617283,
                604365123,
                79112831,
                22440031,
                809193898,
                675993946,
                99928197,
                644324211,
                170555722,
                218906830,
                782039120,
                686747235,
                356537885
            )
        )
    }
}
