fun main() {
    class Solution {
        fun alertNames(keyName: Array<String>, keyTime: Array<String>): List<String> {
            val times = keyName.mapIndexed { index, s -> s to keyTime[index].replace(":", "").toInt() }
                .groupBy({ it.first }, { it.second })

            return times.filter { (_, values) ->
                values.sorted().let {
                    for (i in 0 until it.size - 2) {
                        if (it[i + 2] - it[i] <= 100) {
                            return@let true
                        }
                    }

                    false
                }
            }.keys.sorted()
        }
    }
}
