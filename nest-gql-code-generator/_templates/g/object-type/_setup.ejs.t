---
sh: "touch ./constant.ts"
---
<%
const filePrefix = name
h.storage.filePrefix = filePrefix

const normalized = name.replace('-', '_')
h.storage.normalized = normalized

const className = h.inflection.camelize(normalized)
h.storage.className = className

const objectTypeName = h.inflection.camelize(normalized)
h.storage.objectTypeName = objectTypeName

const constantName = normalized.toUpperCase()
h.storage.constantName = constantName


%>