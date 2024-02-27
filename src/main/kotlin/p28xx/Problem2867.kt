package p28xx

import util.expect

fun main() {
    class Solution {
        fun countPaths(n: Int, edges: Array<IntArray>): Long {
            val primeCache = hashMapOf<Int, Boolean>()
            fun Int.isPrime(): Boolean {
                return when {
                    this in primeCache -> {
                        primeCache[this] ?: false
                    }

                    this == 1 -> {
                        false
                    }

                    this == 2 -> {
                        true
                    }

                    else -> {
                        for (i in 2 until this) {
                            if (i * i > this) {
                                break
                            }

                            if (this % i == 0) {
                                primeCache[this] = false
                                return false
                            }
                        }

                        primeCache[this] = true
                        return true
                    }
                }
            }

            class Group {
                var size = 0

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.size += size
                    }
                    get() {
                        return innerParent?.parent?.also {
                            innerParent = it
                        } ?: this
                    }

                fun join(target: Group) {
                    val leftParent = parent
                    val rightParent = target.parent

                    if (leftParent != rightParent) {
                        leftParent.parent = rightParent
                    }
                }
            }

            val groups = Array(n + 1) { Group().also { it.size++ } }

            val adjacent = Array(n + 1) { arrayListOf<Int>() }
            edges.forEach { (from, to) ->
                val prime1 = from.isPrime()
                val prime2 = to.isPrime()

                when {
                    !prime1 && !prime2 -> groups[from].join(groups[to])
                    prime1 && !prime2 -> adjacent[from].add(to)
                    !prime1 && prime2 -> adjacent[to].add(from)
                }
            }

            var result = 0L
            adjacent.forEach {
                var sum = 1L
                for (groupIndex in it) {
                    val size = groups[groupIndex].parent.size
                    result += size * sum
                    sum += size
                }
            }

            return result
        }
    }

    expect {
        Solution().countPaths(
            2, arrayOf(
                intArrayOf(1, 2),
            )
        )
    }
}
