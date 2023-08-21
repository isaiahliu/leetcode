package p14xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun peopleIndexes(favoriteCompanies: List<List<String>>): List<Int> {
            val favorites = hashMapOf<String, MutableSet<Int>>()

            val result = TreeSet<Int>()
            favoriteCompanies.mapIndexed { index, list -> index to list }.sortedByDescending { it.second.size }
                .forEach { (person, fav) ->
                    val same = favoriteCompanies.indices.toMutableSet()

                    fav.forEach {
                        favorites.computeIfAbsent(it) { hashSetOf() }.also {
                            same.retainAll(it)
                            it.add(person)
                        }
                    }

                    if (same.isEmpty()) {
                        result.add(person)
                    }
                }

            return result.toList()
        }
    }

    expect {
        Solution().peopleIndexes(
            listOf()
        )

    }
}

