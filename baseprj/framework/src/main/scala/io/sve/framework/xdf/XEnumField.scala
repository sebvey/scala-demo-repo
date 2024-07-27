package io.sve.framework.xdf

trait XEnumField[T] {
  def __xField: XField[T]
}

object XEnumField {

}

  // TODO - '__xField' name to reconsider
  // it will be in the same namespace as the case class representing the struct
  // => collision possible