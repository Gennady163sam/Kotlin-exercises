package test

import java.lang.StringBuilder
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.max

fun commonCharacterCount(s1: String, s2: String): Int {
    var counter = 0
    var inputString = s2
    for (element in s1) {
        if (inputString.length > inputString.replaceFirst(element.toString(), "").length) {
            counter++
            inputString = inputString.replaceFirst(element.toString(), "")
        }
    }
    return counter
}

fun matrixElementsSum(matrix: MutableList<MutableList<Int>>): Int {
    var sum = 0;
    val excludedColumn = mutableSetOf<Int>()
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[i].size) {
            if (matrix[i][j] == 0) {
                excludedColumn.add(j)
            }
            if (!excludedColumn.contains(j)) {
                sum += matrix[i][j]
            }
        }
    }
    return sum
}

fun allLongestStrings(inputArray: MutableList<String>) =
        inputArray.groupBy {it.length}
                .maxBy { it.key }
                ?.value.orEmpty()

fun higherVersion2(ver1: String, ver2: String): Int {
    val v1 = ver1.split(".").map{it.toInt()}
    val v2 = ver2.split(".").map {it.toInt()}
    for (i in v1.indices) {
        if (v1[i] > v2[i]) {
            return 1
        }
        if (v2[i] > v1[i]) {
            return -1
        }
    }
    return 0
}

fun mutateTheArray(n: Int, a: MutableList<Int>): MutableList<Int> {
    val b = mutableListOf<Int>()
    for (i in 0 until n) {
        var res = 0
        if (i - 1 > -1) {
            res += a[i - 1]
        }
        res += a[i]
        if (i + 1 < n) {
            res += a[i + 1]
        }
        b.add(res)
    }
    return b
}

fun countTinyPairs(a: MutableList<Int>, b: MutableList<Int>, k: Int): Int {
    var counter = 0
    for (i in 0 until a.size) {
        val concated = a[i].toString() + b[b.size - 1 - i].toString()
        if (concated.toInt() < k) counter++
    }
    return counter
}

fun meanGroups(a: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
    val result :MutableMap<Double, MutableList<Int>> = mutableMapOf()
    for (i in 0 until a.size) {
        val avg = a[i].average()
        if (!result.containsKey(avg)) {
            result[avg] = mutableListOf()
        }
        result[avg]?.add(i)
    }
    return result.values.toCollection(mutableListOf())
}
fun firstNotRepeatingCharacter(s: String): Char {
    val counts = s.groupingBy { it }.eachCount()
    return counts
            .filter { it.value == 1 }
            .map { it.key }
            .firstOrNull() ?: '_'
}

fun makeArrayConsecutive2(statues: MutableList<Int>): Int {
    statues.sort()
    var res:Int = 0;
    for (i in 1 until statues.size) {
        res += statues[i] - statues[i - 1] - 1;
    }
    return res;
}

fun almostIncreasingSequence(sequence: MutableList<Int>): Boolean {
    var deletedIndex = -1;
    // find need index
    for (i in 1 until sequence.size) {
        if (sequence[i] - sequence[i - 1] <= 0) {
            deletedIndex = i
            break
        }
    }
    if (deletedIndex != -1 && !isIncreasingSequence(deletedIndex - 1, sequence)) {
        // try to delete right element
        return isIncreasingSequence(deletedIndex, sequence)
    }
    return true
}

fun isIncreasingSequence(removedIndex: Int, sequence: MutableList<Int>): Boolean {
    for (i in 0 until sequence.size - 1) {
        if (removedIndex > i || (removedIndex == 0 && i == 0)) {
            continue
        }
        var needIndex = i
        if (needIndex == removedIndex) {
            needIndex -= 1
        }
        if (sequence[needIndex] >= sequence[i + 1]) {
            return false
        }
    }
    return true
}


fun areSimilar(a: MutableList<Int>, b: MutableList<Int>): Boolean {
    val incorrectIndexes = mutableListOf<Int>()

    for(i in 0 until a.size) {
        if (a[i] != b[i]) {
            incorrectIndexes.add(i)
        }
    }

    if (incorrectIndexes.size == 0) return true

    if (incorrectIndexes.size != 2) return false

    val firstIndex = incorrectIndexes[0]
    val secondIndex = incorrectIndexes[1]

    for (i in 0 until a.size) {
        var index = i
        if (i == firstIndex) {
            index = secondIndex
        }
        if (i == secondIndex) {
            index = firstIndex
        }
        if (a[i] != b[index]) {
            return false
        }
    }
    return true
}
fun isLucky(n: Int) = n
        .toString()
        .substring(0 until n.toString().length / 2)
        .sumBy(Char::toInt) ==
        n.toString()
                .substring(n.toString().length / 2 until n.toString().length)
                .sumBy(Char::toInt)

fun reverseWord(iterator: CharIterator): String {
    var str = ""

    do {
        val char = iterator.nextChar()
        if (char == '(') {
            str += reverseWord(iterator)
        } else if (char != ')') {
            str += char
        }
    } while (char != ')')
    return str.reversed()
}

fun reverseInParentheses(inputString: String): String {
    var res = ""
    val iterator = inputString.iterator()
    while (iterator.hasNext()) {
        val char = iterator.next()
        if (char == '(') {
            res += reverseWord(iterator)
        } else {
            res += char
        }
    }
    return res
}

fun arrayChange(inputArray: MutableList<Int>): Int {
    var steps = 0
    for (i in 0 until inputArray.size - 1) {
        if (inputArray[i] >= inputArray[i + 1]) {
            val localSteps = abs(inputArray[i + 1] - inputArray[i]) + 1
            inputArray[i + 1] += localSteps
            steps += localSteps
        }
    }
    return steps
}


fun palindromeRearranging(inputString: String): Boolean {
    val charsCount = inputString.toCharArray()
            .toList()
            .groupingBy { it }
            .eachCount()

    var flag = false
    for (charCount in charsCount.entries) {
        if (charCount.value % 2 != 0) {
            if (flag) {
                return false
            }
            flag = true
        }
    }
    return true
}

fun arrayMaximalAdjacentDifference(inputArray: MutableList<Int>): Int {
    var maxDifference = 0
    for (i in 1 until inputArray.size) {
        maxDifference = max(abs(inputArray[i] - inputArray[i - 1]), maxDifference)
    }
    return maxDifference
}


fun alternatingSums(a: MutableList<Int>): MutableList<Int> {
    var sumFirstTeam = 0;
    var sumSecondTeam = 0;
    a.forEachIndexed { index, i -> if (index % 2 == 0) sumFirstTeam+=i else sumSecondTeam += i; }
    return mutableListOf(sumFirstTeam, sumSecondTeam)
}

fun addBorder(picture: MutableList<String>): MutableList<String> {
    val len = picture[0].length
    var headerAndFooter = ""
    for (i in 0 until len + 2) {
        headerAndFooter += "*"
    }
    val res = mutableListOf(headerAndFooter)
    res.addAll(picture.map { v -> "*$v*" })
    res.add(headerAndFooter)
    return res

}

fun minesweeper(matrix: MutableList<MutableList<Boolean>>): MutableList<MutableList<Int>> {
    val result: MutableList<MutableList<Int>> = mutableListOf()
    for (row in 0 until matrix.size) {
        val newRow = mutableListOf<Int>()
        for (column in 0 until matrix[row].size) {
            var countBomb = 0
            countBomb += if (row > 0 && column > 0 && matrix[row - 1][column - 1]) 1 else 0
            countBomb += if (row > 0 && matrix[row - 1][column]) 1 else 0
            countBomb += if (row > 0 && column < matrix[row].size - 1 && matrix[row - 1][column + 1]) 1 else 0

            countBomb += if (column > 0 && matrix[row][column - 1]) 1 else 0
            countBomb += if (column < matrix[row].size - 1 && matrix[row][column + 1]) 1 else 0

            countBomb += if (row < matrix.size - 1 && column > 0 && matrix[row + 1][column - 1]) 1 else 0
            countBomb += if (row < matrix.size - 1 && matrix[row + 1][column]) 1 else 0
            countBomb += if (row < matrix.size - 1 && column < matrix[row].size - 1 && matrix[row + 1][column + 1]) 1 else 0

            newRow.add(countBomb)
        }
        result.add(newRow)
    }
    return result
}

fun isIPv4Address(inputString: String) = """^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\.(?!${'$'})|${'$'})){4}${'$'}""".toRegex() matches inputString

fun avoidObstacles(inputArray: MutableList<Int>): Int {
    var jumps = 1
    val maximum = inputArray.max()?: 1000
    var current = 0
    while (true) {
        current += jumps
        if (current in inputArray) {
            jumps += 1
            current = 0
        }
        if (current > maximum) {
            return jumps
        }
    }
}



fun containsDuplicates(a: MutableList<Int>): Boolean {
    val res = mutableSetOf<Int>()
    res.addAll(a)
    return res.size != a.size
}

fun evenDigitsOnly(n: Int): Boolean = !n.toString()
        .chars()
        .mapToObj { it.toChar() }
        .filter { it.toInt() % 2 != 0 }
        .findFirst()
        .isPresent

fun variableName(name: String): Boolean {
    if (name[0].isDigit()) return false
    return name.all { it.isLetterOrDigit() || it == '_' }
}

fun chessBoardCellColor(cell1: String, cell2: String): Boolean {
    val firstNum = cell1[0].toInt() + cell1[1].toInt()
    val secondNum = cell2[0].toInt() + cell2[1].toInt()
    return (firstNum % 2 == 0 && secondNum % 2 == 0) ||
            (firstNum % 2 != 0 && secondNum % 2 != 0)
}

fun arraySum(arr: IntArray): Int {
    if (arr.size == 1) return arr[0]
    return arr[0] + arraySum(arr.copyOfRange(1, arr.size))
}

fun arrayCount(arr: IntArray): Int {
    if (arr.size == 1) return 1
    return 1 + arrayCount(arr.copyOfRange(1, arr.size))
}

fun arrayMax(arr: IntArray): Int {
    if (arr.size == 1) return arr[0]
    val localMax = arrayMax(arr.copyOfRange(1, arr.size))
    return if (arr[0] > localMax) arr[0] else localMax
}

data class Tree<T>(var value: T) {
    var left: Tree<T>? = null;
    var right: Tree<T>? = null;
}

fun traverseTree(t: Tree<Int>?): MutableList<Int> {
    val result = mutableListOf<Int>()
    if (t == null) return result
    val nodes = mutableListOf<Tree<Int>>()
    nodes.add(t)
    var currentIndex = 0
    while (currentIndex < nodes.size) {
        val currentNode = nodes[currentIndex]
        result.add(currentNode.value)
        if (currentNode.left != null) nodes.add(currentNode.left!!)
        if (currentNode.right != null) nodes.add(currentNode.right!!)
        currentIndex++
    }
    return result
}


data class ListNode<T>(var value: T) {
    var next: ListNode<T>? = null;
}

fun removeKFromList(l: ListNode<Int>?, k: Int): ListNode<Int>? {
    var prev: ListNode<Int>? = null
    var current = l
    var head: ListNode<Int>? = null
    while (current != null) {
        if (current.value == k) {
            if (prev != null) {
                prev.next = current.next
            }
            current = current.next
            continue
        }
        if (head == null) head = current
        prev = current
        current = current.next
    }
    return head
}

fun absoluteValuesSumMinimization(a: MutableList<Int>): Int {
    var minSum = Long.MAX_VALUE
    var result = 0
    var currIndex = 0
    do {
        var absoluteSum = 0L
        for (i in 0 until a.size) {
            absoluteSum += abs(a[i] - a[currIndex])
        }
        if (absoluteSum > minSum) {
            break
        } else if (absoluteSum < minSum) {
            result = currIndex
            minSum = absoluteSum
        } else if (absoluteSum == minSum && a[result] > a[currIndex]) {
            result = currIndex
        }
        currIndex++
    } while(currIndex < a.size - 1)
    return a[result]
}

fun absoluteValuesSumMinimizationSimple(a: MutableList<Int>) =
        if (a.size % 2 == 0) a.size / 2 - 1 else a.size / 2


fun equalPairOfBits(n: Int, m: Int): Int {
    val firstRepresentation = Integer.toBinaryString(n)
    val secondRepresentation = Integer.toBinaryString(m)

    var i = 0
    while (i < firstRepresentation.length || i < secondRepresentation.length) {
        if (firstRepresentation[firstRepresentation.length - 1 - i] == secondRepresentation[secondRepresentation.length - 1 - i]) {
            break
        }
        i++
    }
    return Math.pow(2.0, i.toDouble()).toInt()
}


fun canTransform(from: String, to: String): Boolean {
    if (from.length != to.length) return false
    var oneChange = false
    for (i in from.indices) {
        if(from[i] != to[i]) {
            if (!oneChange) {
                oneChange = true
            } else {
                return false
            }
        }
    }
    return true
}
fun checkRearrangement(inputArray: MutableList<String>):Boolean {
    for (i in 1 until inputArray.size) {
        if (!canTransform(inputArray[i - 1], inputArray[i])) {
            return false
        }
    }
    return true
}

fun getPermutations(inputArray: MutableList<String>): MutableList<MutableList<String>> {
    if (inputArray.size == 1) return mutableListOf(inputArray)
    val permutations = mutableListOf<MutableList<String>>()
    val toInsert = inputArray[0]
    for (perm in getPermutations(inputArray.drop(1) as MutableList<String>)) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            permutations.add(newPerm)
        }
    }
    return permutations
}

fun stringsRearrangement(inputArray: MutableList<String>): Boolean {
    if (inputArray.size == 0 || inputArray.size == 1) return false
    val permutations = getPermutations(inputArray)
    return permutations.firstOrNull { checkRearrangement(it) } != null
}

fun depositProfit(deposit: Int, rate: Int, threshold: Int) =
        ceil(log((threshold.toDouble()/deposit), (1 + rate.toDouble() / 100))).toInt()


fun digitDegree(n: Int): Int {
    if (n < 10) return 0
    val sumOfNumbers = n.toString().sumBy { Character.getNumericValue(it) }
    return digitDegree(sumOfNumbers) + 1
}

fun bishopAndPawn(bishop: String, pawn: String) =
        Math.abs(bishop[0] - pawn[0]) == Math.abs(bishop[1] - pawn[1])

fun isBeautifulString(inputString: String): Boolean {
    val countOfLetters = inputString.groupingBy { it }.eachCount()
    for (letter in 'b' .. 'z') {
        if (countOfLetters[letter - 1]?: 0 < countOfLetters[letter]?: 0) return false
    }
    return true
}