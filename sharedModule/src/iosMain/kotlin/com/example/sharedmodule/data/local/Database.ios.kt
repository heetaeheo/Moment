import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.MomentDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


@OptIn(ExperimentalForeignApi::class)
fun getDatabaseBuilder(): RoomDatabase.Builder<MomentDatabase> {
    val url = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    ) ?: error("Documents directory not found")

    val dbPath = url.path + "/moment_database.db"
    return Room.databaseBuilder<MomentDatabase>(name = dbPath)
}