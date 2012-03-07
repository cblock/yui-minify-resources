class YuiMinifyResourcesGrailsPlugin {
  // the plugin version
  def version = "0.1.5"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "1.2 > *"
  // the other plugins this plugin depends on
  def dependsOn = [resources: '1.0 > *']
  def loadAfter = ['resources']
  def pluginExcludes = [
      "grails-app/views/error.gsp",
      "web-app/css/**/*.*",
      "web-app/js/**/*.*"
  ]

  def title = 'YUI Minify Resources'
  def description = 'Minifies static css and js resources using the YUI Compressor library'
  def documentation = 'http://grails.org/plugin/yui-minify-resources'

  def author = 'Carsten Block'
  def authorEmail = 'info@block-consult.com'
  def license = '''
The plugin is licensed under Apache 2 license, see http://www.apache.org/licenses/LICENSE-2.0
The YUI Compressor library that is used to compress the resources is licensed under BSD license, see http://developer.yahoo.com/yui/license.html
'''

  def organization = [name: "Block Consult", url: "http://www.block-consult.com"]
  def scm = [url: "https://github.com/cblock/yui-minify-resources"]

  def doWithWebDescriptor = {}
  def doWithSpring = {}
  def doWithDynamicMethods = {}
  def doWithApplicationContext = {}
  def onChange = {}
  def onConfigChange = {}
}
