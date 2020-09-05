require('dotenv').config()

const {v1: uuid} = require('uuid')
const env = process.env
const axios = require('axios')


const express = require('express')

const AWS = require('aws-sdk');

const s3 = new AWS.S3({
    accessKeyId: env.accessKeyId,
    secretAccessKey: env.secretAccessKey,
    // region: 'eu-west-1'
})


const bucketName = env.Bucket


const body = Buffer.from("1234124")


async function serverSide(){

}


async function clientSide(){

}

async function start() {
    try {

        const key = `example-${uuid()}.text`
        const contentType = "text/plain"

        const params = {
            Bucket: bucketName,
            Key: key,
            Expires: 3600,
            ContentType: contentType,
            ACL: 'public-read',
        };
        const signedUrlPut = s3.getSignedUrl('putObject', params);


        const buffer = Buffer.from("abcdefg,hijklmnop, qrs, tuv, double-u, x, y and z")

        console.log(signedUrlPut)
        const url = signedUrlPut.split("?")[0]

        await axios.put(url, buffer, {
            headers: {
                "Content-Type": "text/plain"
            }
        });

        console.log('here')
    } catch (e) {
        const {response} = e
        if (response.data) {
            console.log(response.data)

        }
    }


}


start();

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