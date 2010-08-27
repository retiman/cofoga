package cofoga

import org.slf4j.LoggerFactory

trait Logged {
  val log = LoggerFactory.getLogger(this.getClass)
}
