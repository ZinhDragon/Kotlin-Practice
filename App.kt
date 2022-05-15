import java.util.Scanner
fun main(args: Array<String>){
    val numberList = mutableListOf<Int>()
    generateRandomness(numberList)
    println("Original List: $numberList")
    val newList = when(numberList.size){
        in  1 until 20 -> selectionSort(numberList)
        in 20 until 40 -> bubbleSort(numberList)
        //in 15 until 20 -> recursiveBubbleSort(numberList) //Work on later
        in 40 until 80 -> insertionSort(numberList)
        in 80 until 100 -> mergeSort(numberList)
        else -> quickSort(numberList,0,numberList.size-1)
    }
    println("Sorted List:   $newList")
    println("Target number to search:")
    val read = Scanner(System.`in`)
    val targetNum = read.nextInt()
    println(if(binarySearch(numberList,targetNum))
        "$targetNum is in the List"
    else
        "$targetNum is not in the list")
}
// Generating Randomness
fun generateRandomness(
    numberList:MutableList<Int>,
):MutableList<Int>{
    println("Input the length of the List:")
    val read = Scanner(System.`in`)
    val k = read.nextInt()
    repeat(k){
        numberList.add((1..1000).random())
    }
    return numberList
}
//InsertionSort
fun insertionSort(
    numberList: MutableList<Int>,
):MutableList<Int>{
    for(j in 1 until numberList.size){
        val key = numberList[j]
        var i = j
        while(i > 0 && numberList[i-1] >= key){
            numberList[i] = numberList[i-1]
            i--
        }
        numberList[i] = key
    }
    return numberList
}
//MergeSort
fun mergeSort(
    numberList: MutableList<Int>,
):MutableList<Int>{
    if(numberList.size <= 1){
        return numberList
    }
    val middle = numberList.size/2
    val leftList = numberList.subList(0,middle)
    val rightList = numberList.subList(middle,numberList.size)
    return merge(mergeSort(leftList),mergeSort(rightList))
}
fun merge(
    leftList:MutableList<Int>,
    rightList:MutableList<Int>,
):MutableList<Int>{
    var leftIndex = 0
    var rightIndex = 0
    val newList = mutableListOf<Int>()
    while(leftIndex < leftList.size && rightIndex < rightList.size){
        if(leftList[leftIndex] < rightList[rightIndex]){
            newList.add(leftList[leftIndex])
            leftIndex++
        } else {
            newList.add(rightList[rightIndex])
            rightIndex++
        }
    }

    while(leftIndex < leftList.size){
        newList.add(leftList[leftIndex])
        leftIndex++
    }
    while(rightIndex < rightList.size){
        newList.add(rightList[rightIndex])
        rightIndex++
    }
    return newList
}
//QuickSort
fun quickSort(
    numbers:MutableList<Int>,
    low:Int,
    high:Int,
):List<Int>{
    if(low < high){
        val pivot:Int = partition(numbers,low,high)
        quickSort(numbers,low,pivot-1)  //Before pivot
        quickSort(numbers,pivot+1,high) //After pivot
    }
    return numbers
}
fun partition(
    numbers: MutableList<Int>,
    low: Int,
    high: Int,
):Int{
    val pivot = numbers[high]
    var i = (low - 1)
    for(j in low until high){
        if(numbers[j]<pivot){
            i++
            numbers[i]=numbers[j].also { numbers[j]=numbers[i] }
        }
    }
    numbers[i+1]=numbers[high].also{numbers[high] = numbers[i+1]}
    return (i+1)
}
//SelectionSort
fun selectionSort(
    numbers:MutableList<Int>
):List<Int>{
    for(i in 0 until numbers.size){
        var minIdx = i
        for(j in i+1 until numbers.size){
            if(numbers[j] < numbers[minIdx])
                minIdx = j
            numbers[minIdx] = numbers[i].also { numbers[i] = numbers[minIdx] }
        }
    }
    return numbers
}
//BubbleSort
fun bubbleSort(
    numbers:MutableList<Int>
):List<Int>{
    for(i in 0 until numbers.size){
        for(j in 0 until numbers.size - i){
            if(numbers[j] > numbers[j + i])
                numbers[j] = numbers[j + 1].also{numbers[j+1] = numbers[j]}
        }
    }
    return numbers
}
/*RecursiveBubbleSort
fun recursiveBubbleSort(
    numbers:MutableList<Int>,
):List<Int>{
    if(numbers.size == 1)
        return numbers
    for(i in 0 until numbers.size)
        if(numbers[i] > numbers[i+1])
            numbers[i] = numbers[i+1].also{numbers[i+1] = numbers[i]}
    return recursiveBubbleSort(numbers)
}
*/

//Binary Search
fun binarySearch(
    numbers:MutableList<Int>,
    targetNum:Int,
):Boolean{
    var first = 0
    var last = numbers.size - 1
    while(last >= first){
        val mid = (first + last)/2
        if(numbers[mid] == targetNum){
            return true
        } else {
            if(targetNum < numbers[mid])
                last = mid - 1
            else
                first = mid + 1
        }
    }
    return false
}

//

