package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.core.utils.*
import com.example.core.utils.CacheUtils
import com.example.app.widget.CodeView
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.app.entity.User
import com.example.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val usernameKey = "username"
    private val passwordKey = "password"
    private var etUsername: EditText? = null
    private var etPassword: EditText? = null
    private var etCode: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etCode = findViewById(R.id.et_code)
        etUsername?.setText(CacheUtils.get(usernameKey))
        etPassword?.setText(CacheUtils.get(passwordKey))
        findViewById<Button>(R.id.btn_login).setOnClickListener(this)
        findViewById<CodeView>(R.id.code_view).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = etUsername?.text.toString()
        val password = etPassword?.text.toString()
        val code = etCode?.text.toString()
        val user = User(username, password, code)
        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean {
        if ((user.username?.length ?: 0) < 4) {
            toast("用户名不合法")
            return false
        }
        if ((user.password?.length ?: 0) < 4) {
            toast("密码不合法")
            return false
        }
        return true
    }
}