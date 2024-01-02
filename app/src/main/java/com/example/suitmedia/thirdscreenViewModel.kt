package com.example.suitmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmedia.api.apiconfig
import com.example.suitmedia.data.User
import kotlinx.coroutines.launch

class thirdscreenViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    var currentPage = 1
    var isLastPage = false
    var isLoading = false

    fun getUsers() {
        if (isLoading) return
        isLoading = true


        viewModelScope.launch {
            val response = apiconfig.apiService.getUsers(currentPage, PAGE_SIZE)
            if (response.isSuccessful) {
                val userList = response.body()?.data ?: emptyList()
                _users.value = _users.value.orEmpty() + userList

                if (userList.size < PAGE_SIZE) {
                    isLastPage = true
                } else {
                    currentPage++
                }
            }
            isLoading = false
        }
    }

    companion object {
        private const val PAGE_SIZE = 0
    }
}