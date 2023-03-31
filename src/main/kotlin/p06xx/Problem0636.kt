package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun exclusiveTime(n: Int, logs: List<String>): IntArray {
            val result = IntArray(n)

            class Func(val id: Int, val start: Int, val parent: Func?) {
                var deductTime: Int = 0

                fun end(endAt: Int) {
                    val time = endAt - start + 1 - deductTime

                    parent?.deduct(time)

                    result[id] += time
                }

                fun deduct(time: Int) {
                    deductTime += time

                    parent?.deduct(time)
                }
            }

            val regex = "(\\d+):(\\w+):(\\d+)".toRegex()
            var current: Func? = null
            logs.mapNotNull { regex.matchEntire(it)?.groupValues?.drop(1) }.forEach { (fid, status, time) ->
                when (status) {
                    "start" -> {
                        current = Func(fid.toInt(), time.toInt(), current)
                    }

                    "end" -> {
                        current?.end(time.toInt())
                        current = current?.parent
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().exclusiveTime(
            2, listOf("0:start:0", "1:start:2", "1:end:5", "0:end:6")
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}