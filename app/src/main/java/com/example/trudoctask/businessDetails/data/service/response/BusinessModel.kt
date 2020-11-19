package com.example.trudoctask.businessDetails.data.service.response


import com.google.gson.annotations.SerializedName

data class BusinessModel(
    @SerializedName("alias")
    var alias: String?,
    @SerializedName("categories")
    var categories: List<Category?>?,

    var categoriesString: String?,
    @SerializedName("coordinates")
    var coordinates: Coordinates?,
    @SerializedName("display_phone")
    var displayPhone: String?,
    @SerializedName("hours")
    var hours: List<Hour?>?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("image_url")
    var imageUrl: String?,
    @SerializedName("is_claimed")
    var isClaimed: Boolean?,
    @SerializedName("is_closed")
    var isClosed: Boolean?,
    @SerializedName("location")
    var location: Location?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("photos")
    var photos: List<String?>?,
    @SerializedName("price")
    var price: String?,
    @SerializedName("rating")
    var rating: Float?,
    @SerializedName("review_count")
    var reviewCount: Int?,
    @SerializedName("transactions")
    var transactions: List<String?>?,
    @SerializedName("url")
    var url: String?
) {
    fun categoriesText(): String {
        val sb = StringBuilder()
        categories?.forEachIndexed { index, category ->
            sb.append(category?.title)
            if (categories!!.size - 1 != index)
                sb.append(",")
        }
        return sb.toString()
    }

    val firstImage : String?
        get() = photos?.firstOrNull()

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

    data class Hour(
        @SerializedName("hours_type")
        var hoursType: String?,
        @SerializedName("is_open_now")
        var isOpenNow: Boolean?,
        @SerializedName("open")
        var `open`: List<Open?>?
    ) {
        data class Open(
            @SerializedName("day")
            var day: Int?,
            @SerializedName("end")
            var end: String?,
            @SerializedName("is_overnight")
            var isOvernight: Boolean?,
            @SerializedName("start")
            var start: String?
        )
    }

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
        @SerializedName("cross_streets")
        var crossStreets: String?,
        @SerializedName("display_address")
        var displayAddress: List<String?>?,
        @SerializedName("state")
        var state: String?,
        @SerializedName("zip_code")
        var zipCode: String?
    ){
        val displayedLocation : String?
            get() = displayAddress?.joinToString(", ")
    }
}