package p17xx

fun main() {
    class AuthenticationManager(private val timeToLive: Int) {
        val tokens = hashMapOf<String, Int>()

        fun generate(tokenId: String, currentTime: Int) {
            tokens[tokenId] = currentTime + timeToLive
        }

        fun renew(tokenId: String, currentTime: Int) {
            tokens[tokenId]?.takeIf { currentTime < it }?.also {
                generate(tokenId, currentTime)
            }
        }

        fun countUnexpiredTokens(currentTime: Int): Int {
            return tokens.count { (_, value) -> currentTime < value }
        }

    }
}
