package com.droidco.nytimes.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Article(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    val articleId: Int,

    val title: String,

    @ColumnInfo(name = "author_name")
    @SerializedName("byline")
    val authorName: String,

    @SerializedName("abstract")
    val description: String,

    @SerializedName("published_date")
    val publishedDAte: String,

    @SerializedName("short_url")
    val url: String,

    @SerializedName("multimedia")
    @Ignore
    val multimedia: ArrayList<Multimedia>

) {

    var thumbnail: String? = ""
    var backgroundImage: String? = ""

    init {
        backgroundImage = multimedia.find { it.format === "mediumThreeByTwo210" }?.url
        thumbnail = multimedia.find { it.format === "thumbLarge" }?.url
    }
}

data class Multimedia(
    val url: String,
    val format: String
)