package test

import java.lang.StringBuilder
import java.util.stream.IntStream
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


fun firstDigit(inputString: String) =
        inputString.toCharArray().first { it.isDigit() }

fun differentSymbolsNaive(s: String) = s.groupBy { it }.keys.size

fun arrayMaxConsecutiveSum(inputArray: MutableList<Int>, k: Int): Int {
    var maximum = 0
    for (i in k until inputArray.size) {
        var localSum = 0
        for (j in 0 until k) {
            localSum+= inputArray[i - j]
        }
        if (localSum > maximum) {
            maximum = localSum
        }
    }
    return maximum
}

fun buildPalindrome(st: String): String {
    var i = 0
    var addedStr = ""
    var resultString = st
    while (resultString != resultString.reversed()) {
        addedStr = st[i] + addedStr
        i++
        resultString = st + addedStr
    }
    return resultString
}

fun buildPalindrome2(st: String): String {
    if (st.length < 2) {
        return ""
    }
    for (i in 1 until st.length) {
        if (i < st.length - 1 && st[i - 1] == st[i + 1]) {
            val left = st.substring(0 until i)
            val right = st.substring(i + 1 until st.length)
            val reversedLeft = left.reversed()
            if (reversedLeft.startsWith(right)) {
                return st + reversedLeft.replaceFirst(right, "")
            }
        }
        if (st[i - 1] == st[i]) {
            val left = st.substring(0 until i)
            val right = st.substring(i until st.length)
            val reversedLeft = left.reversed()
            if (reversedLeft.startsWith(right)) {
                return st + reversedLeft.replaceFirst(right, "")
            }
        }
    }
    val reversedStr = st.reversed()
    return st + reversedStr.subSequence(1 until reversedStr.length)
}

fun electionsWinners(votes: MutableList<Int>, k: Int): Int {
    var maxVotes = 0
    var countMax = 0
    for (i in 0 until votes.size) {
        if (votes[i] > maxVotes) {
            maxVotes = votes[i]
            countMax = 0
        } else if (votes[i] == maxVotes) {
            countMax++
        }
    }
    return when {
        k > 0 -> votes.count { (it + k) > maxVotes }
        countMax > 0 -> 0
        else -> 1
    }
}

fun isMAC48Address(inputString: String) =
        inputString.matches(Regex("((^|-)[0-9A-F]{2}){6}"))
fun addToResult(char: Char, charCount: Int, result: String) =
        if (charCount > 1) {
            result + charCount.toString() + char
        } else {
            result + char
        }

fun lineEncoding(s: String): String {
    var currentChar = s[0]
    var result = ""
    var charCount = 1
    for(i in 1 until s.length) {
        if(s[i] == currentChar) {
            charCount++
        } else {
            result = addToResult(currentChar, charCount, result)
            charCount = 1
            currentChar = s[i]
        }
        if (i == s.length - 1) {
            result = addToResult(currentChar, charCount, result)
        }
    }
    return result
}



fun checkValid(letter: Char, num: Char) =
        if (letter in 'a'..'h' && num in '1'..'8') 1 else 0

fun chessKnight(cell: String): Int {
    var countMoves = 0
    countMoves += checkValid(cell[0] - 2, cell[1] - 1)
    countMoves += checkValid(cell[0] - 1, cell[1] - 2)
    countMoves += checkValid(cell[0] + 1, cell[1] - 2)
    countMoves += checkValid(cell[0] + 2, cell[1] - 1)
    countMoves += checkValid(cell[0] + 2, cell[1] + 1)
    countMoves += checkValid(cell[0] + 1, cell[1] + 2)
    countMoves += checkValid(cell[0] - 2, cell[1] + 1)
    countMoves += checkValid(cell[0] - 1, cell[1] + 2)
    return countMoves
}

fun deleteDigit(n: Int): Int {
    val numAsStr = n.toString()
    var maxNum = 0
    for (i in numAsStr.indices) {
        val newNum = numAsStr.removeRange(i..i).toInt()
        if (newNum > maxNum) maxNum = newNum
    }
    return maxNum
}

fun longestWord(text: String) =
        Regex("[a-zA-Z]*")
                .findAll(text)
                .map { it.groupValues[0] }
                .maxBy { it.length }!!

fun sumUpNumbers(inputString: String) =
        "(\\d+)".toRegex().findAll(inputString)
                .map{ it.value.toInt() }
                .sum()

fun validTime(time: String) = with(time.split(":")) {
    this[0].toInt() in 0..23 && this[1].toInt() in 0..59
}

/**
 * https://app.codesignal.com/arcade/intro/level-12/fQpfgxiY6aGiGHLtv
 */
fun differentSquares(matrix: MutableList<MutableList<Int>>): Int {
    val squares = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
    for (col in 0 until matrix.size - 1) {
        for (row in 0 until matrix[col].size - 1) {
            squares.add(Pair(Pair(matrix[col][row], matrix[col][row + 1]), Pair(matrix[col+1][row], matrix[col + 1][row + 1])))
        }
    }
    return squares.size
}

/**
 * https://app.codesignal.com/arcade/intro/level-12/NJJhENpgheFRQbPRA
 */
fun digitsProduct(product: Int): Int {
    if (product == 0) return 10
    if (product < 9) return product
    var result = ""
    var num = 9
    var residue = product
    while(residue > 1) {
        if (num < 2) return -1
        if (residue % num == 0) {
            result = num.toString() + result
            residue /= num
            num = 9
            continue
        }
        num--
    }
    return result.toInt()
}

/**
 * https://app.codesignal.com/interview-practice/task/C8Jdyk3ybixqQdAvM
 * Method Knuth-Morass-Pratt
 */
fun strstr(s: String, x: String): Int {
    val str = "$x@$s"
    val size = str.length
    val pi = IntArray(size)
    pi[0] = 0
    for (i in 1 until size) {
        var j = pi[i - 1]
        while ((j > 0) && (str[i] != str[j])) {
            j = pi[j - 1]
        }

        if (str[i] == str[j]) {
            j++
        }
        pi[i] = j
        if (j == x.length) {
            return i - (x.length * 2)
        }
    }
    return -1
}

/**
 * https://app.codesignal.com/arcade/intro/level-12/sqZ9qDTFHXBNrQeLC
 */
fun fileNaming(names: MutableList<String>): MutableList<String> {
    val namesMap = mutableMapOf<String, Int>()
    val result = mutableListOf<String>()
    for (i in names.indices) {
        var countOfName = namesMap[names[i]]?:0
        if (countOfName == 0) {
            namesMap[names[i]] = 1
            result.add(names[i])
        } else {
            while (namesMap.containsKey(names[i] + "($countOfName)")) {
                countOfName++
            }
            namesMap[names[i]] = countOfName
            namesMap[names[i] + "($countOfName)"] = 1
            result.add(names[i] + "($countOfName)")
        }
    }
    return result
}

/**
 * https://app.codesignal.com/arcade/intro/level-12/sCpwzJCyBy2tDSxKW
 */
fun messageFromBinaryCode(code: String): String {
    val chunkedCode = code.chunked(8)
    return chunkedCode.map {
        it.toInt(2).toChar()
    }.joinToString(separator = "")
}

/**
 * https://app.codesignal.com/arcade/intro/level-12/uRWu6K8E7uLi3Qtvx
 */
fun spiralNumbers(n: Int): MutableList<MutableList<Int>> {
    val result = MutableList(n) { MutableList(n) {0} }
    var row = 0
    var col = 0
    var num = 1
    var borderRight = n
    var borderLeft = -1
    var borderUp = -1
    var borderDown = n
    while (num - 1 < n * n) {
        while (col < borderRight) {
            result[row][col++] = num++
        }
        col--
        row++
        borderUp++
        while (row < borderDown) {
            result[row++][col] = num++
        }
        row--
        col--
        borderRight--
        while (col > borderLeft) {
            result[row][col--] = num++
        }
        col++
        row--
        borderDown--
        while (row > borderUp) {
            result[row--][col] = num++
        }
        row++
        col++
        borderLeft++
    }
    return result
}

private fun isValid(set: MutableSet<Int>): Boolean {
    return set.containsAll(listOf(1,2,3,4,5,6,7,8,9))
}

private fun checkColumn(grid: MutableList<MutableList<Int>>, col: Int): Boolean {
    val nums = mutableSetOf<Int>()
    for (row in 0 until grid.size) {
        nums.add(grid[row][col])
    }
    return isValid(nums)
}

private fun checkRow(grid: MutableList<MutableList<Int>>, row: Int): Boolean {
    val nums = mutableSetOf<Int>()
    for (col in 0 until grid.size) {
        nums.add(grid[row][col])
    }
    return isValid(nums)
}

private fun checkSquare(grid: MutableList<MutableList<Int>>, minRow: Int, minCol: Int): Boolean {
    val nums = mutableSetOf(grid[minRow][minCol], grid[minRow][minCol+1], grid[minRow][minCol + 2],
            grid[minRow + 1][minCol], grid[minRow+1][minCol+1], grid[minRow+1][minCol+2],
            grid[minRow+2][minCol], grid[minRow+2][minCol+1], grid[minRow+2][minCol+2])
    return isValid(nums)
}

/**
 * https://app.codesignal.com/arcade/intro/level-12/tQgasP8b62JBeirM
 */
fun sudoku(grid: MutableList<MutableList<Int>>): Boolean {
    for (i in grid.indices) {
        if (!checkRow(grid, i) || !checkColumn(grid, i)) return false
    }
    return checkSquare(grid, 0, 0) && checkSquare(grid, 0, 3) && checkSquare(grid, 0, 6) &&
            checkSquare(grid, 3, 0) && checkSquare(grid, 3, 3) && checkSquare(grid, 3, 6) &&
            checkSquare(grid, 6, 0) && checkSquare(grid, 6, 3) && checkSquare(grid, 6, 6)
}

/**
 * https://app.codesignal.com/challenge/aBAm93zzrw4rXxhQk
 */
fun sortByString(s: String, t: String): String {
    var letters = s
    val result = StringBuilder()
    for (i in t.indices) {
        val letter = t[i]
        val letterAsString = letter.toString()
        val countOfLetter = letters.count { it == letter }
        result.append(letterAsString.repeat(countOfLetter))
        letters = letters.replace(letterAsString, "")
    }
    return result.toString() + letters
}

/**
 * https://app.codesignal.com/arcade/code-arcade/intro-gates/aiKck9MwwAKyF8D4L
 */
fun lateRide(n: Int): Int {
    val hours = n / 60
    val minutes = n % 60
    return (hours.toString() + minutes.toString()).sumBy { Character.digit(it, 10) }
}

/**
 * https://app.codesignal.com/arcade/code-arcade/at-the-crossroads/m9wjpkCjgofg7gs8N
 */
fun phoneCall(min1: Int, min2_10: Int, min11: Int, s: Int): Int {
    if (s < min1) return 0
    var minutes = 1
    var money = s
    money -= min1
    if (money < min2_10 * 9) {
        minutes += money / min2_10
        return minutes
    }
    minutes += 9
    money -= min2_10 * 9
    minutes += money / min11
    return minutes
}

/**
 * https://app.codesignal.com/arcade/code-arcade/at-the-crossroads/r9azLYp2BDZPyzaG
 */
fun knapsackLight(value1: Int, weight1: Int, value2: Int, weight2: Int, maxW: Int): Int {
    if (weight1 + weight2 <= maxW) return value1 + value2
    if (maxW in weight1 until weight2) return value1
    if (maxW in weight2 until weight1) return value2
    if (value1 >= value2 && weight1 <= maxW) return value1
    if (value2 >= value1 && weight2 <= maxW) return value2
    return 0
}

/**
 * https://app.codesignal.com/arcade/code-arcade/corner-of-0s-and-1s/eC2Zxu5H5SnuKxvPT
 */
fun rangeBitCount(a: Int, b: Int) =
        IntStream.range(a,b).map { Integer.bitCount(it) }.sum()
/**
 * https://app.codesignal.com/arcade/code-arcade/loop-tunnel/hBw5BJiZ4LmXcy92u
 */
fun countSumOfTwoRepresentations2(n: Int, l: Int, r: Int): Int {
    var countSum = 0
    for (i in l .. r) {
        if (n - i in i..r) countSum++
    }
    return countSum
}

fun isLuckyNumber(n: Int): Boolean {
    return n.toString().filter { it == '4' || it == '7' }.count() == n.toString().length
}
fun digitDifferenceSort(a: MutableList<Int>): List<Int> {
    return a.sortedBy { it.toString().max()!! - it.toString().min()!! }
}
fun evenNumbersBeforeFixed(sequence: MutableList<Int>, fixedElement: Int): Int {
    val res = sequence.takeWhile { it != fixedElement }
    if (res.size == sequence.size) return -1
    return res.count { it % 2 == 0 }
}

fun characterParity(s: Char) =
        if (!s.isDigit()) "not a digit" else if (Character.getNumericValue(s) % 2 == 0) "even" else "odd"

/**
 * https://app.codesignal.com/arcade/code-arcade/loop-tunnel/LbuWRHnMoJH9SAo4o
 */
fun magicalWell(a: Int, b: Int, n: Int): Int {
    var money = 0
    for (i in 0 until n) {
        money += (a + i) * (b + i)
    }
    return money
}

/**
 * https://app.codesignal.com/arcade/code-arcade/loop-tunnel/8rqs3BLpdKePhouQM
 */
fun lineUp(commands: String): Int {
    var res = 0
    var eq = true
    for (i in commands) {
        if (i == 'L' || i =='R')
            eq = !eq
        res += if (eq) 1 else 0
    }
    return res
}

/**
 * https://app.codesignal.com/arcade/code-arcade/loop-tunnel/xzeZqCQjpfDJuN72S
 */
fun additionWithoutCarrying(param1: Int, param2: Int): Int {
    var firstNum = param1.toString()
    var secondNum = param2.toString()
    val diff = firstNum.length - secondNum.length
    if (diff > 0) secondNum = "0".repeat(diff) + secondNum
    if (diff < 0) firstNum = "0".repeat(abs(diff)) + firstNum
    var res = ""
    for (i in firstNum.indices) {
        res += ((Character.getNumericValue(firstNum[i]) + Character.getNumericValue(secondNum[i])) % 10).toString()
    }
    return res.toInt()
}

fun calcCountApples (m: Int, apples: IntArray): IntArray {
    if (m == 0) return apples
    if (m % 2 == 0) {
        apples[1] += m * m
    } else {
        apples[0] += m * m
    }
    return calcCountApples(m - 1, apples)
}

/**
 * https://app.codesignal.com/arcade/code-arcade/loop-tunnel/scG8AFsPuqQGx8Qjf/solutions
 */
fun appleBoxes(k: Int): Int {
    val apples = calcCountApples(k, IntArray(2))
    return apples[1] - apples[0]
}
