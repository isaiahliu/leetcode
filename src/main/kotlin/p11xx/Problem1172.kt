package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class DinnerPlates(val capacity: Int) {
        val emptyStacks = TreeMap<Int, LinkedList<Int>>()
        val availableStacks = TreeMap<Int, LinkedList<Int>>()
        val fullStacks = TreeMap<Int, LinkedList<Int>>()

        fun push(`val`: Int) {
            val firstEmpty = emptyStacks.firstEntry()
            val firstAvailable = availableStacks.firstEntry()

            when {
                firstEmpty == null && firstAvailable == null -> {
                    val lastFull = fullStacks.lastEntry()?.key?.let { it + 1 } ?: 0

                    val list = LinkedList<Int>()
                    list.push(`val`)

                    if (list.size == capacity) {
                        fullStacks[lastFull] = list
                    } else {
                        availableStacks[lastFull] = list
                    }
                }

                firstEmpty == null || firstAvailable != null && firstAvailable.key < firstEmpty.key -> {
                    firstAvailable.value.push(`val`)

                    if (firstAvailable.value.size == capacity) {
                        fullStacks[firstAvailable.key] = firstAvailable.value
                        availableStacks.remove(firstAvailable.key)
                    }
                }

                else -> {
                    firstEmpty.value.push(`val`)
                    emptyStacks.remove(firstEmpty.key)

                    if (firstEmpty.value.size == capacity) {
                        fullStacks[firstEmpty.key] = firstEmpty.value
                    } else {
                        availableStacks[firstEmpty.key] = firstEmpty.value
                    }
                }
            }
        }

        fun pop(): Int {
            var index = -1
            availableStacks.lastEntry()?.key?.also {
                index = index.coerceAtLeast(it)
            }
            fullStacks.lastEntry()?.key?.also {
                index = index.coerceAtLeast(it)
            }

            return popAtStack(index)
        }

        fun popAtStack(index: Int): Int {
            if (index < 0) {
                return -1
            }

            availableStacks[index]?.also {
                val top = it.poll()

                if (it.isEmpty()) {
                    availableStacks.remove(index)
                    emptyStacks[index] = it
                }

                return top
            }

            fullStacks[index]?.also {
                val top = it.poll()
                fullStacks.remove(index)

                if (it.isEmpty()) {
                    emptyStacks[index] = it
                } else {
                    availableStacks[index] = it
                }

                return top
            }

            return -1
        }
    }

    measureTimeMillis {
        val dp = DinnerPlates(1)

        dp.push(1).also { println(it) }
        dp.push(2).also { println(it) }
        dp.popAtStack(1).also { println(it) }
        dp.pop().also { println(it) }
        dp.push(1).also { println(it) }
        dp.push(2).also { println(it) }
        dp.pop().also { println(it) }
        dp.pop().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}