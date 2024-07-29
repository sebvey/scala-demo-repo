package io.sve.framework.xdf

trait ArrayXFN[T] extends XFN[T] {
  def apply(i: Int): EnumXFN[T]
}
