import { Field, InputType, Int } from '@nestjs/graphql';

@InputType()
export class GetPostByInput {
  @Field(returns => String, { name: 'title' })
  title: string;

  @Field(type => Int, { nullable: true, name: 'votes' })
  votes?: number;
}
