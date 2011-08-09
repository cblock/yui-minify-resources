package com.blockconsult.yuiminifyresources

import org.apache.commons.logging.LogFactory
import org.mozilla.javascript.ErrorReporter
import org.mozilla.javascript.EvaluatorException

/**
 * TODO: Add Description
 *
 * @author Carsten Block
 * @version 1.0 , Date: 20.07.11
 */
class YuiCompressorErrorReporter implements ErrorReporter {

  private static final log = LogFactory.getLog(this)

  public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
    if (line < 0 || !lineSource) {
      log.warn "${message}"
    } else {
      log.warn("$message at ${lineSource} [line ${line}:${lineOffset}]")
    }
  }

  public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
    if (line < 0 || !lineSource) {
      log.warn "${message}"
    } else {
      log.warn("$message at ${lineSource} [line ${line}:${lineOffset}]")
    }
  }

  public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
    error(message, sourceName, line, lineSource, lineOffset);
    return new EvaluatorException(message);
  }

}
