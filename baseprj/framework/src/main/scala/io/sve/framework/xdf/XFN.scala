package io.sve.framework.xdf

import org.apache.hadoop.shaded.org.apache.avro.generic.GenericData.StringType

trait XFN[T] {
  def $: XField[T]
}
