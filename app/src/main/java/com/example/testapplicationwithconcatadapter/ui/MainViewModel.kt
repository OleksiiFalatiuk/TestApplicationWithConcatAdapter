package com.example.testapplicationwithconcatadapter.ui

import androidx.lifecycle.ViewModel
import com.example.testapplicationwithconcatadapter.data.Data
import com.example.testapplicationwithconcatadapter.ui.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class MainViewModel: ViewModel() {

    private val _itemList = MutableStateFlow<List<Item>>(emptyList())
    val itemList: StateFlow<List<Item>> = _itemList

    init {
        _itemList.value = Data.getItems()
    }

    fun itemSelected(id: String){
        _itemList.value
            .first{ it.id == id }.let {
                Timber.d("Item selected $it")
            }
    }

}