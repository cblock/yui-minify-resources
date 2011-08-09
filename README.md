This plugin integrates with Grails' resources framework to automatically
compress javascript and css files using the YUI Compressor tool
(http://developer.yahoo.com/yui/compressor/).

By default, in this alpha, all files ending with .css and .js will be
compressed all other files including files ending with .min.js or .min.css are
ignored.

You can suppress compression of specific resources with nominify:true
parameter - see the resources plugin (http://www.grails.org/plugin/resources)
for details of how to declare resources.