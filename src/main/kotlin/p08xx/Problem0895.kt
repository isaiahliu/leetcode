package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class FreqStack {
        val counts = hashMapOf<Int, Int>()

        val stacks = hashMapOf<Int, LinkedList<Int>>()
        var maxCount = 0
        fun push(`val`: Int) {
            val count = (counts[`val`] ?: 0) + 1

            maxCount = maxCount.coerceAtLeast(count)

            stacks.computeIfAbsent(count) { LinkedList<Int>() }.push(`val`)
            counts[`val`] = count
        }

        fun pop(): Int {
            while (true) {
                val num = stacks[maxCount]?.poll()

                if (num == null) {
                    maxCount--
                } else {
                    counts[num] = (counts[num] ?: 0) - 1
                    return num
                }
            }
        }
    }

    measureTimeMillis {
        val fs = FreqStack()
        fs.push(5).also { println(it) }
        fs.push(7).also { println(it) }
        fs.push(5).also { println(it) }
        fs.push(7).also { println(it) }
        fs.push(4).also { println(it) }
        fs.push(5).also { println(it) }
        fs.pop().also { println(it) }
        fs.pop().also { println(it) }
        fs.pop().also { println(it) }
        fs.pop().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}