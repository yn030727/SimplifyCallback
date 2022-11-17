package com.example.simplifycallback

import java.lang.Exception

//发起网络请求后，服务器响应的数据无法返回
//耗时逻辑都是在子线程中进行，因为sendHttpRequest()方法会在服务器还没来的及响应的时候就执行结束
//所以用了回调机制
interface HttpCallbackListener {
    fun onFinish(response:String)
    fun onError(e:Exception)
}