package com.knoldus.api.datamodels

import play.api.libs.json.{Format, Json}

case class GuestAddresses(
                           line1: String,
                           line2: String,
                           line3: String,
                           apartment: String,
                           addressType: String,
                           city: String,
                           stateCode: String,
                           postalCode: String,
                           countryCode: String
                         )

object GuestAddresses {
  implicit val format: Format[GuestAddresses] = Json.format[GuestAddresses]

}