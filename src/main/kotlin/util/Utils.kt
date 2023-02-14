package util

import java.io.File

val input by lazy {
    ClassLoader.getSystemResource("util/sample.txt")?.file?.let { File(it) }
        ?.readLines()
        .orEmpty()

}