package sclera.ux.editor

object UXEditorScalaKeywords {
  var keywords = Array("class", "override", "new", "def", "extends", "import", "val", "var")
  var keywordRegexp = keywords.reduceLeft(_+"|"+_).r
}