package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun subdomainVisits(cpdomains: Array<String>): List<String> {
            class Domain(val name: String) {
                var count = 0

                val children = hashMapOf<String, Domain>()
            }

            val domains = arrayListOf<Domain>()

            fun Domain.addCount(c: Int, nodes: List<String>, index: Int) {
                count += c

                nodes.getOrNull(index)?.also {
                    children.computeIfAbsent(it) {
                        Domain("$it${
                            name.takeIf { it.isNotEmpty() }?.let { ".$it" }.orEmpty()
                        }"
                        ).also { domains.add(it) }
                    }.addCount(c, nodes, index - 1)
                }
            }

            val root = Domain("")

            cpdomains.map { it.split(" ").let { it[0].toInt() to it[1].split(".") } }.forEach { (count, nodes) ->
                root.addCount(count, nodes, nodes.lastIndex)
            }

            return domains.map { "${it.count} ${it.name}" }
        }
    }

    measureTimeMillis {
        Solution().subdomainVisits(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}