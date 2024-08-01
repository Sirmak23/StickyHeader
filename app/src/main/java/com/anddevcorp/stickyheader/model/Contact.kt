package com.anddevcorp.stickyheader.model

import java.time.LocalDate
import java.time.Month

object Contacts {

    fun getDummyContacts(): List<Contact> {
        val names = listOf(
            "Alice", "Albert", "Alicia", "Aaron",
            "Bob", "Billy", "Bobby", "Barbara",
            "Charlie", "Catherine", "Carl", "Christina",
            "David", "Daniel", "Diana", "Derek",
            "Edward", "Eve", "Ethan", "Ella",
            "Frank", "Fiona", "Fred", "Francesca",
            "George", "Grace", "Gavin", "Gloria",
            "Hannah", "Henry", "Helen", "Harry",
            "Ivy", "Isaac", "Isabelle", "Ian",
            "Jack", "James", "Jessica", "Jill",

            )

        val months = Month.entries.toTypedArray()
        return names.mapIndexed { index, name ->
            Contact(
                id = index,
                firstName = name,
                lastName = "LastName$index",
                date = LocalDate.of(2023, months[index % 12], (index % 28) + 1)
            )
        }
    }
}

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val date: LocalDate
)