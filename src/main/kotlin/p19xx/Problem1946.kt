package p19xx

import util.expect

fun main() {
    class Solution {
        fun maximumNumber(num: String, change: IntArray): String {
            var changed = false
            var changing = false
            return num.map {
                (it - '0').let {
                    when {
                        it > change[it] -> {
                            if (!changing && !changed) {
                                changing = true
                                changed = true
                            }

                            if (changing) {
                                change[it]
                            } else {
                                it
                            }
                        }

                        it < change[it] -> {
                            changing = false
                            it
                        }

                        else -> {
                            it
                        }
                    }
                }
            }.joinToString("")
        }
    }

    expect {
        Solution().maximumNumber(
            "", intArrayOf()
        )
    }
}