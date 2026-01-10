import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rms.db.AppDataBase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDataBase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDataBase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}