import { Test, TestingModule } from '@nestjs/testing';
import { AuthorsResolver } from './author.resolver';
import { AuthorService } from './author.service';

describe('author resolver', () => {
  let authorsResolver: AuthorsResolver;
  let authorService: AuthorService;
  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [AuthorsResolver, AuthorService],
    }).compile();

    authorsResolver = module.get(AuthorsResolver);
    authorService = module.get(AuthorService);
  });

  it('should di', () => {
    expect(authorService).toBeDefined();
    expect(authorsResolver).toBeDefined();
  });

  it('should get author', async () => {
    const id = 3;
    const author = await authorsResolver.getAuthor(id, {});
    expect(author).toBeDefined();
  });
});
