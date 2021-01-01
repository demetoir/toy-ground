const express = require('express')

const app = express();

app.use('*', (req, res) => {
    res.send('hello shit world')
})

app.listen(3000, () => {
    console.log('server listen')
})
