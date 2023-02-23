package p02xx

fun main() {
    class Solution {
        val map = hashMapOf<Int, MutableList<Pair<Int, Int>>>()
        val result = arrayListOf<String>()

        inner class Node {
            var str: String? = null

            var strFound = true

            val done: Boolean
                get() {
                    return children.all { it?.done ?: true } && (strFound)
                }
            val children = arrayOfNulls<Node>(26)

            operator fun Array<Node?>.get(char: Char): Node? {
                return this[char - 'a']
            }

            operator fun Array<Node?>.set(char: Char, node: Node?) {
                this[char - 'a'] = node
            }

            fun insert(word: String, index: Int = 0) {
                if (index == word.length) {
                    str = word
                    strFound = false
                } else {
                    val child = children[word[index]] ?: run {
                        Node().also { children[word[index]] = it }
                    }

                    child.insert(word, index + 1)
                }
            }

            fun dfs(
                last: Pair<Int, Int>? = null,
                route: Set<Pair<Int, Int>> = emptySet()
            ) {
                if (done) {
                    return
                }

                str?.also {
                    if (!strFound) {
                        result.add(it)
                        strFound = true
                    }
                }

                children.withIndex().mapNotNull { (index, child) ->
                    child?.takeIf { !it.done }?.let { index to it }
                }.forEach { (index, child) ->
                    val pos = map[index].orEmpty()

                    for (p in pos) {
                        if (last != null) {
                            val (r, c) = last

                            if (p !in (arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1))) {
                                continue
                            }
                        }

                        if (p in route) {
                            continue
                        }

                        child.dfs(p, route + p)

                        if (child.done) {
                            break
                        }
                    }
                }
            }
        }

        fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
            board.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, c ->
                    map.computeIfAbsent(c - 'a') { arrayListOf() }.add(rowIndex to columnIndex)
                }
            }

            val root = Node()
            words.forEach {
                root.insert(it)
            }

            root.dfs()

            return result
        }
    }

    println(
        Solution().findWords(
            arrayOf(
                charArrayOf('a', 'b', 'c'),
                charArrayOf('a', 'e', 'd'),
                charArrayOf('a', 'f', 'g'),
            ),
            arrayOf("abcdefg", "befa", "eaabcdgfa", "gfedcbaaa")
        )
    )
}

