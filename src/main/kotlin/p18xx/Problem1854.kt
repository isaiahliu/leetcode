package p18xx

import util.expect

fun main() {
    class Solution {
        fun maximumPopulation(logs: Array<IntArray>): Int {
            val years = IntArray(101)

            logs.forEach { (birth, death) ->
                years[birth - 1950]++
                years[death - 1950]--
            }

            var maxPop = years[0]
            var result = 0

            for (i in 1 until years.size) {
                years[i] += years[i - 1]

                if (years[i] > maxPop) {
                    maxPop = years[i]
                    result = i
                }
            }

            return result + 1950
        }
    }

    expect {
        Solution().maximumPopulation(
            arrayOf()
        )

    }
}
