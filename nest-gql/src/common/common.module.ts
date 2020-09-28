import { Module } from '@nestjs/common';
import { ScalarsModule } from './scalars/scalars.module';

@Module({
  providers: [ScalarsModule],
})
export class CommonModule {}
