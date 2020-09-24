import {Args, Context, Int, Parent, Query, ResolveField, Resolver,} from '@nestjs/graphql';
import {Author} from './author.model';
import {Post} from './post.model';
import {Shit} from './shit.model';

import {parse} from 'graphql';

async function getShit(post) {
    const shit = new Shit();
    shit.id = 1;
    shit.post = post;

    return shit;
}

@Resolver(() => Author)
export class AuthorsResolver {
    // private authorsService: AuthorsService,
    // private postsService: PostsService,

    constructor() {
    }

    @Query(() => Author, {name: 'getAuthor'})
    async getAuthor(
        @Args('id', {type: () => Int}) id: number,
        @Context() context,
    ): Promise<Author> {
        // return this.authorsService.findOneById(id);

        const author = new Author();
        author.firstName = 'shit';
        author.id = 234;
        author.lastName = 'ass';

        console.log(context.req.body);
        const {query} = context.req.body;
        const parsed = parse(query);

        // context.body.
        return author;
    }

    @ResolveField(() => [Post], {name: 'posts'})
    async posts(
        @Parent()
            author: Author,
    ) {
        const {id} = author;
        console.log(`in post parent author id ${id}`);

        const post1 = new Post();
        post1.id = 1;
        post1.title = '@34';
        post1.votes = 3;
        post1.author = author;
        post1.shit = await getShit(post1);

        return [post1, post1];
    }
}
