package p09xx

import java.util.*
import util.expect

fun main() {
    class TopVotedCandidate(persons: IntArray, times: IntArray) {
        val result = TreeMap<Int, Int>()

        init {
            val personVotes = hashMapOf<Int, Int>()
            val map = hashMapOf<Int, LinkedHashSet<Int>>()
            var maxVote = 0

            persons.indices.forEach {
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

    expect {
        TopVotedCandidate(intArrayOf(), intArrayOf()).q(
            1
        )
    }
}