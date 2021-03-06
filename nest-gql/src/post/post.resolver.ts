import {
  Args,
  Int,
  Mutation,
  Parent,
  Query,
  ResolveField,
  Resolver,
} from '@nestjs/graphql';
import { Post } from './post.model';
import { Author } from '../author/author.model';
import { PostFactory } from './post.factory';
import { Shit } from '../shit/shit.model';
import { ShitFactory } from '../shit/shit.factory';
import { AuthorFactory } from '../author/author.factory';
import { GetPostByInput } from './getPostBy.input';

@Resolver(of => Post)
export class PostResolver {
  @Mutation(returns => Post, { name: 'upvotePost' })
  async upvotePost(@Args({ name: 'postId', type: () => Int }) postId: number) {
    const post = PostFactory.build();

    return post;
  }

  @Query(returns => [Post], { name: 'getPostBy' })
  async getPostBy(
    @Args({ name: 'getPostByInput', type: () => GetPostByInput })
    getPostByInput: GetPostByInput,
  ): Promise<Post[]> {
    console.log(getPostByInput);

    const post = PostFactory.build();

    return [post];
  }

  @ResolveField(returns => Author, { name: 'author' })
  async author(@Parent() post: Post) {
    console.log('resolve author, from root shit');

    const author = AuthorFactory.build();
    return author;
  }

  @ResolveField(returns => Shit, { name: 'shit' })
  async shit(): Promise<Shit> {
    const shit = ShitFactory.build();

    return shit;
  }
}
