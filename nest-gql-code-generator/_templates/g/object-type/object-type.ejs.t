---
to: ./<%= h.storage.filePrefix %>.object-type.ts
---
import { ObjectType } from '@nestjs/graphql';

@ObjectType('<%= h.storage.objectTypeName %>', {
	implements: []
})
export class <%= h.storage.className %>ObjectType {}


