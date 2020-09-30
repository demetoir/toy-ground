import { Author } from './author.model';
import { AuthorTypeEnum } from './author-type.enum';

export class AuthorFactory {
  static build(): Author {
    const author = new Author();
    author.id = 2
    author.createdAt = new Date();
    author.lastName = 'lastname';
    author.firstName = 'shit';
    author.type = AuthorTypeEnum.FREE

    return author;
  }
}
