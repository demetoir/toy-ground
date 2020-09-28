import { Module } from '@nestjs/common';
import { AuthorsResolver } from './author.resolver';
import { PostResolver } from './post.resolver';
import { ShitResolver } from './shit.resolver';

@Module({
  providers: [AuthorsResolver, PostResolver, ShitResolver],
})
export class AuthorModule {}
