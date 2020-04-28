package com.example.pixabayapi

import android.annotation.SuppressLint
import android.app.Dialog
import android.media.Image
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.squareup.picasso.Picasso
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_item.view.*

class MainActivity : AppCompatActivity() {

    private val apiManager by lazy { ApiManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }


    @SuppressLint("InflateParams")
    private fun itemClicked(image: Image) {

        val layoutParams = WindowManager.LayoutParams()
        val dialog = Dialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_item, null)

        Picasso.get()
            .load(image.largeImageURL)
            .fit()
            .centerCrop()
            .into(view.image_view)
        dialog.setContentView(view)
        dialog.window!!.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.show()


    }


    fun init() {
        val searchView = search_field as SearchView
        searchView.isFocusable = false
        searchView.isIconified = false
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                apiManager.loadImages(
                    onSuccess = {
                        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
                        recycler_view.adapter =
                            ItemAdapter(it.hits, { image: Image -> itemClicked(image) })
                    },
                    onError = {
                    },
                    keyword = newText!!
                )
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
        })
        search_field.setOnSearchClickListener {

        }
    }
}