package bek.droid.news.data.db.dao

import androidx.room.*
import bek.droid.news.data.model.entity.NewsEntity
import kotlinx.coroutines.flow.*

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(news: List<NewsEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(vararg news: NewsEntity): List<Long>

    @Query("SELECT * FROM news ORDER BY id DESC")
    fun getNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE (source_name LIKE :query or title LIKE :query or description LIKE :query or content LIKE :query)")
    fun search(query: String): Flow<List<NewsEntity>>

    @Query("UPDATE news SET is_saved=:isSaved WHERE id=:id")
    suspend fun bookmark(id: Int, isSaved: Boolean)

    @Query("SELECT COUNT(*) FROM news WHERE title IN (:newsTitles)")
    fun getNewsCountByTitle(newsTitles: List<String?>): Int

    @Query("SELECT * FROM news WHERE is_saved=:isSaved ORDER BY id DESC")
    fun getSavedNews(isSaved:Boolean = true): Flow<List<NewsEntity>>
}