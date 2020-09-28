import { Module } from '@nestjs/common';
import { GraphQLModule } from '@nestjs/graphql';
import { join } from 'path';
import { AuthorModule } from './author/author.module';
import { ShitModule } from './shit/shit.module';
import { PostModule } from './post/post.module';
import { CommonModule } from './common/common.module';

@Module({
  imports: [
    CommonModule,
    AuthorModule,
    ShitModule,
    PostModule,
    GraphQLModule.forRoot({
      autoSchemaFile: join(process.cwd(), 'src/schema.gql'),
      sortSchema: true,
    }),
  ],
})
export class AppModule {}
