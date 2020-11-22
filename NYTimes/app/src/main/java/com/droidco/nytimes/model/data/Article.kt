package com.droidco.nytimes.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Article(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    val articleId: Int = 0,

    val title: String,

    @ColumnInfo(name = "author_name")
    @SerializedName("byline")
    val authorName: String,

    @SerializedName("abstract")
    val description: String,

    @SerializedName("published_date")
    val publishedDate: String,

    @SerializedName("short_url")
    val url: String,

    @SerializedName("multimedia")
    val multimedia: ArrayList<Multimedia>

) {


    fun getThumbnailUrl(): String? {
        val multimediaResp = this.multimedia.find { it.format == "thumbLarge" }
        return multimediaResp?.url
    }

    fun getBackgroundImage(): String? {
        val multimediaResp = this.multimedia.find { it.format == "mediumThreeByTwo210" }
        return multimediaResp?.url
    }
}

data class Multimedia(
    val url: String,
    val format: String
)