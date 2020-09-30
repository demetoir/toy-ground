import { Field, Int, ObjectType } from '@nestjs/graphql';
import { Post } from '../post/post.model';
import { PriceScalar } from '../common/scalars/price/price.scalar';
import { AuthorTypeEnum } from './author-type.enum';

@ObjectType()
export class Author {
  @Field(type => Int)
  id: number;

  @Field({ nullable: true })
  firstName?: string;

  @Field({ nullable: true })
  lastName?: string;

  @Field({ nullable: false })
  createdAt: Date;

  @Field(type => PriceScalar, { nullable: true })
  price: number;

  @Field(type => [Post])
  posts: Post[];

  @Field(type => AuthorTypeEnum)
  type: AuthorTypeEnum;
}
