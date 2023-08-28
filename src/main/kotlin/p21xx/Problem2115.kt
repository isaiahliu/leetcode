package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun findAllRecipes(
            recipes: Array<String>,
            ingredients: List<List<String>>,
            supplies: Array<String>
        ): List<String> {
            val supplySet = supplies.toSet()
            val recipeMap = recipes.mapIndexed { index, s -> s to index }.toMap()

            val dependencies = Array(recipes.size) {
                hashSetOf<Int>()
            }

            val degrees = IntArray(recipes.size)

            val queue = LinkedList<Int>()
            ingredients.forEachIndexed { index, requirements ->
                for (requirement in requirements) {
                    when (requirement) {
                        in supplySet -> {

                        }

                        in recipeMap -> {
                            degrees[index]++

                            recipeMap[requirement]?.also {
                                dependencies[it].add(index)
                            }
                        }

                        else -> {
                            degrees[index] = Int.MAX_VALUE
                            return@forEachIndexed
                        }
                    }
                }

                if (degrees[index] == 0) {
                    queue.add(index)
                }
            }

            val result = arrayListOf<String>()

            while (queue.isNotEmpty()) {
                val index = queue.poll()
                result.add(recipes[index])

                dependencies[index].forEach {
                    degrees[it]--

                    if (degrees[it] == 0) {
                        queue.add(it)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().findAllRecipes(
            arrayOf(), listOf(), arrayOf()
        )
    }
}