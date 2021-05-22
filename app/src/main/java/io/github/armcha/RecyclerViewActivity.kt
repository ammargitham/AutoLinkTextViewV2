package io.github.armcha

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.armcha.autolink.*
import io.github.armcha.databinding.ActivityRecyclerViewBinding
import io.github.armcha.databinding.RecyclerItemBinding


class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_recycler_view)

        binding.recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun getItemCount() = 200

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

                val autoLinkTextView = RecyclerItemBinding.bind(holder.itemView).autoLinkTextView
                val context = holder.itemView.context
                val custom = MODE_CUSTOM("\\sAndroid\\b")

                autoLinkTextView.addAutoLinkMode(
                    MODE_HASHTAG,
                    MODE_URL,
                    MODE_PHONE,
                    MODE_EMAIL,
                    custom,
                    MODE_MENTION
                )

                autoLinkTextView.addUrlTransformations(
                    "https://google.com" to "Google",
                    "https://en.wikipedia.org/wiki/Cyberpunk_2077" to "Cyberpunk",
                    "https://en.wikipedia.org/wiki/Fire_OS" to "FIRE",
                    "https://en.wikipedia.org/wiki/Wear_OS" to "Wear OS"
                )

                autoLinkTextView.addSpan(MODE_URL, StyleSpan(Typeface.BOLD_ITALIC), UnderlineSpan())
                autoLinkTextView.addSpan(custom, StyleSpan(Typeface.BOLD))
                autoLinkTextView.addSpan(MODE_HASHTAG, BackgroundColorSpan(Color.GRAY), UnderlineSpan(), ForegroundColorSpan(Color.WHITE))

                autoLinkTextView.hashTagModeColor = ContextCompat.getColor(context, R.color.color2)
                autoLinkTextView.customModeColor = ContextCompat.getColor(context, R.color.color1)
                autoLinkTextView.mentionModeColor = ContextCompat.getColor(context, R.color.color3)
                autoLinkTextView.emailModeColor = ContextCompat.getColor(context, R.color.colorPrimary)
                autoLinkTextView.phoneModeColor = ContextCompat.getColor(context, R.color.colorAccent)

                val text = when {
                    position % 3 == 1 -> R.string.android_text_short
                    position % 3 == 2 -> R.string.android_text_short_second
                    else -> R.string.text_third
                }

                autoLinkTextView.text = getString(text)

                autoLinkTextView.onAutoLinkClick(object : AutoLinkTextView.OnAutoLinkClick {
                    override fun onClick(item: AutoLinkItem) {
                        val message = if (item.originalText == item.transformedText) item.originalText
                        else "Original text - ${item.originalText} \n\nTransformed text - ${item.transformedText}"
                        val url = if (item.mode is MODE_URL) item.originalText else null
                        showDialog(item.mode.modeName, message, url)
                    }
                })
            }
        }
    }
}
