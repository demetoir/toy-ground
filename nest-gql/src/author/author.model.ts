import { Field, Int, ObjectType } from '@nestjs/graphql';
import { Post } from '../post/post.model';

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

  @Field(type => [Post])
  posts: Post[];
}
