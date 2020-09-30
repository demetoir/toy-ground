import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import * as supertest from 'supertest';
import { AppModule } from './app.module';

describe('AppController (e2e)', () => {
  let app: INestApplication;
  let agent: supertest.SuperTest<supertest.Test>;

  beforeEach(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();

    agent = supertest(app.getHttpServer());
  });

  it('/ (GET)', () => {
    expect(app).toBeDefined();
  });

  it('should getAuthor', async function() {
    await agent
      .post('/graphql')
      .send({
        query:
          'query{\n' +
          '  getAuthor(id:1){\n' +
          '\t\ttype\n' +
          '  }\n' +
          '  getAuthorByType(type: FREE){\n' +
          '    id\n' +
          '    type\n' +
          '  }\n' +
          '}',
      })
      .expect(200)
      .expect(res => {
        res.body;
        console.log(res.body);
      });
  });

  it('should able to send variable', async function() {
    await agent
      .post('/graphql')
      .send({
        query:
          'query shit($input: GetPostByInput!){\n' +
          '  getPostBy(getPostByInput:$input){\n' +
          '    id\n' +
          '  }\n' +
          '}',
        variables:
          '{\n' +
          '  "input": {\n' +
          '    "title": "shit",\n' +
          '    "votes": 123\n' +
          '  }\n' +
          '}',
      })
      .expect(200)
      .expect(res => {
        res.body;
        console.log(res.body);
      });
  });
});
