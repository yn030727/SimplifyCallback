package com.example.simplifycallback

import java.lang.Exception
import java.net.HttpURLConnection

object HttpUtil {
    fun sendHttpRequset(address:String,listener: HttpCallbackListener){
        var connection:HttpURLConnection?=null
        try {
            //这里是最常见的发送网络请求的逻辑代码
            listener.onFinish(response.toString())
        }catch (e:Exception){
            e.printStackTrace()
            listener.onError(e)
        }
        finally {
            //关闭连接
            connection?.disconnect()
        }
    }
}