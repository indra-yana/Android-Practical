package com.indrayana.foodrecipe

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.indrayana.foodrecipe.data.Recipe

class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivThumb: ImageView
    private lateinit var tvIngredient: TextView
    private lateinit var tvStep: TextView
    private lateinit var btnShare: Button
    private lateinit var btnFavourite: Button
    private lateinit var btnBack: Button
    private lateinit var tvReadMore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvTitle = findViewById(R.id.tv_item_title)
        tvDescription = findViewById(R.id.tv_item_description)
        ivThumb = findViewById(R.id.iv_item_thumb)
        tvIngredient = findViewById(R.id.tv_item_ingredient)
        tvStep = findViewById(R.id.tv_item_step)
        btnShare = findViewById(R.id.btn_set_share)
        btnFavourite = findViewById(R.id.btn_set_favourite)
        tvReadMore = findViewById(R.id.tv_read_more)

        btnBack = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        val recipe: Recipe = intent.getParcelableExtra("recipe") as Recipe

        with(recipe) {
            tvTitle.text = title
            tvDescription.text = description
            Glide.with(this@DetailActivity)
                    .load(thumb)
                    .apply(RequestOptions().override(450, 250))
                    .into(ivThumb)

            tvIngredient.text = ingredient.joinToString(separator = "\n") {
                "• $it"
            }

            tvStep.text = step.joinToString(separator = "\n")

            btnShare.setOnClickListener {
                Toast.makeText(this@DetailActivity, "$title Added to favourite!", Toast.LENGTH_SHORT).show()
            }

            btnFavourite.setOnClickListener {
                Toast.makeText(this@DetailActivity, "$title Shared to social media!", Toast.LENGTH_SHORT).show()
            }

            // Setup read more action
            tvReadMore.visibility = View.VISIBLE
            tvReadMore.setOnClickListener {
                if (tvReadMore.text.toString() == "Read More") {
                    tvDescription.maxLines = Int.MAX_VALUE
                    tvDescription.ellipsize = null
                    tvReadMore.text = getString(R.string.text_read_less)
                } else {
                    tvDescription.maxLines = 4
                    tvDescription.ellipsize = TextUtils.TruncateAt.END
                    tvReadMore.text = getString(R.string.text_read_more)
                }
            }
        }

    }
}