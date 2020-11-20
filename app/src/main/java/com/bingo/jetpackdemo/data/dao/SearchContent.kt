package com.bingo.jetpackdemo.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "SearchContent")
data class SearchContent(

    @ColumnInfo(name = "content") var content: String,

    @ColumnInfo(name = "searchTime") var searchTime: Long,

    ) {
    @PrimaryKey(autoGenerate = true)
    var _id: Long = 0

}

@Dao
interface SearchContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchContent(searchContent: SearchContent)

    @Query("select * from SearchContent order by searchTime desc limit 10")
    fun getAllSearchContent(): Flow<List<SearchContent>>
}