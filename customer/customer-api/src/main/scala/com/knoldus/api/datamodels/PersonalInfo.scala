package com.knoldus.api.datamodels
import play.api.libs.json.{Format, Json}

case class PersonalInfo(
                         guestTitle: String,
                         firstName: String,
                         middleName: String,
                         middleNameFlag: String,
                         lastName: String,
                         suffix: String,
                         birthdate: String,
                         gender: String,
                         maritalStatus: String,
                         localeFirstName: String,
                         localeMiddleName: String,
                         localeLastName: String,
                         loyaltyNumber: String,
                         occupation: String
                       )



object PersonalInfo {
  implicit val format: Format[PersonalInfo] = Json.format[PersonalInfo]

}
