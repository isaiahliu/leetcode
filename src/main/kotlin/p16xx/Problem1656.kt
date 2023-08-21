package p16xx

import util.expect

fun main() {
    class OrderedStream(n: Int) {
        var ptr = 0

        var index = 0
        val indices = arrayOfNulls<String>(n)

        fun insert(idKey: Int, value: String): List<String> {
            val result = arrayListOf<String>()

            indices[idKey - 1] = value

            while (true) {
                indices.getOrNull(index)?.also {
                    result.add(it)
                    ptr++
                    index++
                } ?: break
            }

            return result
        }
    }

    expect {
        val os = OrderedStream(5)
        os.insert(3, "ccccc")
        os.insert(1, "aaaaa")
        os.insert(2, "bbbbb")
        os.insert(5, "eeeee")
        os.insert(4, "ddddd")
    }
}

