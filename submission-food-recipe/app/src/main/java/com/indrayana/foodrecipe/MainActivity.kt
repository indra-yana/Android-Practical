package com.indrayana.foodrecipe

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.indrayana.foodrecipe.adapter.IOnItemClickListener
import com.indrayana.foodrecipe.adapter.RecipeAdapter
import com.indrayana.foodrecipe.adapter.ViewHolderType
import com.indrayana.foodrecipe.data.Recipe
import com.indrayana.foodrecipe.data.RecipeData


class MainActivity : AppCompatActivity() {

    private lateinit var rvRecipe: RecyclerView
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var listData: ArrayList<Recipe> = arrayListOf()
    private val itemIdDefault: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvRecipe = findViewById(R.id.rv_recipe)
        rvRecipe.setHasFixedSize(true)

        listData.addAll(RecipeData.listData)
        setMode(itemIdDefault)
    }

    private fun setMode(itemId: Int) {
        when(itemId) {
            R.id.menu_act_cardview -> {
                buildRecyclerView(ViewHolderType.CARD)
            }
            R.id.menu_act_list -> {
                buildRecyclerView(ViewHolderType.LIST)
            }
            R.id.menu_act_grid -> {
                buildRecyclerView(ViewHolderType.GRID)
            }
            R.id.menu_act_about -> {
                openBottomSheetAbout()
            }
            itemIdDefault -> {
                buildRecyclerView(ViewHolderType.CARD)
            }
        }
    }

    private fun buildRecyclerView(viewHolderType: ViewHolderType) {
        val adapter = RecipeAdapter(listData).apply {
            holderType = viewHolderType
            iOnItemClickListener = object : IOnItemClickListener {
                override fun onItemClicked(data: Recipe) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("recipe", data)
                    startActivity(intent)
                }

                override fun onButtonFavouriteClicked(data: Recipe) {
                    super.onButtonFavouriteClicked(data)
                    Toast.makeText(this@MainActivity, "${data.title} Added to favourite!", Toast.LENGTH_SHORT).show()
                }

                override fun onButtonShareClicked(data: Recipe) {
                    super.onButtonShareClicked(data)
                    Toast.makeText(this@MainActivity, "${data.title} Shared to social media!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        rvRecipe.layoutManager = when(viewHolderType) {
            ViewHolderType.CARD -> LinearLayoutManager(this)
            ViewHolderType.LIST -> LinearLayoutManager(this)
            ViewHolderType.GRID -> GridLayoutManager(this, 2)
        }

        rvRecipe.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun openBottomSheetAbout() {
        if (bottomSheetDialog == null) {
            val view: View = LayoutInflater.from(this).inflate(R.layout.layout_about_bottom_sheet, findViewById<LinearLayout>(R.id.layout_about_container), true) //View.inflate(this, R.layout.layout_about_bottom_sheet, findViewById(R.id.layout_about_container))
            with(view) {
                (findViewById<Button>(R.id.btn_close)).setOnClickListener { bottomSheetDialog?.dismiss() }
                (findViewById<ImageView>(R.id.iv_about_photo)).setImageDrawable( getDrawable( R.drawable.profile_photo) )
                minimumHeight = Resources.getSystem().displayMetrics.heightPixels
            }

            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            bottomSheetDialog?.setContentView(view)

            val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
            bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_SETTLING
        }

        bottomSheetDialog?.show()
    }
}