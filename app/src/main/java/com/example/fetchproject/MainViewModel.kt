package com.example.fetchproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproject.model.ListItem
import com.example.fetchproject.networking.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: Api
) : ViewModel() {

    val hiringListState = MutableStateFlow<Map<Int, List<ListItem>>>(emptyMap())

    private suspend fun getHiringList(): Map<Int, List<ListItem>> {
        val hiringList = api.getHiringList()
        val filteredList = hiringList.filter { !it.name.isNullOrBlank() }
        val sortedList = filteredList.sortedWith(
            compareBy<ListItem> { it.listId }
                .thenBy { nameToInt(it.name) }
        )
        return sortedList.groupBy { it.listId }
    }

    private fun nameToInt(name: String?): Int {
        return name?.split(" ")?.getOrNull(1)?.toIntOrNull() ?: Int.MAX_VALUE
    }

    init {
        viewModelScope.launch {
            hiringListState.emit(getHiringList())
        }
    }
}