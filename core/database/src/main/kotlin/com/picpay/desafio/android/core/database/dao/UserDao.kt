package com.picpay.desafio.android.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.picpay.desafio.android.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("Select * from user_tbl")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    fun searchUsers(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ) = searchUsers(
        createSearchQuery(
            searchQuery = searchQuery,
            sortColumn = sortColumn,
            sortOrder = sortOrder
        )
    )

    @RawQuery(observedEntities = [UserEntity::class])
    fun searchUsers(query: SupportSQLiteQuery): Flow<List<UserEntity>>

    private fun createSearchQuery(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): SupportSQLiteQuery =
        SimpleSQLiteQuery(
            query = """
        SELECT * 
        FROM user_tbl
        WHERE name LIKE '%' || ? || '%' 
        OR username LIKE '%' || ? || '%'
        ORDER BY 
        $sortColumn
        COLLATE NOCASE 
        $sortOrder
    """.trimIndent(),
            bindArgs = arrayOf(searchQuery, searchQuery)
        )
}