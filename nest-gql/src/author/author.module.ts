import {Module} from '@nestjs/common';
import {AuthorsResolver} from "./author.resolver";

@Module({
    providers: [AuthorsResolver],
})
export class AuthorModule {
}
