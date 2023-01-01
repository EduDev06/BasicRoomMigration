package com.example.basicroommigration

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [BookEntity::class, AuthorEntity::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = MyDatabase.MigrationFrom2To3::class)
    ]
)
abstract class MyDatabase: RoomDatabase() {
    abstract fun bookDao(): MyDao

    @RenameColumn(tableName = "BookEntity", fromColumnName = "summary", toColumnName = "description")
    class MigrationFrom2To3: AutoMigrationSpec

    companion object {
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS AuthorEntity (" +
                        "id INT NOT NULL PRIMARY KEY," +
                        "name TEXT NOT NULL)")
            }
        }
    }
}