package p29xx

import util.expect

fun main() {
    class Solution {
        fun getGoodIndices(variables: Array<IntArray>, target: Int): List<Int> {
            fun modPow(a: Int, b: Int, m: Int): Int {
                var last = a % m
                val mods = arrayListOf<Int>()

                while (last != mods.firstOrNull() && b > mods.size) {
                    mods += last

                    last = (last * a) % m
                }

                return mods[(b - 1) % mods.size]
            }

            return variables.indices.filter {
                val (a, b, c, m) = variables[it]

                modPow(modPow(a, b, 10), c, m) == target
            }
        }
    }

    expect {
        Solution().getGoodIndices(
            arrayOf(), 1
        )
    }
}
