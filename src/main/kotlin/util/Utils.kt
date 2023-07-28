package util

import java.io.File

val input by lazy {
    ClassLoader.getSystemResource("util/sample.txt")?.file?.let { File(it) }
        ?.readLines()
        .orEmpty()

}

object Ints

operator fun Ints.get(vararg params: Int): IntArray {
    return intArrayOf(*params)
}

object Longs

operator fun Longs.get(vararg params: Long): LongArray {
    return longArrayOf(*params)
}

object Arr

inline operator fun <reified T> Arr.get(vararg params: T): Array<T> {
    return arrayOf(*params)
}