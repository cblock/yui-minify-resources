package com.blockconsult.yuiminifyresources

import com.yahoo.platform.yui.compressor.CssCompressor
import org.grails.plugin.resource.mapper.MapperPhase

class YuiCssMinifyResourceMapper {

  def phase = MapperPhase.COMPRESSION

  static defaultExcludes = ['**/*.min.css']
  static defaultIncludes = ['**/*.css']

  def map(resource, config) {

    if (config?.disable) {
      if (log.debugEnabled) log.debug "YUI CSS Minifier disabled in Config.groovy"
      return false
    }

    File inputFile = resource?.processedFile
    File targetFile = Util.getTargetFile(resource, Util.cssFilePattern)
    if (!targetFile) return false

    try {
      String encoding = config?.charset ?: 'UTF-8'
      if (log.debugEnabled) log.debug "Minifying CSS file [$inputFile] to [$targetFile]"
      CssCompressor compressor = null
      inputFile.withReader(encoding) {
        compressor = new CssCompressor(it)
      }
      if (compressor) {
        targetFile.withWriter(encoding) {
          compressor.compress(it, config?.lineBreak ?: -1)
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

