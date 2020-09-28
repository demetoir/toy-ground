import { Author } from './author.model';

export class AuthorFactory {
  static build(): Author {
    const author = new Author();
    author.createdAt = new Date();
    author.lastName = 'lastname';
    author.firstName = 'shit';

    return author;
  }
}
