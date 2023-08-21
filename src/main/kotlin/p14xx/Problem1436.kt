package p14xx

import util.expect

fun main() {
    class Solution {
        fun destCity(paths: List<List<String>>): String {
            val froms = hashSetOf<String>()
            val tos = hashSetOf<String>()

            paths.forEach { (from, to) ->
                froms.add(from)
                tos.add(to)
            }

            tos.removeAll(froms)
            return tos.first()
        }
    }

    expect {
        Solution().destCity(
            listOf()
        )

    }
}

