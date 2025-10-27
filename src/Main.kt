private val people = listOf(
    Person("Петя", 25),
    Person("Вася", 30),
    Person("Даша", 25),
    Person("Женя", 30),
    Person("Алексей", 20),
)
private val peopleNames = listOf("Артем", "Андрей", "Даша", "Алена", "Алексей")

fun main() {
    println("first List:")
    people
        .groupingBy { it.age }
        .eachCount()
        .forEach { println("${it.key} - ${it.value}")}

    println("------------------------------------------")

    println("second List:")
    peopleNames
        .filter { it.startsWith("А") }
        .groupingBy { it.length }
        .eachCount()
        .forEach { (length, value) ->
            println("$length букв - $value слово(а)")
        }

}

data class Person(val name: String, val age: Int)