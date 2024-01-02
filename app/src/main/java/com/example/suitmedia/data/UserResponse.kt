package com.example.suitmedia.data

data class User(val id: Int, val avatar: String, val email: String, val first_name: String, val last_name: String)

data class UserResponse(val data: List<User>)
