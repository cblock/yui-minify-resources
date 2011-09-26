package com.blockconsult.yuiminifyresources

import com.yahoo.platform.yui.compressor.JavaScriptCompressor
import org.grails.plugin.resource.mapper.MapperPhase

class YuiJsMinifyResourceMapper {

  def phase = MapperPhase.COMPRESSION

  static defaultExcludes = ['**/*.min.js']
  static defaultIncludes = ['**/*.js']

  def map(resource, config) {

    if (config?.disable) {
      if (log.debugEnabled) log.debug "YUI JS Minifier disabled in Config.groovy"
      return false
    }

    File inputFile = resource?.processedFile
    File targetFile = Util.getTargetFile(resource, Util.jsFilePattern)
    if (!targetFile) return false

    try {
      String encoding = config?.charset ?: 'UTF-8'
      if (log.debugEnabled) log.debug "Minifying Javascript file [$inputFile] to [$targetFile]"
      JavaScriptCompressor compressor = null
      inputFile.withReader(encoding) {
        compressor = new JavaScriptCompressor(it, new YuiCompressorErrorReporter())
      }
      if (compressor) {
        targetFile.withWriter(encoding) {
          compressor.compress(it,
              (config?.lineBreak ?: -1),
              (config?.noMunge ?: false),
              false, //non-verbose
              (config?.preserveAllSemicolons ?: false),
              (config?.disableOptimizations ?: false)
          )
        }
      }
      resource.processedFile = targetFile
      resource.updateActualUrlFromProcessedFile()
    } catch (Exception e) {
      log.error "Stopped minifying [${inputFile}]: ${e.message} Set log level to warn for more details."
      return false
    }
  }
}
