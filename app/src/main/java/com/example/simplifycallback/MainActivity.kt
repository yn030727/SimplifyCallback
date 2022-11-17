package com.example.simplifycallback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var address:String
        HttpUtil.sendHttpRequset(address,object :HttpCallbackListener{
            override fun onFinish(response: String) {
                //得到服务器返回的具体内容
            }

            override fun onError(e: Exception) {
                //在这里对异常情况进行处理
            }

        } )
    }

    //request是一个挂起函数，并且接受一个address参数。
    suspend fun request(address:String ):String{
        //调用了suspendCoroutine函数，当前协程立刻被挂起
        //Lambda表达式中的内容会在普通线程中执行，然后我们创建HttpUtil发送网络请求，然后通过传统回调方式监听请求结果
        return suspendCoroutine { continuation ->
            HttpUtil.sendHttpRequset(address,object : HttpCallbackListener{
                override fun onFinish(response: String) {
                    //恢复挂起的协程
                    continuation.resume(response)
                }

                override fun onError(e: Exception) {
                    continuation.resumeWithException(e)
                }

            })
        }
    }
    //比如说获取百度
    suspend fun getBaiduResponse(){
        try{
            val response=request("https://www.baidu.com/")
        }catch (e:Exception){

        }
    }
}