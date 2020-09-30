import { registerEnumType } from '@nestjs/graphql';

export enum AuthorTypeEnum {
  FREE,
  SHIT,
}

// 단순히 등록하기 하고
// 이거를 field type 으로 사용하면 된다
// 이놈은 위치가 조금 어정쩡하긴한데
// 가장 괜찮은곳은 enum이 정의된 파일 내에서 정의하는것이 가장 좋아보인다
registerEnumType(AuthorTypeEnum, {
  name: 'AuthorType',
  description: 'this is description',
});
