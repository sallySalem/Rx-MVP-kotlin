package com.example.sally.rxjavakotlin.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Sally Salem on 2/4/18.
 */
data class RepositoryModel(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("full_name")
        val fullName: String?,
        @SerializedName("owner")
        val ownerInfo: RepositoryOwnerModel?,
        @SerializedName("private")
        val isPrivate: Boolean?,
        @SerializedName("html_url")
        val htmlUrl: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("fork")
        val isFork: Boolean?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("updated_at")
        val updatedAt: String?,
        @SerializedName("language")
        val language: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(RepositoryOwnerModel::class.java.classLoader),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeParcelable(ownerInfo, flags)
        parcel.writeValue(isPrivate)
        parcel.writeString(htmlUrl)
        parcel.writeString(description)
        parcel.writeValue(isFork)
        parcel.writeString(url)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<RepositoryModel> {
            override fun createFromParcel(parcel: Parcel) = RepositoryModel(parcel)

            override fun newArray(size: Int) = arrayOfNulls<RepositoryModel>(size)
        }
    }
}