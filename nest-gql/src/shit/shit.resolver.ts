import {
  Args,
  Int,
  Parent,
  Query,
  ResolveField,
  Resolver,
} from '@nestjs/graphql';
import { Post } from './post.model';
import { Shit } from './shit.model';

async function getShit(post) {
  const shit = new Shit();
  shit.id = 1;

  return shit;
}

@Resolver(() => Shit)
export class ShitResolver {
  @Query(returns => Shit, { name: 'getShit' })
  async getShit(@Args({ name: 'id', type: () => Int }) id: number) {
    const shit = await getShit(2);

    return shit;
  }

  @ResolveField(returns => Post, { name: 'post' })
  async post(@Parent() shit: Shit) {
    console.log('resolve post, from root shit');
    const post1 = new Post();
    post1.id = 1;
    post1.title = '@34';
    post1.votes = 3;
    post1.shit = await getShit(post1);

    return post1;
  }
}
