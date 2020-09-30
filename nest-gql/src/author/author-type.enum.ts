import { registerEnumType } from '@nestjs/graphql';

export enum AuthorTypeEnum {
  FREE,
  SHIT,
}

registerEnumType(AuthorTypeEnum, {
  name: 'AuthorType',
});
