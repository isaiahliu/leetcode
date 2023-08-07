package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class OrderedStream(n: Int) {
        var ptr = 0

        val indices = TreeMap<Int, String>()

        fun insert(idKey: Int, value: String): List<String> {
            val result = arrayListOf<String>()

            indices[idKey - 1] = value

            while (indices.isNotEmpty() && ptr == indices.firstKey()) {
                result.add(indices.pollFirstEntry().value)
                ptr++
            }

            return result
        }
    }

    measureTimeMillis {
        val os = OrderedStream(5)
        os.insert(3, "ccccc").also { println(it) }
        os.insert(1, "aaaaa").also { println(it) }
        os.insert(2, "bbbbb").also { println(it) }
        os.insert(5, "eeeee").also { println(it) }
        os.insert(4, "ddddd").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

