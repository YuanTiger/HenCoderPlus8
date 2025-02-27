package com.example.lesson

import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import java.util.ArrayList

class LessonPresenter(private val activity: LessonActivity) {
    private var lessons: List<Lesson?> = ArrayList()
    private val type = object : TypeToken<List<Lesson?>?>() {}.type
    fun fetchData() {
        HttpClient.INSTANCE[LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread { activity.showResult(entity) }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread { toast(message!!) }
            }
        }]
    }

    fun showPlayback() {
//        val playbackLessons: MutableList<Lesson> = ArrayList()
//        lessons.forEach {
//            if (it?.state === Lesson.State.PLAYBACK) {
//                playbackLessons.add(it)
//            }
//        }
        val playbackLessons = lessons.filter { it?.state === Lesson.State.PLAYBACK }
        activity.showResult(playbackLessons)
    }

    companion object {
        private const val LESSON_PATH = "lessons"
    }
}