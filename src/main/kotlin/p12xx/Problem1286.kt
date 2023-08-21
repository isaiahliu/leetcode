package p12xx

import java.util.*
import util.expect

fun main() {
    class CombinationIterator(private val characters: String, private val combinationLength: Int) {
        val list = LinkedList<Int>()

        init {
            repeat(combinationLength) {
                list.add(it)
            }
        }

        fun next(): String {
            val result = list.joinToString("") { characters[it].toString() }

            while (true) {
                val last = list.pollLast() ?: break

                if (last + combinationLength - list.size < characters.length) {
                    repeat(combinationLength - list.size) {
                        list.add(last + it + 1)
                    }

                    break
                }
            }

            return result
        }

        fun hasNext(): Boolean {
            return list.isNotEmpty()
        }
    }

    expect {
        val itor = CombinationIterator("abc", 2)
        itor.next()
        itor.hasNext()
        itor.next()
        itor.hasNext()
        itor.next()
        itor.hasNext()
        itor.next()
        itor.hasNext()
    }
}
