import { Field, Int, ObjectType } from '@nestjs/graphql';
import { Author } from '../author/author.model';
import { Shit } from '../shit/shit.model';

@ObjectType()
export class Post {
  @Field(type => Int)
  id: number;

  @Field()
  title: string;

  @Field(type => Int, { nullable: true })
  votes?: number;

  @Field(() => Author)
  author: Author;

  @Field(() => Shit)
  shit: Shit;
}
