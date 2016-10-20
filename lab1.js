var inputLabel = document.getElementById("inputLabel");
var resultP = document.getElementById("resultP");
var errorP = document.getElementById("errorP");
var theButton = document.getElementById("theButton");
var resultExp = document.getElementById("resultExpression");


var patternOfLegal = /^[0-9+*]+$/;
var patternOfExpression = /^(([a-zA-Z])|([0-9]+))([\+\*](([a-zA-Z])|([0-9]+)))*$/;
var patternOfCommand = /^\!simplify ([a-zA-Z]=([0-9])+)+/;
var patternOfQiudao = /^!d\/d [a-zA-Z]$/

theButton.onclick = function getinput(){
    inputText = inputLabel.value;
    //alert(inputText);
    judgement(inputText);
}

function judgement(theText){
    if (patternOfExpression.test(theText) == true) {
        resultP.setAttribute("style","display:inline"); //resultP.style.display = inline;
        resultExp.setAttribute("style","display:inline"); //resultExp.style.display = inline;
        errorP.setAttribute("style","display:none"); //errorP.style.display = none;
        theExpression = theText;
        consoleLog();
    }
    else if (patternOfCommand.test(theText) == true) {
        resultP.setAttribute("style","display:inline");// resultP.style.display = inline;
        resultExp.setAttribute("style","display:inline");// resultExp.style.display = inline;
        errorP.setAttribute("style","display:none");// errorP.style.display = none;
        splitment(theText);
        consoleLog();
    }
    /*else if (patternOfQiudao.test(theText) == true){
        resultP.setAttribute("style","display:inline");// resultP.style.display = inline;
        resultExp.setAttribute("style","display:inline");// resultExp.style.display = inline;
        errorP.setAttribute("style","display:none");// errorP.style.display = none;
        xxx = inputText[6];
        qiudao(inputText,xxx);
        consoleLog();
    }*/
    else{
        resultP.setAttribute("style","display:none");// resultP.style.display = none;
        resultExp.setAttribute("style","display:none");// resultExp.style.display = none;
        errorP.setAttribute("style","display:inline");// errorP.style.display = inline;
    }
}

// function replacement(input1,input2,str){
//     str.replace(input1,input2);
// }

function splitment(str){
    var strsplited = str.split(/ |=/);
    for(var i = 1 ; i < strsplited.length + 1; i++){
        theExpression = theExpression.replace(strsplited[i],strsplited[i+1]);
        i++;
        // replacement(strsplited[i],strsplited[i+1],theExpression);
        // i++;
    }
}

function consoleLog(){
    // resultExp.value = theExpression;
    if(patternOfLegal.test(theExpression) == false){
        resultExpression.innerHTML = theExpression;
    }
    else{
        resultExpression.innerHTML = eval(theExpression);
    }

}

/*function qiudao(expression1,x){
    var strsplited = expression1.split("+");
    var newString = "";
    for(var i = 0 ; i < 10; i++){
        var num = 0;
        var biaozhi = 0;
        var k = strsplited[i];
        for(var j = 0 ; j < 10 ; j++){

            if (strsplited[i][j] == x){
                num++;
                if((strsplited[i][j-1] == "*") & (biaozhi == 0)){
                    strsplited[i] = strsplited[i].slice(0,j-1) + strsplited[i].slice(j+1,strsplited.length+1);
                    biaozhi = 1;
                }
                else if ((strsplited[i][j+1] == "*") & (biaozhi == 0)) {
                    strsplited[i] = strsplited[i].slice(0,j) + strsplited[i].slice(j+2,strsplited.length+1);
                    biaozhi = 1;
                }
                else{
                    strsplited[i] = "1";
                }
            }
        }
        newString = newString.concat(strsplited[i]);
    }
    theExpression = newString;
}

*/

















//
//123
