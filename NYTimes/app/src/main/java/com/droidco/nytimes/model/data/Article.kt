package com.droidco.nytimes.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["article_id", "section"])
data class Article(

    @ColumnInfo(name = "article_id")
    @SerializedName("short_url")
    val url: String,

    val title: String,

    @ColumnInfo(name = "author_name")
    @SerializedName("byline")
    val authorName: String,

    @SerializedName("abstract")
    val description: String,

    @SerializedName("section")
    var section: String,

    @SerializedName("published_date")
    val publishedDate: String,

    @SerializedName("multimedia")
    val multimedia: ArrayList<Multimedia>?

) {

    fun getThumbnailUrl(): String? {
        val multimediaResp = this.multimedia?.find { it.format == "thumbLarge" }
        return multimediaResp?.url
    }

    fun getBackgroundImage(): String? {
        val multimediaResp = this.multimedia?.find { it.format == "mediumThreeByTwo210" }
        return multimediaResp?.url
    }

    fun getArticleId(): String {
        return this.url
    }
}

data class Multimedia(
    val url: String,
    val format: String
)