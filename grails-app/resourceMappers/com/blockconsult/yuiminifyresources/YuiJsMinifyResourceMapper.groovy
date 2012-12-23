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
      /* Henceforth, "munge" should be used instead of "noMunge", but "noMunge" is kept for backwards compatibility.
       * Also, "noMunge" has its boolean logic backwards, in order to support developers who were relying
       * on the buggy behavior. Therefore their code won't suddenly start un-munging when they upgrade this plugin.
       * @see https://github.com/cblock/yui-minify-resources/issues/4
       */
      boolean munge = config?.munge ?: (config?.noMunge?.equals(Boolean.TRUE) ?: false);
      if (config.noMunge != null) { // user defined 'noMunge'
          log.warn("[DEPRECATED] The config param 'noMunge' has been deprecated " + 
              "and is highly unrecommended! Please use grails.resources.mappers.yuijsminify.munge " + 
              "in your Config.groovy instead.")
      }
      if (compressor) {
        targetFile.withWriter(encoding) {
          compressor.compress(it,
              (config?.lineBreak ?: -1),
              munge,
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
