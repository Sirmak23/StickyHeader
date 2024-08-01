package com.anddevcorp.stickyheader

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anddevcorp.stickyheader.model.Contact
import com.anddevcorp.stickyheader.model.Contacts.getDummyContacts
import com.anddevcorp.stickyheader.ui.theme.StickyHeaderTheme
import java.time.Month

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StickyHeaderTheme {
                ContactApp()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ContactApp() {
        val contacts = getDummyContacts()
        val groupedContacts = contacts.groupBy { it.date.month }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            Column {
                Text(
                    text = "Contacts",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                ContactsList(groupedContacts)
            }
        }
    }

    @Composable
    fun MonthHeader(month: Month) {
        Text(
            text = month.name,
            style = MaterialTheme.typography.titleSmall,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(8.dp)
        )
    }

    @Composable
    fun ContactListItem(contact: Contact) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.users),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "${contact.firstName} ${contact.lastName}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "ID: ${contact.id}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Date: ${contact.date}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ContactsList(grouped: Map<Month, List<Contact>>) {
        LazyColumn(
            modifier = Modifier.background(Color.Black)
        ) {
            grouped.forEach { (month, contactsForMonth) ->
                stickyHeader {
                    MonthHeader(month)
                }
                items(contactsForMonth) { contact ->
                    ContactListItem(contact)
                }
            }
        }
    }
}