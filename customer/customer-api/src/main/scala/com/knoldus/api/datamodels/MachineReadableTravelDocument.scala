package com.knoldus.api.datamodels

import play.api.libs.json.{Format, Json}

case class MachineReadableTravelDocument(
                                          firstName: String,
                                          lastName: String,
                                          documentNumber: String
                                        )

object MachineReadableTravelDocument {
  implicit val format: Format[MachineReadableTravelDocument] = Json.format[MachineReadableTravelDocument]

}
