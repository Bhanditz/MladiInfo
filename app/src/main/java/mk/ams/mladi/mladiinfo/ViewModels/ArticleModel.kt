package mk.ams.mladi.mladiinfo.ViewModels

import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.article_item.view.*
import mk.ams.mladi.mladiinfo.DataModels.ArticleInterface
import mk.ams.mladi.mladiinfo.R
import mk.ams.mladi.mladiinfo.toRelativeTime

class ArticleModel(val article: ArticleInterface) : EpoxyModelWithDivider<ArticleModel.ArticleItemVH>(article.getArticleId()) {

  override fun createNewHolder() = ArticleItemVH()

  override fun getDefaultLayout() = R.layout.article_item

  override fun bind(holder: ArticleItemVH) {
    holder.itemView.context
    holder.bind(article)
    holder.itemView.itemDivider.visibility = dividerVisible
  }

  class ArticleItemVH : EpoxyHolder() {
    lateinit var itemView: View
    override fun bindView(itemView: View) {
      this.itemView = itemView
    }

    fun bind(article: ArticleInterface) {
      itemView.tvArticleTitle.text = article.getArticleTitle()
      itemView.tvArticleDescription.setTextWithVisibility(article.getArticleDescription())
      itemView.tvArticleData.text = article.getArticlePublishDate().toRelativeTime(itemView.context)
      itemView.tvArticleSource.text = article.getArticleSiteName()
      itemView.itemDivider.setBackgroundColor(ContextCompat.getColor(itemView.context, article.getDividerColor()))
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
}
