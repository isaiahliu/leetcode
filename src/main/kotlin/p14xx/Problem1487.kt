package p14xx

import util.expect

fun main() {
    class Solution {
        fun getFolderNames(names: Array<String>): Array<String> {
            val map = hashMapOf<String, Int>()
            names.forEachIndexed { index, s ->
                map[s]?.also {
                    var t = it
                    while (true) {
                        val newName = "${s}(${t})"

                        if (newName in map) {
                            t++
                        } else {
                            names[index] = newName
                            map[newName] = 1
                            break
                        }
                    }

                    map[s] = t
                } ?: run {
                    map[s] = 1
                }
            }

            return names
        }
    }

    expect {
        Solution().getFolderNames(
            arrayOf("kaido", "kaido(1)", "kaido", "kaido(1)")
        ).toList()
    }
}

