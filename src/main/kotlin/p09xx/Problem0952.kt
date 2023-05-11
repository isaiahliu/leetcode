package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestComponentSize(nums: IntArray): Int {
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

            val primes = arrayListOf(2)

            fun Int.isPrime(): Boolean {
                when {
                    this == 1 -> {
                        return false
                    }

                    this == 2 -> {
                        return true
                    }

                    this % 2 == 0 -> {
                        return false
                    }

                    else -> {
                        for (i in 3 until this) {
                            if (i * i > this) {
                                break
                            }

                            if (this % i == 0) {
                                return false
                            }
                        }

                        return true
                    }
                }
            }

            fun Int.factors(): Set<Int> {
                if (this == 1) {
                    return setOf(1)
                }
                var primeIndex = 0

                val results = hashSetOf<Int>()
                var num = this
                loop@ while (true) {
                    if (primeIndex == primes.size) {
                        var t = primes[primes.lastIndex] + 1
                        while (!t.isPrime()) {
                            t++
                        }

                        primes.add(t)
                    }

                    val factor = primes[primeIndex]

                    when {
                        num % factor == 0 -> {
                            results.add(factor)
                            num /= factor
                        }

                        factor * factor < num -> {
                            primeIndex++
                        }

                        else -> break@loop
                    }
                }

                if (num > 1) {
                    results.add(num)
                }

                return results
            }

            var max = 0
            val groupMap = hashMapOf<Int, Group>()
            nums.forEach {
                val groups = it.factors().map {
                    groupMap.computeIfAbsent(it) { Group() }
                }

                groups.reduce { a, b ->
                    a.join(b)
                    a
                }

                groups[0].parent.also {
                    it.size++

                    max = max.coerceAtLeast(it.size)
                }
            }

            return max
        }
    }

    measureTimeMillis {
        Solution().largestComponentSize(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
