package p13xx

import util.expect

fun main() {
    class UndergroundSystem {
        val map = hashMapOf<Pair<String, String>, IntArray>()

        val checkIns = hashMapOf<Int, Pair<String, Int>>()

        fun checkIn(id: Int, stationName: String, t: Int) {
            checkIns[id] = stationName to t
        }

        fun checkOut(id: Int, stationName: String, t: Int) {
            checkIns.remove(id)?.also { (fromStation, fromTime) ->
                map.computeIfAbsent(fromStation to stationName) { IntArray(2) }.also {
                    it[0]++
                    it[1] += t - fromTime
                }
            }
        }

        fun getAverageTime(startStation: String, endStation: String): Double {
            return map[startStation to endStation]?.let { (count, time) -> time.toDouble() / count } ?: 0.0
        }
    }

    expect {
        UndergroundSystem().checkIn(
            1, "", 1
        )
    }
}

