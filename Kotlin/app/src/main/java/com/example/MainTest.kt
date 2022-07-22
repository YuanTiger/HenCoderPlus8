package com.example

import android.widget.EditText
import android.widget.TextView
import com.example.app.entity.User

/**
 * @author mengyuan
 * @date 2022/7/22/10:12 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
fun main() {
    val user = User()
    val user1 = user.copy()
    println(user1 == user)
    println(user1 === user)

    repeat(100) {
        print(it)
    }
    println("")

    for (i in 0 until 100) {
        print(i)
    }


    val testView = TestView()
//    testView.setOnClickListener(::onClick)
    testView.setOnClickListener {
        print("被点击了2")
    }

}


/**
 * Java中函数的参数，不能传递方法，但Kotlin可以；
 * 本质上是一致的：如果Java想传递一个函数，我们必须将这个函数封装成一个对象，然后参数传递这个对象，就能实现方法的传递。
 * Kotlin则直接支持传递函数的语法糖
 */
class TestView {
    interface OnClickListener {
        fun onClick(view: TestView)
    }

    fun setOnClickListener(listener: (view: TestView) -> Unit) {
    }
}


fun onClick(view: TestView) {
    print("被点击了1")
}