package com.example.data.local

import androidx.room.ConstructedBy
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

enum class MessageType {
    USER, GEMINI
}

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val message: String,
    val timestamp: Long,
    val type: MessageType,
    val dateKey: String,
    val geminiResponseTime: Long? = null
)

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_messages")
    suspend fun selectAll(): List<ChatMessageEntity>

    @Query("SELECT * FROM chat_messages WHERE dateKey = :dateKey ORDER BY timestamp ASC")
    suspend fun selectByDate(dateKey: String): List<ChatMessageEntity>

    @Insert
    suspend fun insertMessage(entity: ChatMessageEntity)

    @Query("DELETE FROM chat_messages")
    suspend fun deleteAll()
}

@Database(entities = [ChatMessageEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class MomentDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<MomentDatabase> {
    override fun initialize(): MomentDatabase
}