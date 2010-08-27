package cofoga

import org.slf4j.LoggerFactory

trait Logged {
  protected val _log = LoggerFactory.getLogger(this.getClass)
  protected def log = new {
    def debug(any: => Any, t: Throwable) = if (_log.isDebugEnabled) _log.debug(any.toString, t)
    def debug(any: => Any) = if (_log.isDebugEnabled) _log.debug(any.toString)
    def error(any: => Any, t: Throwable) = if (_log.isErrorEnabled) _log.error(any.toString, t)
    def error(any: => Any) = if (_log.isErrorEnabled) _log.error(any.toString)
    def info(any: => Any, t: Throwable) = if (_log.isInfoEnabled) _log.info(any.toString, t)
    def info(any: => Any) = if (_log.isInfoEnabled) _log.info(any.toString)
    def trace(any: => Any, t: Throwable) = if (_log.isTraceEnabled) _log.trace(any.toString, t)
    def trace(any: => Any) = if (_log.isTraceEnabled) _log.trace(any.toString)
    def warn (any: => Any, t: Throwable) = if (_log.isWarnEnabled)  _log.warn(any.toString, t)
    def warn (any: => Any) = if (_log.isWarnEnabled)  _log.warn(any.toString)
  }
}
