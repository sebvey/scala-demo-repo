package io.sve.framework.xdf

// instances that hold everything, case object representing the struct + name, nullable ....
// it's used as attributes of :
// - XEnumField, trait extended by case classes representing enums
// - XArrayField (to come) trait extended by case classes representing arrays

case class XField[T](
  name: String,
  eStruct: T,
  nullable: Boolean = true
)
