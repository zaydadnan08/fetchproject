package com.example.fetchproject.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.fetchproject.MainViewModel
import com.example.fetchproject.components.CardItem
import com.example.fetchproject.components.SectionHeader


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HiringListScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val hiringList by viewModel.hiringListState.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        hiringList.forEach { (listId, items) ->
            stickyHeader {
                SectionHeader(listId)
            }
            items(
                items = items,
                key = { item -> "ListId: ${item.listId} Id: ${item.id}"}
            ) { item ->
                CardItem(item)
            }
        }
    }
}