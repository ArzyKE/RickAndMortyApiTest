package com.example.rickandmortyapitest.base

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.rickandmortyapitest.data.network.dtos.models.ApiResponse
import java.io.IOException

abstract class BasePagingSource<ValueDto : Any, Value : Any>(
    private val service: suspend (nextPage: Int) -> ApiResponse<ValueDto>,
    private val mappedData: (data: List<ValueDto>) -> List<Value>
) : PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            val nextPage = params.key ?: 1
            val response = service(nextPage)
            val next = response.info.next
            val nextPageNumber = if (next == null) {
                null
            } else {
                Uri.parse(next).getQueryParameter("page")?.toInt()
            }
            LoadResult.Page(
                data = mappedData(response.results),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (http: HttpException) {
            LoadResult.Error(http)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey
        }
    }
}
