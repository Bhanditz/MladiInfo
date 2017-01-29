package mk.ams.mladi.mladiinfo.ViewModels

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.article_item.view.*
import mk.ams.mladi.mladiinfo.DataModels.ArticleInterface
import mk.ams.mladi.mladiinfo.R
import mk.ams.mladi.mladiinfo.toRelativeTime
import java.util.*

class ArticleModel(val title: String, val description: String, val date: Date, val source: String, val dividerColor: Int)
  : EpoxyModelWithHolder<ArticleModel.ArticleItemVH>() {
  var id = hashCode().toLong()

  constructor(dataModel: ArticleInterface, @ColorRes dividerColor: Int = R.color.secondary_text) :
      this(dataModel.getArticleTitle().trim(), dataModel.getArticleDescription().trim(),
          dataModel.getArticlePublishDate(), dataModel.getArticleSiteName().trim(), dividerColor) {
    id(dataModel.getArticleId().toLong())
  }

  override fun createNewHolder() = ArticleItemVH()

  override fun getDefaultLayout() = R.layout.article_item

  override fun bind(holder: ArticleItemVH) {
    holder.itemView.context
    holder.itemView.tvArticleTitle.text = title
    holder.itemView.tvArticleDescription.setTextWithVisibility(description)
    holder.itemView.tvArticleData.text = date.toRelativeTime(holder.itemView.context)
    holder.itemView.tvArticleSource.text = source
    holder.itemView.itemDivider.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, dividerColor))
  }

  class ArticleItemVH : EpoxyHolder() {
    lateinit var itemView: View
    override fun bindView(itemView: View) {
      this.itemView = itemView
    }
  }

  override fun id(): Long {
    return id
  }

  override fun id(id: Long): EpoxyModel<ArticleItemVH> {
    this.id = id
    return this
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false
    if (!super.equals(other)) return false

    other as ArticleModel

    if (title != other.title) return false
    if (date != other.date) return false
    if (id != other.id) return false

    return true
  }

  override fun hashCode(): Int {
    var result = super.hashCode()
    result = 31 * result + title.hashCode()
    result = 31 * result + date.hashCode()
    result = 31 * result + id.hashCode()
    return result
  }

  fun TextView.setTextWithVisibility(content: CharSequence) {
    if (content.isNotEmpty()) {
      text = content
      visibility = View.VISIBLE
    } else {
      visibility = View.GONE
    }
  }
}
