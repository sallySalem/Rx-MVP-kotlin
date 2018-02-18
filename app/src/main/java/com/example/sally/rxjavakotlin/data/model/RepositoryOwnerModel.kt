package com.example.sally.rxjavakotlin.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by Sally Salem on 2/4/18.
 */
data class RepositoryOwnerModel(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("login")
        val login: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("gravatar_id")
        val gravatarId: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("html_url")
        val htmlUrl: String?,
        @SerializedName("repos_url")
        val reposUrl: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("site_admin")
        val siteAdmin: Boolean?,
        @SerializedName("name")
        val ownerName: String?,
        @SerializedName("blog")
        val ownerBlog: String?,
        @SerializedName("email")
        val ownerEmail: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(login)
        parcel.writeString(avatarUrl)
        parcel.writeString(gravatarId)
        parcel.writeString(url)
        parcel.writeString(htmlUrl)
        parcel.writeString(reposUrl)
        parcel.writeString(type)
        parcel.writeValue(siteAdmin)
        parcel.writeString(ownerName)
        parcel.writeString(ownerBlog)
        parcel.writeString(ownerEmail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<RepositoryOwnerModel> {
            override fun createFromParcel(parcel: Parcel) = RepositoryOwnerModel(parcel)

            override fun newArray(size: Int) = arrayOfNulls<RepositoryOwnerModel>(size)
        }
    }
}