package p23xx

import util.expect
import java.util.*

fun main() {
    class FoodRatings(foods: Array<String>, cuisines: Array<String>, ratings: IntArray) {
        val scores = hashMapOf<String, Int>()

        val foodMap = hashMapOf<String, String>()

        val cuisineMap = hashMapOf<String, TreeMap<Int, SortedSet<String>>>()

        init {
            foods.indices.forEach {
                scores[foods[it]] = ratings[it]
                foodMap[foods[it]] = cuisines[it]
                cuisineMap.computeIfAbsent(cuisines[it]) { TreeMap(compareByDescending { it }) }
                    .computeIfAbsent(ratings[it]) { sortedSetOf() }.add(foods[it])
            }
        }

        fun changeRating(food: String, newRating: Int) {
            scores[food]?.also { oldRating ->
                cuisineMap[foodMap[food]]?.also { scoreMap ->
                    scoreMap[oldRating]?.also {
                        it.remove(food)

                        if (it.isEmpty()) {
                            scoreMap.remove(oldRating)
                        }
                    }
                }
            }

            scores[food] = newRating
            cuisineMap[foodMap[food]]?.computeIfAbsent(newRating) { sortedSetOf() }?.add(food)
        }

        fun highestRated(cuisine: String): String {
            return cuisineMap[cuisine]?.firstEntry()?.value?.firstOrNull().orEmpty()
        }
    }

    expect {
        FoodRatings(arrayOf(), arrayOf(), intArrayOf()).highestRated(
            ""
        )
    }
}