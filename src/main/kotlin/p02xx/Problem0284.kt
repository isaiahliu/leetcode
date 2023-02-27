package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class PeekingIterator(private val iterator: Iterator<Int>) : Iterator<Int> {
        var tempNum: Int? = null

        fun peek(): Int {
            return tempNum ?: run {
                iterator.next().also { tempNum = it }
            }
        }

        override fun next(): Int {
            return tempNum?.also { tempNum = null } ?: iterator.next()
        }

        override fun hasNext(): Boolean {
            return tempNum != null || iterator.hasNext()
        }
    }

    measureTimeMillis {
        PeekingIterator(intArrayOf().iterator()).also { println(it) }
    }
}

