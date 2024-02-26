package io.sve.framework.args

import scopt.Read
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import org.apache.hadoop.fs.Path

trait CustomScoptRead {
  Read.reads(s => LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE))

  implicit lazy val localDateTimeRead: Read[LocalDateTime] =
    Read.reads(s => LocalDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME))

  implicit lazy val offsetDateTimeRead: Read[OffsetDateTime] =
    Read.reads(s => OffsetDateTime.parse(s, DateTimeFormatter.ISO_OFFSET_DATE_TIME))

  implicit lazy val zonedDateTimeRead: Read[ZonedDateTime] =
    Read.reads(s => ZonedDateTime.parse(s, DateTimeFormatter.ISO_ZONED_DATE_TIME))

  implicit lazy val hadoopPathRead: Read[Path] = Read.reads(s => new Path(s))
}
