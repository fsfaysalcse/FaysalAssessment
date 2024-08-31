package com.faysal.assessment.data.repository

import com.faysal.assessment.common.Resource
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

fun Response<ResponseBody>.parseApiResponse() : Resource<JSONObject> {
    return try {
        val response = this
        if (response.isSuccessful && response.body() != null) {
            val rawResponse = response.body()!!.string()
            val successObject = JSONObject(rawResponse).apply {
                put("status_code", response.code())
            }
            Resource.Success(successObject)
        } else {
            val errorBody = response.errorBody()!!.string()
            val errorObject = JSONObject(errorBody).apply {
                put("status_code", response.code())
            }
            Resource.Success(errorObject)
        }
    } catch (e: Exception) {
        Resource.Error("Error ${e.message}", null)
    }
}