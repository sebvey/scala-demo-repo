package io.sve.framework.xdf

trait ArrayXFN[T] extends XFN {
  def nav: T
  def apply(i: Int): ArrayValueXFN[T] = {

  }
}

class ArrayValueXFN[T](nav: T,$: XField) extends XFN {

  nav =>
  def $: XField = $
}