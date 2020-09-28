import { Module } from '@nestjs/common';
import { ShitResolver } from './shit.resolver';

@Module({
  providers: [ShitResolver],
})
export class ShitModule {}
