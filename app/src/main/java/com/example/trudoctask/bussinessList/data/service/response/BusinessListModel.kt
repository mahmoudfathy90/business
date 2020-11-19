package com.example.trudoctask.bussinessList.data.service.response


import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.google.gson.annotations.SerializedName

data class BusinessListModel(
    @SerializedName("businesses")
    var businesses: List<BusinessModel?>?,
    @SerializedName("region")
    var region: Region?,
    @SerializedName("total")
    var total: Int?
)

{
    data class Business(
        @SerializedName("alias")
        var alias: String?,
        @SerializedName("categories")
        var categories: List<Category?>?,
        @SerializedName("coordinates")
        var coordinates: Coordinates?,
        @SerializedName("display_phone")
        var displayPhone: String?,
        @SerializedName("distance")
        var distance: Double?,
        @SerializedName("id")
        var id: String?,
        @SerializedName("image_url")
        var imageUrl: String?,
        @SerializedName("is_closed")
        var isClosed: Boolean?,
        @SerializedName("location")
        var location: Location?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("phone")
        var phone: String?,
        @SerializedName("price")
        var price: String?,
        @SerializedName("rating")
        var rating: Double?,
        @SerializedName("review_count")
        var reviewCount: Int?,
        @SerializedName("transactions")
        var transactions: List<String?>?,
        @SerializedName("url")
        var url: String?
    ) {
        data class Category(
            @SerializedName("alias")
            var alias: String?,
            @SerializedName("title")
            var title: String?
        )

        data class Coordinates(
            @SerializedName("latitude")
            var latitude: Double?,
            @SerializedName("longitude")
            var longitude: Double?
        )

        data class Location(
            @SerializedName("address1")
            var address1: String?,
            @SerializedName("address2")
            var address2: String?,
            @SerializedName("address3")
            var address3: String?,
            @SerializedName("city")
            var city: String?,
            @SerializedName("country")
            var country: String?,
            @SerializedName("display_address")
            var displayAddress: List<String?>?,
            @SerializedName("state")
            var state: String?,
            @SerializedName("zip_code")
            var zipCode: String?
        )
    }

    data class Region(
        @SerializedName("center")
        var center: Center?
    ) {
        data class Center(
            @SerializedName("latitude")
            var latitude: Double?,
            @SerializedName("longitude")
            var longitude: Double?
        )
    }
}