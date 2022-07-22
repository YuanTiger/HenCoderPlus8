@file:JvmName("CCC")

package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

private val displayMetrics = Resources.getSystem().displayMetrics

fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}


/**
 * 即可以使用单参数，也可使用多参数
 * JvmOverloads：让Java调用时，也可以看见多个重写方法
 */
@JvmOverloads
fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.currentApplication, string, duration).show()
}
