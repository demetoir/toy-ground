import { Module } from '@nestjs/common';
import { DateScalar } from './date/date.scalar';
import { PriceScalar } from './price/price.scalar';

@Module({
  providers: [DateScalar, PriceScalar],
})
export class ScalarsModule {}
