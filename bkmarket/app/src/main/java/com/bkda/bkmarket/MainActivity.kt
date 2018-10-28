package com.bkda.bkmarket

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var mMainLayout: DrawerLayout
    private lateinit var mToolbar: Toolbar
    private lateinit var mDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainLayout = findViewById(R.id.drawer_layout)

        mToolbar = findViewById(R.id.app_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //Setup drawer toggle
        mDrawerToggle = android.support.v7.app.ActionBarDrawerToggle(this, mMainLayout, R.string.app_name, R.string.app_name)
        mDrawerToggle.syncState()

        mMainLayout.addDrawerListener(mDrawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        mDrawerToggle.syncState()
    }
}
