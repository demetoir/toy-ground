import {Args, Int, Query, Resolver} from "@nestjs/graphql";
import {Author} from "./author.model";

@Resolver(() => Author)
export class AuthorsResolver {
    constructor(
        // private authorsService: AuthorsService,
        // private postsService: PostsService,
    ) {
    }

    @Query(() => Author, {name: 'getAuthor'})
    async author(@Args('id', {type: () => Int}) id: number): Promise<Author> {
        // return this.authorsService.findOneById(id);

        const author = new Author()
        author.firstName = "shit"
        author.id = 234;
        author.lastName = "ass"
        console.log('hrer')
        return author
    }

    // @ResolveField()
    // async posts(@Parent() author: Author) {
    //     const { id } = author;
    //     return this.postsService.findAll({ authorId: id });
    // }
}