package com.example.basicroommigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.basicroommigration.ui.theme.BasicRoomMigrationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val database: MyDatabase by lazy {
        Room.databaseBuilder(applicationContext, MyDatabase::class.java, "myDb")
            .addMigrations(MyDatabase.MIGRATION_3_4)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicRoomMigrationTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { insertBooks() }) {
                        Text(text = "Insert Books")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { insertAuthors() }) {
                        Text(text = "Insert Authors")
                    }
                }
            }
        }
    }

    private fun insertBooks() {
        lifecycleScope.launch {
            val listOfBooks = mutableListOf<BookEntity>()
            for (i in 11 .. 15) {
                listOfBooks.add(
                    BookEntity(i, "Title$i", "SubTitle$i","Summary$i")
                )
            }
            database.bookDao().insertBooks(listOfBooks)
        }
    }

    private fun insertAuthors() {
        lifecycleScope.launch {
            val listOfAuthors = mutableListOf<AuthorEntity>()
            for (i in 1 .. 5) {
                listOfAuthors.add(
                    AuthorEntity(i, "Name$i")
                )
            }
            database.bookDao().insertAuthors(listOfAuthors)
        }
    }

}