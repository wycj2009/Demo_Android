package com.example.demo_android.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class MyPagingSource : PagingSource<Int, MyDataItem>() {

    // 데이터를 비동기적으로 로드하는 핵심 메소드입니다.
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyDataItem> {
        // 로드할 페이지 번호. 첫 로드 시에는 null일 수 있으므로 기본값 1로 설정합니다.
        val loadKey = params.key ?: 1

        return try {
            // API 호출 등으로 데이터를 가져옵니다.
            val items = getItems(loadKey = loadKey, loadSize = params.loadSize)

            // 이전 페이지와 다음 페이지의 키를 설정합니다.
            val prevKey = if (loadKey > 1) loadKey - 1 else null
            val nextKey = if (items.isNotEmpty()) loadKey + 1 else null

            LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    // 데이터가 새로고침되거나 무효화되었을 때 호출될 키를 정의합니다.
    override fun getRefreshKey(state: PagingState<Int, MyDataItem>): Int? {
        // 가장 최근 접근한 페이지를 기준으로 키를 반환합니다.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

private suspend fun getItems(loadKey: Int, loadSize: Int): List<MyDataItem> {
    delay(3000L)

    val dataList = buildList {
        for (i in 0 until loadSize) {
            add(
                MyDataItem(
                    data = "loadKey:${loadKey} loadSize:${i}",
                )
            )
        }
    }
    return dataList
}
