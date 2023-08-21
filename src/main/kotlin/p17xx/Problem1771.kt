package p17xx

import util.expect

fun main() {
    class Solution {
        fun longestPalindrome(word1: String, word2: String): Int {
            val word = word1 + word2

            val leftIndices = IntArray(26) { -1 }
            val rightIndices = IntArray(26) { -1 }

            for (index in word1.lastIndex downTo 0) {
                leftIndices[word1[index] - 'a'] = index
            }
            for (index in word2.indices) {
                rightIndices[word2[index] - 'a'] = word2.lastIndex - index
            }

            val cache = Array(word.length) { IntArray(word.length) { -1 } }
            fun dfs(left: Int, right: Int): Int {
                return when {
                    left > right -> {
                        0
                    }

                    left == right -> {
                        1
                    }

                    cache[left][right] >= 0 -> {
                        cache[left][right]
                    }

                    else -> {
                        val result = if (word[left] == word[right]) {
                            dfs(left + 1, right - 1) + 2
                        } else {
                            dfs(left + 1, right).coerceAtLeast(dfs(left, right - 1))
                        }

                        cache[left][right] = result
                        return result
                    }
                }
            }

            var result = 0
            repeat(26) {
                if (leftIndices[it] >= 0 && rightIndices[it] >= 0) {
                    result = result.coerceAtLeast(2 + dfs(leftIndices[it] + 1, word.lastIndex - rightIndices[it] - 1))
                }
            }

            return result
        }

    }

    expect {
        Solution().longestPalindrome(
            "fwjltqemdtmlhadgwymcasllmdetyfggohqfoiphmaadfbhkkdnpeubislwgrkpejxjalpaytzhhbrvrjfpjrcgdfsakeqxexbgfhevhruhhwxsjqtnhxsemsvvxsyevfmmqevdsqergvfphpckbpeoeqyrmmdzafgtshitmibbpoucyynengtaaixahewgvpsiufqxgilmtazymycrxnjajdgpdluhvtftaummsbfureuiptxmedlxothitffouogmucgwznfobcbveengtmqdknzvzzysmjwgnbbykimpjckyzsebcbfpknxbodxpigmfnhxlmywdwptmkzuxxuvhbcixbsgngrtxgbcjuyctaqvzayvzeofbubuyhhgraohnhuelbqrlnbzlxahpsmgwpiflujdazsqrmrmtssudexfiulbyzjgmuucobevbeeofefsekshtdlidbfdbtywhbjerawvfobnadfcbzbkptozixprdqzvrzthhjyrnpajyswpogxvmefzfckgpwadkxzaqktvhzegtqlgxkfynowfmllyjigzldltvwfxseifqqishhuoguviviuvpkfljauaqtmynjwleevsdcsstcydfhlbhlnnyriomuzaxrvwlyyowsmclsudlojdnyjzvpnoegzltxgmeqkbfqdcutwgaupzkpnftkxhbyjcabavxohtkktkdejziuyzrctmkpukfsjbjznarrtgeyvdxrsoyikddlnxuonmbtrkadgmhwjpnvlmoonczsjpjpcevcdvuxqmyfylyfcnqahzynsfqcobglkdehuapfpjgsiztsiobjkcpopbloplalgwzeccjnnkivvqvidmhxcpzefrqrlhjcyyfolyzogmbjiakufyjytmjgjwylwpjvixougyggjmbzarudlmlyhvcxbhuqurxlznwkkrjbyiioumtsmybrtzvibqyvhibxmvgkoiyzmjdrqvyygzfq",
            "oiaynrbkfkbgzynphprvucylwnfwsrvldkcgbdedgrktzeomjayyoelproupvxomxcbrrluekpnaldblhgvbxumjxqxmuvbebygwhsmxtklotlbzbdcxzscebazdanyksvhrqhgootomabyzjgjwhikissvzywlcirgnfsneshmakmcwnusdqphpcyfjsvwisklwvhrytmdjhwimrsozirggxfrghjuzccxihgslgeuemocqseggpoekbbzvgqwegzvsyetzyobskohgcmicalqwfkjppidojlqgncxyjyhyxnyidgsginlaaupkdlwhrwcddbnjbstblfkokauobwqquuuybmdznuvqzdvzlmndejmhmotuvxsumxrpnfduxvbhdbplfxhsuupswbynzolzsxsiwfgtphokgdyfxfbzdwnqdvgytztmhmcnloovrntdzwoawrvnbzbmvbuonkbstopivnvxpwzfsscekvcmhbhktjvgloxvgqgddinunuvjheziihadvzbyvkqedtvxotksicthjvnjffujhfehovplvaskmblxhkypaplqccyeboclwziheiqxulmpufpzkrxngismorycagltcbftptpmuhnplxohaqyfufofshdbmuqbwgkeswgvxesmcyeovixplrwrhwbfzlireqzxkcqpehhgxlhtztwkdwkxteqieolyimboxqintkbvyuzljzhxbxubqvguigdlpsydragglmdghtfzdohhsalalbfcpzgrjbivoathyfqenlsfyzupoktcydtystmbldgownnvgvbsmehlubxlkjgbkogjapbnblrjnjnqvibmugubxgddxbkxgjxnspbvykudcztsyazwlrlvybenfxdiywneagsnnjyknfyrwiwtdebbocwqrijtwrfphfawjridbsxfqalzurcttsstalqeqaqfnmyoanqccafzkqmjfwhaoaugyhqvesbctjgc"
        )
    }
}
