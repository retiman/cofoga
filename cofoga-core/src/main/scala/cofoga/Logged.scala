package cofoga

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

trait Logged {
  protected val _log = LogFactory.getLog(this.getClass)
  protected def log = new {
    def debug(msg: => String, t: Throwable) = if (_log.isDebugEnabled) _log.debug(msg, t)
    def debug(msg: => String) = if (_log.isDebugEnabled) _log.debug(msg)
    def error(msg: => String, t: Throwable) = if (_log.isErrorEnabled) _log.error(msg, t)
    def error(msg: => String) = if (_log.isErrorEnabled) _log.error(msg)
    def info(msg: => String, t: Throwable) = if (_log.isInfoEnabled) _log.info(msg, t)
    def info(msg: => String) = if (_log.isInfoEnabled) _log.info(msg)
    def trace(msg: => String, t: Throwable) = if (_log.isTraceEnabled) _log.trace(msg, t)
    def trace(msg: => String) = if (_log.isTraceEnabled) _log.trace(msg)
    def warn (msg: => String, t: Throwable) = if (_log.isWarnEnabled)  _log.warn(msg, t)
    def warn (msg: => String) = if (_log.isWarnEnabled)  _log.warn(msg)
  }
}