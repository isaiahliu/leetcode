package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class TopVotedCandidate(persons: IntArray, times: IntArray) {
        val result = TreeMap<Int, Int>()

        init {
            val personVotes = hashMapOf<Int, Int>()
            val map = hashMapOf<Int, LinkedHashSet<Int>>()
            var maxVote = 0

            persons.indices.sortedWith(compareBy<Int> { times[it] }.thenBy { it }).forEach {
                val person = persons[it]
                val time = times[it]

                var vote = personVotes[person] ?: 0

                map[vote]?.remove(person)

                vote++

                maxVote = maxVote.coerceAtLeast(vote)

                personVotes[person] = vote
                map.computeIfAbsent(vote) { LinkedHashSet() }.add(person)

                map[maxVote]?.last()?.also {
                    result[time] = it
                }
            }
        }

        fun q(t: Int): Int {
            return result.lowerEntry(t + 1).value
        }
    }

    measureTimeMillis {
        TopVotedCandidate(intArrayOf(), intArrayOf()).q(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}