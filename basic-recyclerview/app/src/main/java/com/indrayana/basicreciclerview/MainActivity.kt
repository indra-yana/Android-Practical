package com.indrayana.basicreciclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView
    private var list: ArrayList<Hero> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        setMode(10)
        // buildRecyclerView(ViewType.LIST)
    }

    private fun buildRecyclerView(viewType: ViewType) {
        val heroAdapter = HeroAdapter(list).apply {
            itemViewType = viewType
            onItemClickCallback = object : HeroAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Hero) {
                    Toast.makeText(this@MainActivity, "${data.name} Clicked", Toast.LENGTH_SHORT).show()
                }
            }
        }

        rvHeroes.layoutManager = when(viewType) {
            ViewType.LIST -> LinearLayoutManager(this)
            ViewType.GRID -> GridLayoutManager(this, 2)
            ViewType.CARD -> LinearLayoutManager(this)
        }

        rvHeroes.adapter = heroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when(itemId) {
            R.id.menu_act_list -> {
                supportActionBar?.title = "Mode List"
                buildRecyclerView(ViewType.LIST)
            }
            R.id.menu_act_grid -> {
                supportActionBar?.title = "Mode Grid"
                buildRecyclerView(ViewType.GRID)
            }
            R.id.menu_act_cardview -> {
                supportActionBar?.title = "Mode Card"
                buildRecyclerView(ViewType.CARD)
            }
            else -> {
                supportActionBar?.title = "Mode List"
                buildRecyclerView(ViewType.LIST)
            }
        }
    }
}