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
  
  /*
   * Henceforth, "munge" should be used instead of "noMunge", but "noMunge" is kept for backwards compatibility. 
   * @deprecated
   */
  Boolean noMunge = null; // use Boolean in order to do a null test
  boolean munge = false
  boolean preserveAllSemicolons = false
  boolean disableOptimizations = false

  public String toString() {
    "[lineBreak: $lineBreak, charset: $charset, munge: $munge, noMunge: $noMunge, preserveAllSemicolons: $preserveAllSemicolons, disableOptimizations: $disableOptimizations]"
  }
}
