import {
  Args,
  Int,
  Mutation,
  Parent,
  ResolveField,
  Resolver,
} from '@nestjs/graphql';
import { Post } from './post.model';
import { Author } from '../author/author.model';
import { Shit } from '../shit/shit.model';

async function getShit(post) {
  const shit = new Shit();
  shit.id = 1;
  shit.post = post;

  return shit;
}

@Resolver(of => Post)
export class PostResolver {
  @Mutation(returns => Post, { name: 'shitPost' })
  async upvotePost(@Args({ name: 'postId', type: () => Int }) postId: number) {
    const author = new Author();
    author.firstName = 'shit';
    author.id = 234;
    author.lastName = 'ass';

    const post1 = new Post();
    post1.id = 1;
    post1.title = '@34';
    post1.votes = 3;
    post1.author = author;
    post1.shit = await getShit(post1);

    author.posts = [post1];

    return post1;
  }

  @ResolveField(returns => Author, { name: 'author' })
  async author(@Parent() post: Post) {
    console.log('resolve author, from root shit');

    const author = new Author();
    author.firstName = 'shit';
    author.id = 234;
    author.lastName = 'ass';

    return author;
  }
}
