package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minNumberOfFrogs(croakOfFrogs: String): Int {
            class Frog

            val frogPool = LinkedList<Frog>()
            val result = hashSetOf<Frog>()

            val counts = hashMapOf<Int, MutableSet<Frog>>()

            croakOfFrogs.forEach {
                when (it) {
                    'c' -> {
                        val newFrog = frogPool.poll() ?: Frog()
                        counts.computeIfAbsent(0) { hashSetOf() }.add(newFrog)
                    }

                    'r' -> {
                        counts.computeIfAbsent(0) { hashSetOf() }.also { frogs ->
                            frogs.firstOrNull()?.also {
                                frogs.remove(it)
                                counts.computeIfAbsent(1) { hashSetOf() }.add(it)
                            } ?: return -1
                        }
                    }

                    'o' -> {
                        counts.computeIfAbsent(1) { hashSetOf() }.also { frogs ->
                            frogs.firstOrNull()?.also {
                                frogs.remove(it)
                                counts.computeIfAbsent(2) { hashSetOf() }.add(it)
                            } ?: return -1
                        }
                    }

                    'a' -> {
                        counts.computeIfAbsent(2) { hashSetOf() }.also { frogs ->
                            frogs.firstOrNull()?.also {
                                frogs.remove(it)
                                counts.computeIfAbsent(3) { hashSetOf() }.add(it)
                            } ?: return -1
                        }
                    }

                    'k' -> {
                        counts.computeIfAbsent(3) { hashSetOf() }.also { frogs ->
                            frogs.firstOrNull()?.also {
                                frogs.remove(it)
                                frogPool.add(it)
                                result.add(it)
                            } ?: return -1
                        }
                    }
                }
            }

            return if (counts.any {
                    it.value.isNotEmpty()
                }) {
                -1
            } else {
                result.size
            }
        }
    }

    measureTimeMillis {
        Solution().minNumberOfFrogs(
            "abc"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}