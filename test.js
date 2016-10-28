var express = require('express');
var app = express();
var request = require('request');
var cheerio = require('cheerio');

var theURLWeLookingFor = 'http://120.27.121.23/game/';
app.get('/',function(req,res){
    /*for(i = 274877906934 ; i < 274877906954 ; i++){
        var newURL = theURLWeLookingFor + i;
        request(newURL,function(error,response,body){
            if(!error && response.statusCode != 404){
                res.json({
                    'the number': i
                });

            }
        });
    };
});*/
    request('http://120.27.121.23/game/274877906944/',function(error,response,body){
        $ = cheerio.load(body);
        res.json({
            'return response.statusCode': response.statusCode ,
            'aaa': $('body').innerHTML
        });
        /*if(!error && response.statusCode == 200){
            $ = cheerio.load(body);
            res.json({
                'Classnum': $('div').length
            });
        }*/
    });
});


app.listen(3000);
