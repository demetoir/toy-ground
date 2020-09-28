import {
  Args,
  Int,
  Parent,
  Query,
  ResolveField,
  Resolver,
} from '@nestjs/graphql';
import { Shit } from './shit.model';
import { Post } from '../post/post.model';
import { PostFactory } from '../post/post.factory';
import { ShitFactory } from './shit.factory';

@Resolver(() => Shit)
export class ShitResolver {
  @Query(returns => Shit, { name: 'getShit' })
  async getShit(@Args({ name: 'id', type: () => Int }) id: number) {
    return ShitFactory.build();
  }

  @ResolveField(returns => Post, { name: 'post' })
  async post(@Parent() shit: Shit) {
    console.log('resolve post, from root shit');

    const post = PostFactory.build();
    return post;
  }
}
