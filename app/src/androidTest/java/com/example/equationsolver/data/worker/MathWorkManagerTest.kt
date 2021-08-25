package com.example.equationsolver.data.worker

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.WorkManager
import com.example.equationsolver.data.engine.Operator
import com.google.ar.core.Config
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject




@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MathWorkManagerTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var foo: WorkManager

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun Test(){
       assert(true)
    }
}