package io.github.collins993.todolistkotlin.data

import androidx.room.TypeConverter
import io.github.collins993.todolistkotlin.data.models.Priority

class Converter {

    /**
     * to convert priority to string class and back to priority class
     * */


    @TypeConverter
    fun fromPriority(priority: Priority): String{

        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {

        return Priority.valueOf(priority)
    }
}