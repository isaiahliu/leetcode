package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProduct(words: Array<String>): Int {
            fun String.toCharMap(): Int {
                var result = 0

                this.forEach {
                    result = result or (1 shl (it - 'a'))
                }

                return result
            }

            val sortedWords = words.map { it.toCharMap() to it.length }.groupingBy { it.first }.fold(0) { a, b ->
                a.coerceAtLeast(b.second)
            }.entries.sortedByDescending { it.value }

            var result = 0
            for (i in sortedWords.indices) {
                val (leftChars, leftLength) = sortedWords[i]
                for (j in i + 1 until sortedWords.size) {
                    val (rightChars, rightLength) = sortedWords[j]

                    if (leftLength * rightLength <= result) {
                        break
                    }

                    if (leftChars and rightChars == 0) {
                        result = leftLength * rightLength
                        break
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxProduct(
            arrayOf(
                "edadc",
                "ebbfe",
                "aacdde",
                "dfe",
                "cb",
                "fddddff",
                "fabca",
                "adccac",
                "ece",
                "ccaf",
                "feba",
                "bcb",
                "edadc",
                "aea",
                "bacb",
                "acefa",
                "fcebffd",
                "dfeebca",
                "bedcbaa",
                "deaccc",
                "abedc",
                "dadff",
                "eef",
                "ddebbb",
                "abecab",
                "cd",
                "abdeee",
                "eedce",
                "deef",
                "dceaddd",
                "ced",
                "fbbf",
                "ba",
                "eefeda",
                "fb",
                "cddc",
                "adafbf",
                "dded",
                "aadbf",
                "caefbaf",
                "ccebf",
                "dbb",
                "ee",
                "dadcecc",
                "ddbcabb",
                "afeaa",
                "ec",
                "aad",
                "efde",
                "cbcda",
                "cdbdafd",
                "cddc",
                "ecaaa",
                "ae",
                "cfc",
                "bccac",
                "cdcc",
                "abbbf",
                "fcdface",
                "ddbcdc",
                "bfebb",
                "daed",
                "bc",
                "dc",
                "ecdfc",
                "eeb",
                "bb",
                "dad",
                "caecb",
                "fbe",
                "bbbc",
                "cacea",
                "dbc",
                "fbe",
                "bcfffbd",
                "aeda",
                "cff",
                "ddfc",
                "ea",
                "bdfd",
                "ccb",
                "cb",
                "ae",
                "ceabefa",
                "dcea",
                "cbaed",
                "bfedf",
                "fa",
                "ccd",
                "fece",
                "bceef",
                "acabca",
                "dafa",
                "fdeec",
                "dac",
                "cae",
                "adeeadb",
                "ecacc",
                "acfe",
                "de"
            )
        ).also { println(it) }
    }
}

