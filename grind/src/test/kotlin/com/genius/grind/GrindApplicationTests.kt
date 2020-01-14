
package com.genius.grind

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.streams.toList

/*@SpringBootTest*/
class GrindApplicationTests {

    /*@Test
    fun contextLoads() {
    }*/

    class Person(fName: String, lName:String) {
        val firstName = fName.toUpperCase()
        val lastName = lName.toUpperCase()
    }

    fun meeting(s: String): String {
        val persons : List<Person> = s.split(";")
                .stream()
                .map { p -> p.split(":") }
                .map{ splittedP ->
                    Person(splittedP[0], splittedP[1])
                }.toList()

        val sortedPersons = persons.sortedWith(compareBy(Person::lastName, Person::firstName))

        return sortedPersons.joinToString(separator = "") { "(${it.lastName}, ${it.firstName})" }

    }

    @Test
    fun testFixed() {
        assertEquals("(ARNO, ANN)(BELL, JOHN)(CORNWELL, ALEX)(DORNY, ABBA)(KERN, LEWIS)(KORN, ALEX)(META, GRACE)(SCHWARZ, VICTORIA)(STAN, MADISON)(STAN, MEGAN)(WAHL, ALEXIS)", meeting("Alexis:Wahl;John:Bell;Victoria:Schwarz;Abba:Dorny;Grace:Meta;Ann:Arno;Madison:STAN;Alex:Cornwell;Lewis:Kern;Megan:Stan;Alex:Korn"))
        assertEquals("(BELL, MEGAN)(CORNWELL, AMBER)(DORNY, JAMES)(DORRIES, PAUL)(GATES, JOHN)(KERN, ANN)(KORN, ANNA)(META, ALEX)(RUSSEL, ELIZABETH)(STEVE, LEWIS)(WAHL, MICHAEL)", meeting("John:Gates;Michael:Wahl;Megan:Bell;Paul:Dorries;James:Dorny;Lewis:Steve;Alex:Meta;Elizabeth:Russel;Anna:Korn;Ann:Kern;Amber:Cornwell"))
        assertEquals("(ARNO, ALEX)(ARNO, HALEY)(BELL, SARAH)(CORNWELL, ALISSA)(DORNY, PAUL)(DORRIES, ANDREW)(KERN, ANN)(KERN, MADISON)", meeting("Alex:Arno;Alissa:Cornwell;Sarah:Bell;Andrew:Dorries;Ann:Kern;Haley:Arno;Paul:Dorny;Madison:Kern"))
    }

}
