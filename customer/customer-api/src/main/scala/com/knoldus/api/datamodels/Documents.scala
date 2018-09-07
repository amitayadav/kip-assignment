package com.knoldus.api.datamodels

import play.api.libs.json.{Format, Json}

case class Documents(
                      `type`: String,
                      number: String,
                      expiryDate: String,
                      issuingDate: String,
                      issuingCity: String,
                      countryCode: String,
                      issuingState: String,
                      firstName: String,
                      lastName: String,
                      birthdate: String
                    )


object Documents {
  implicit val format: Format[Documents] = Json.format[Documents]

}