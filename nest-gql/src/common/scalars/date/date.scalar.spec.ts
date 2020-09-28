import { DateScalar } from './date.scalar';
import { Kind, ValueNode } from 'graphql';

function expectAppliedScalarDecorator(cls) {
  const res = Reflect.getMetadataKeys(cls);

  expect(res).toStrictEqual(['graphql:scalar_name', 'graphql:scalar_type']);
}

describe('date scalar', () => {
  it('should defined', () => {
    expect(new DateScalar()).toBeDefined();

    expectAppliedScalarDecorator(DateScalar);
  });

  describe('should define method', () => {
    const scalar = new DateScalar();

    console.log(DateScalar.prototype);

    it('should defined description', () => {
      expect(scalar.description).toBeDefined();
    });

    describe('method parseValue', () => {
      it('should defined', () => {
        expect(scalar.parseValue).toBeDefined();
      });

      it('should pass value as number type', () => {
        const value = 123;

        scalar.parseValue(value);
      });

      it('should return parsed value', () => {
        const value = 1241241241;

        const parsed = scalar.parseValue(value);

        expect(parsed).toBeDefined();
        expect(parsed).toStrictEqual(new Date(value));
      });
    });

    describe('method serialize', () => {
      it('should defined', () => {
        expect(scalar.serialize).toBeDefined();
      });

      it('should pass date type to arg value', () => {
        const date = new Date();

        scalar.serialize(date);
      });

      it('should return serialized value', () => {
        const date = new Date();

        const value = scalar.serialize(date);

        expect(value).toBeDefined();
        expect(value).toStrictEqual(date.getTime());
      });
    });

    describe('method parseLiteral', () => {
      it('should defined', () => {
        expect(scalar.parseLiteral).toBeDefined();
      });

      it('should pass ValueNode to arg ast', () => {
        const ast: ValueNode = {
          fields: undefined,
          kind: undefined,
          name: undefined,
          value: undefined,
          values: undefined,
        };

        scalar.parseLiteral(ast);
      });

      it('should return parsed value', () => {
        const ast: ValueNode = {
          kind: Kind.INT,
          value: '123',
        };

        const result = scalar.parseLiteral(ast);

        expect(result).toBeDefined();
        expect(result).toStrictEqual(new Date(ast.value));
      });

      it('should return null, if ast.kind is not Kind.INT', () => {
        const ast: ValueNode = {
          kind: Kind.STRING,
          value: '123',
        };

        const result = scalar.parseLiteral(ast);

        expect(result).toBeDefined();
        expect(result).toBeNull();
      });
    });
  });
});
