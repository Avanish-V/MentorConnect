package com.byteapps.mentorconnect.Utils

sealed class ResultState<out T>{


    data class Success<out R>(val data : R):ResultState<R>()

    data class Error(val message: String):ResultState<Nothing>()

    object Loading : ResultState<Nothing>()
}
