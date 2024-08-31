package com.faysal.assessment.ui.ui_states

import com.faysal.assessment.data.models.UserPosts

sealed class HomeUiStates {
    data object Empty : HomeUiStates()
    data object Loading : HomeUiStates()
    class Error(val error: String) : HomeUiStates()
    class Success(val userPosts: List<UserPosts>) : HomeUiStates()
}