---
inject: true
append: true
to: ./constant.ts
---
const GRAPHQL_OBJECT_TYPE_<%= h.storage.constantName %> = "<%= h.storage.objectTypeName %>";