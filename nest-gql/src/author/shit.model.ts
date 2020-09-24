import { Field, Int, ObjectType } from '@nestjs/graphql';
import {Post} from "./post.model";

@ObjectType()
export class Shit {
  @Field(type => Int)
  id: number;

  @Field(() => Post)
  post: Post;
}
