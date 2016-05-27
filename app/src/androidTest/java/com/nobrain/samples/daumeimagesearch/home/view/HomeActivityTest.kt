package com.nobrain.samples.daumeimagesearch.home.view

import android.content.Intent
import android.net.Uri
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4

import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenter

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Matchers.eq

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    var rule = IntentsTestRule(HomeActivity::class.java)
    private var view: HomePresenter.View? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        view = rule.activity

    }

    @Test
    @Throws(Throwable::class)
    fun testOnMoveLink() {
        val text = "http://www.coupang.com"
        rule.runOnUiThread { view!!.onMoveLink(text) }

        Intents.intending(IntentMatchers.hasAction(eq(Intent.ACTION_VIEW)))
        Intents.intending(IntentMatchers.hasData(eq(Uri.parse(text))))
    }
}