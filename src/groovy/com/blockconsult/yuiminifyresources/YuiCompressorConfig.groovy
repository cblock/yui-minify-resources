package com.blockconsult.yuiminifyresources

/**
 * Bean that holds YUI compressor config options
 *
 * @author Carsten Block
 * @version 1.0, Date: 21.07.11
 */
class YuiCompressorConfig {
  int lineBreak = -1
  String charset = 'UTF-8'
  boolean noMunge = false
  boolean preserveAllSemicolons = false
  boolean disableOptimizations = false

  public String toString() {
    "[lineBreak: $lineBreak, charset: $charset, noMunge: $noMunge, preserveAllSemicolons: $preserveAllSemicolons, disableOptimizations: $disableOptimizations]"
  }
}
