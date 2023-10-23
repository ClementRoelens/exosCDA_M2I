const fs = require('fs');
const LineReader = require("n-readlines");

const lineReader = new LineReader("source.txt");
let line = "";

while (line = lineReader.next()) {
    line = line.toString().trim();
    fs.mkdir(line, err => {
        if (err){
            console.log(err);
        }
    });
}
