package p12xx

fun main() {
    class Solution {
        fun removeSubfolders(folder: Array<String>): List<String> {
            folder.sort()

            var top = folder[0] + "z"
            return buildList {
                folder.forEach {
                    if (!it.startsWith(top)) {
                        add(it)
                        top = "$it/"
                    }
                }
            }
        }
    }

    Solution().removeSubfolders(arrayOf("/a", "/a/b", "/c/d", "/c/d/e", "/c/f"))
}
