package com.example.demo_android.paging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    val items: Flow<PagingData<MyDataItem>> = Pager(
        // PagingConfig: 페이지 로드 방법을 구성합니다.
        config = PagingConfig(
            pageSize = 10, // 각 페이지에 로드할 데이터 수
            enablePlaceholders = false, // 플레이스홀더 사용 여부
        ),
        pagingSourceFactory = { MyPagingSource() },
    )
        .flow
//        .cachedIn(viewModelScope) // Flow의 데이터를 ViewModelScope 내에서 캐시하여 화면 회전 등에도 데이터가 유지되도록 합니다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val lazyPagingItems: LazyPagingItems<MyDataItem> = items.collectAsLazyPagingItems()

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(count = lazyPagingItems.itemCount) { index ->
                    val item = lazyPagingItems[index]
                    if (item != null) {
                        Text(text = item.data)
                    } else {
                        Text(text = "Loading...")
                    }
                }
            }
        }
    }
}
