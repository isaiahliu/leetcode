package p29xx

import util.expect

fun main() {
    class Solution {
        fun maximumLength(s: String): Int {
            var prev = s[0]
            var length = 0
            val lengths = Array(26) { IntArray(s.length + 1) }
            val maxLengths = IntArray(26)

            "${s}0".forEach {
                when {
                    prev != it -> {
                        lengths[prev - 'a'][length]++
                        maxLengths[prev - 'a'] = maxOf(maxLengths[prev - 'a'], length)

                        length = 1
                    }

                    else -> {
                        length++
                    }
                }

                prev = it
            }

            var result = -1

            lengths.forEachIndexed { index, array ->
                var sum = 0
                var count = 0

                for (size in maxLengths[index] downTo maxOf(result, 1)) {
                    count += array[size]
                    sum += count

                    if (sum >= 3) {
                        result = maxOf(result, size)
                        return@forEachIndexed
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumLength(
            ""
        )
    }
}
