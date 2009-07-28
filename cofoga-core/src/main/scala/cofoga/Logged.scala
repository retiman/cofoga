package cofoga

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

trait Logged {
  protected val commonsLog = LogFactory.getLog(this.getClass)
  protected def log = new {
    def debug(msg: => String, t: Throwable) = if (commonsLog.isDebugEnabled) commonsLog.debug(msg, t)
    def debug(msg: => String) = if (commonsLog.isDebugEnabled) commonsLog.debug(msg)
    def error(msg: => String, t: Throwable) = if (commonsLog.isErrorEnabled) commonsLog.error(msg, t)
    def error(msg: => String) = if (commonsLog.isErrorEnabled) commonsLog.error(msg)
    def info(msg: => String, t: Throwable) = if (commonsLog.isInfoEnabled) commonsLog.info(msg, t)
    def info(msg: => String) = if (commonsLog.isInfoEnabled) commonsLog.info(msg)
    def trace(msg: => String, t: Throwable) = if (commonsLog.isTraceEnabled) commonsLog.trace(msg, t)
    def trace(msg: => String) = if (commonsLog.isTraceEnabled) commonsLog.trace(msg)
    def warn (msg: => String, t: Throwable) = if (commonsLog.isWarnEnabled)  commonsLog.warn(msg, t)
    def warn (msg: => String) = if (commonsLog.isWarnEnabled)  commonsLog.warn(msg)
  }
}