import {
  Args,
  Context,
  Int,
  Mutation,
  Parent,
  Query,
  ResolveField,
  Resolver,
} from '@nestjs/graphql';
import { Author } from './author.model';
import { Shit } from '../shit/shit.model';
import { Post } from '../post/post.model';
import { AuthorFactory } from './author.factory';
import { PostFactory } from '../post/post.factory';
import { DateScalar } from '../common/scalars/date/date.scalar';
import { PriceScalar } from '../common/scalars/price/price.scalar';

async function getShit(post) {
  const shit = new Shit();
  shit.id = 1;
  shit.post = post;

  return shit;
}

// Query나 mutation 데콘는 어디에 들어가도 딱히 상관없는듯하다
// 다만 ResolveField 데코는 해당하는 @Parent 에 해당하는 @Resolver 내부에서 선언되어야
// gql상에서 traversal이 가능하다
// @Root 는 확인 해봐야한다
// @Resolver 에서는 무조건 타입을 지정하고 해당 타입을 root로 삼는 모든 query, mutation 를 정의한다
// 그리고 해당 타입에 해당하는 @Parent 인자를 가지는 @ResolveField 를 정의한다
@Resolver(() => Author)
export class AuthorsResolver {
  // private authorsService: AuthorsService,
  // private postsService: PostsService,

  @Mutation(returns => Author, { name: 'updateAuthor' })
  async updateAuthor(
    @Args('id', { type: () => Int }) id: number,
    // @Args('price', { type: () => PriceScalar, name: 'price' }) price: number,
  ) {
    // console.log(price);
    const author: Author = AuthorFactory.build();

    return author;
  }

  @Query(() => Author, { name: 'getAuthor' })
  async getAuthor(
    @Args('id', { type: () => Int }) id: number,
    @Context() context,
  ): Promise<Author> {
    const author: Author = AuthorFactory.build();

    return author;
  }

  // notice Resolver
  @ResolveField(() => [Post], { name: 'posts' })
  async posts(
    @Parent()
    author: Author,
  ) {
    const { id } = author;
    console.log(`in post parent author id ${id}`);

    const post1 = PostFactory.build();
    const post2 = PostFactory.build();
    return [post1, post2];
  }
}
