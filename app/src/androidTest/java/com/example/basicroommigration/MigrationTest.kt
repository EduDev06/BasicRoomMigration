package com.example.basicroommigration

import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    private val TEST_DB = "migration_test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        MyDatabase::class.java,
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        var db = helper.createDatabase(TEST_DB, 1).apply {
            execSQL("INSERT INTO BookEntity VALUES(1, 'Title1', 'Summary1')")
            close()
        }
        db = helper.runMigrationsAndValidate(TEST_DB, 2, true)
        db.query("SELECT * FROM BookEntity").use {
            Truth.assertThat(it.count).isEqualTo(1)
            it.moveToFirst()
            Truth.assertThat(it.getColumnName(3)).isEqualTo("sub_title")
        }
    }
}