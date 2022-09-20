package com.example.example

import com.google.gson.annotations.SerializedName


data class Result (
  @SerializedName("first_name"          ) var firstName          : String? = null,
  @SerializedName("last_name"           ) var lastName           : String? = null,
  @SerializedName("email"               ) var email              : String? = null,
  @SerializedName("email_verified"      ) var emailVerified      : String? = null,
  @SerializedName("phone_number"        ) var phoneNumber        : String? = null,
  @SerializedName("phone_verified"      ) var phoneVerified      : String? = null,
  @SerializedName("password"            ) var password           : String? = null,
  @SerializedName("authorization"       ) var authorization      : String? = null,
  @SerializedName("apple_token"         ) var appleToken         : String? = null,
  @SerializedName("google_id"           ) var googleId           : String? = null,
  @SerializedName("apple_id"            ) var appleId            : String? = null,
  @SerializedName("facebook_id"         ) var facebookId         : String? = null,
  @SerializedName("login_type"          ) var loginType          : String? = null,
  @SerializedName("image"               ) var image              : String? = null,
  @SerializedName("country_code"        ) var countryCode        : String? = null,
  @SerializedName("country"             ) var country            : String? = null,
  @SerializedName("currency"            ) var currency           : String? = null,
  @SerializedName("account_type"        ) var accountType        : Int?    = null,
  @SerializedName("device_token"        ) var deviceToken        : String? = null,
  @SerializedName("device_type"         ) var deviceType         : String? = null,
  @SerializedName("lat"                 ) var lat                : String? = null,
  @SerializedName("lng"                 ) var lng                : String? = null,
  @SerializedName("wallet_amount"       ) var walletAmount       : Int?    = null,
  @SerializedName("notification_status" ) var notificationStatus : Int?    = null,
  @SerializedName("deleted"             ) var deleted            : Int?    = null,
  @SerializedName("status"              ) var status             : Int?    = null,
  @SerializedName("updated"             ) var updated            : String? = null,
  @SerializedName("created"             ) var created            : String? = null,
  @SerializedName("hide_from_all"       ) var hideFromAll        : String? = null,
  @SerializedName("suspendedBy"         ) var suspendedBy        : String? = null,
  @SerializedName("_id"                 ) var Id                 : String? = null,
  @SerializedName("createdAt"           ) var createdAt          : String? = null,
  @SerializedName("updatedAt"           ) var updatedAt          : String? = null,
  @SerializedName("__v"                 ) var _v                 : Int?    = null

)