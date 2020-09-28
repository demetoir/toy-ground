import { Module } from '@nestjs/common';
import { AuthorsResolver } from './author.resolver';
import { DateScalar } from '../common/scalars/date/date.scalar';
import { PriceScalar } from '../common/scalars/price/price.scalar';

@Module({
  providers: [AuthorsResolver, DateScalar],
})
export class AuthorModule {}
