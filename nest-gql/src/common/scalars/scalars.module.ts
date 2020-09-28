import { Module } from '@nestjs/common';
import { DateScalar } from './date/date.scalar';

@Module({
  providers: [DateScalar],
})
export class ScalarsModule {}
