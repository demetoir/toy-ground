require('dotenv').config()

const env = process.env

const express = require('express')

const AWS = require('aws-sdk');

const s3 = new AWS.S3({
    accessKeyId: env.accessKeyId,
    secretAccessKey: env.secretAccessKey,
    // region: 'eu-west-1'
})


const bucketName = env.Bucket

const params = {

    Bucket: bucketName,
    Key: '/example.text',
    Expires: 14 * 24 * 3600
};

const body = Buffer.from("1234124")

s3.putObject({Bucket: bucketName, Key: 'example.txt', Body: body}, (err, data) => {


    console.log(err, data)


})
const signedUrlPut = s3.getSignedUrl('putObject', params);

const signedUrlRead = s3.getSignedUrl('getObject', params);


console.log(signedUrlPut)
console.log(signedUrlRead)


// const AWS = require('aws-sdk');
// const fs = require('fs');
// const http = require('https');
// const url = require('url');
// const request = require('request');
//
// const s3 = new AWS.S3({
//     accessKeyId: 'KEY',
//     secretAccessKey: 'SECRET',
//     region: 'eu-west-1'
// });
// //request.debug = true;
//
// const params = {
//     Bucket: 'bucket',
//     Key: 'test-upload-file',
//     Expires: 14 * 24 * 3600
// };
// const signedUrlPut = s3.getSignedUrl('putObject', params);
// //console.log('curl -v -T components_map.json "' + signedUrlPut + '"');
//
// const signedUrlRead = s3.getSignedUrl('getObject', params);
// console.log('READ', signedUrlRead);
//
// const remoteUrl = 'http://example.com';
//
// const sourceRequest = request.get(remoteUrl);
// sourceRequest.on('response', onResponse);
//
// function onResponse(res) {
//     const contentLength = res.headers['content-length'];
//     console.log(contentLength);
//     console.log('On response');
//
//     res.pipe(getUploadStream(signedUrlPut, contentLength));
// }
//
// function getUploadStream(repoteUrl, contentLength) {
//     const opts = url.parse(repoteUrl);
//     opts.method = 'PUT';
//     opts.headers = {
//         'Content-Length': contentLength
//     };
//
//     const req = http.request(opts, onRequest);
//
//     function onRequest(res) {
//         console.log('STATUS: ' + res.statusCode);
//         console.log('HEADERS: ' + JSON.stringify(res.headers));
//         res.setEncoding('utf8');
//         res.on('data', function(chunk) {
//             console.log('BODY: ' + chunk);
//         });
//         res.on('end', function() {
//             console.log('No more data in response.')
//         });
//     }
//
//     return req;
// }

//
// const app = express()
//
//
// app.get('/', (req, res) => {
//     res.send({message: 'hello'})
// })
//
//
// app.listen(3000)