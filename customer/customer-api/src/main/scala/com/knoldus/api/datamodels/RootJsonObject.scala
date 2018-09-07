package com.knoldus.api.datamodels

import play.api.libs.json.{Format, Json}


case class RootJsonObject(
                           guests: List[Guests]
                         )

object RootJsonObject {
  implicit val format: Format[RootJsonObject] = Json.format[RootJsonObject]
}
