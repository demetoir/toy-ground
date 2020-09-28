import { CustomScalar, Scalar } from '@nestjs/graphql';
import { GraphQLScalarType, Kind, ValueNode } from 'graphql';

// custom scalar 를 만들려면 GraphQLScalar 타입의 객체를 선언해야한다
// 이놈을 @Field 등에서 type 으로 명시해주어야한
export declare const Price: GraphQLScalarType;

// 이놈이 custom scalar처럼 보이지만 사실은 custom scalar 에 대한 resolver 이다
@Scalar('Price', type => Price)
export class PriceScalar implements CustomScalar<number, number> {
  description = 'price';

  parseValue(value: number): number {
    return value;
  }

  serialize(value: number): number {
    return value;
  }

  parseLiteral(ast: ValueNode): number {
    if (ast.kind === Kind.INT) {
      return parseInt(ast.value, 10);
    }

    return null;
  }
}
