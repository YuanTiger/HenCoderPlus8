package com.example.lesson

import androidx.appcompat.app.AppCompatActivity
import com.example.core.BaseView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import android.widget.LinearLayout
import android.widget.Toolbar
import com.example.core.utils.CacheUtils
import com.example.lesson.entity.Lesson
import kotlin.reflect.KProperty

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>,
    Toolbar.OnMenuItemClickListener {
    override val presenter by lazy {
        LessonPresenter(this)
    }

    var token: String by Saver("token")
    var token2: String by Saver("token2")


    class Saver(var key: String) {
        operator fun getValue(lessonActivity: LessonActivity, property: KProperty<*>): String {
            return CacheUtils[key]!!
        }

        operator fun setValue(lessonActivity: LessonActivity, property: KProperty<*>, s: String) {
            CacheUtils.save(key, s)
        }
    }

    private val lessonAdapter = LessonAdapter()
    private lateinit var refreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }

        findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).run {
            refreshLayout = this
            refreshLayout.setOnRefreshListener { presenter.fetchData() }
            refreshLayout.isRefreshing = true
        }

        presenter.fetchData()
    }

    internal fun showResult(lessons: List<Lesson?>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        presenter.showPlayback()
        return false
    }
}


