import { Post } from './post.model';

export class PostFactory {
  static build(): Post {
    const post = new Post();

    post.id = 123;
    post.votes = 123;
    post.title = '123123';

    return post;
  }
}
