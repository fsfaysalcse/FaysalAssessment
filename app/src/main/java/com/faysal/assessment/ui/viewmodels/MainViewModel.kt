package com.faysal.assessment.ui.viewmodels

import android.net.ConnectivityManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faysal.assessment.common.Resource
import com.faysal.assessment.common.isNetworkAvailable
import com.faysal.assessment.data.models.UserPosts
import com.faysal.assessment.domain.repository.UserRepository
import com.faysal.assessment.ui.ui_states.HomeUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository,
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private val _homeUiStates = mutableStateOf<HomeUiStates>(HomeUiStates.Empty)
    val homeUiStates: State<HomeUiStates> = _homeUiStates

    fun getUserPosts() {
        _homeUiStates.value = HomeUiStates.Empty
        if (!connectivityManager.isNetworkAvailable()) {
            _homeUiStates.value = HomeUiStates.Error("No Internet")
            return
        }
        _homeUiStates.value = HomeUiStates.Loading
        viewModelScope.launch {
            try {
                val deferredUsers = async { repository.getUsers() }
                val deferredPosts = async { repository.getUserPosts() }

                val users = deferredUsers.await()
                val posts = deferredPosts.await()

                if (users is Resource.Success && posts is Resource.Success) {
                    val userPosts = users.data?.map { user ->
                        val userSpecificPosts =
                            posts.data?.filter { post -> post.userId == user.userId }
                        UserPosts(user = user, posts = userSpecificPosts ?: emptyList())
                    } ?: emptyList()
                    _homeUiStates.value = HomeUiStates.Success(userPosts)
                } else {
                    _homeUiStates.value = HomeUiStates.Error("An error occurred")
                }

            } catch (e: Exception) {
                _homeUiStates.value = HomeUiStates.Error(e.message ?: "An error occurred")
            }
        }
    }

}