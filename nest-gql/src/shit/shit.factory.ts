import { Shit } from './shit.model';

export class ShitFactory {
  static build(): Shit {
    const shit = new Shit();
    shit.id = 1;

    return shit;
  }
}
