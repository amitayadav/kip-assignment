package com.knoldus.api.datamodels

import play.api.libs.json.{Format, Json}

case class GuestInformation(
                             guestReservationId: GuestReservationId,
                             personalInfo: PersonalInfo,
                             contactInfo: ContactInfo,
                             travelPhoto: TravelPhoto,
                             emergencyContactInfo: EmergencyContactInfo,
                             insuranceInfo: InsuranceInfo,
                             immigrationInfo: ImmigrationInfo,
                             guestAddresses: List[GuestAddresses],
                             cruiseSurvey: CruiseSurvey
                           )


object GuestInformation {
  implicit val format: Format[GuestInformation] = Json.format[GuestInformation]
}
