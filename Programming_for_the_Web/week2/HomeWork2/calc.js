/*
 * Implement all your JavaScript in this file!
 */
//brings about 87% - did not investigate edge cases

 "use strict";

//create targe display
var display = $("#display");
var number = "";
var valueArray = [];
var operation = null;

$("button[value]").click(//reading from number buttons buttons only
 	//first function - reading numbers
 	function(){
 		number = number + $(this).val();
    	display.val(number);
	}
);

$("button:not([value])").click(handleOperations);//select any button but the number button

function handleOperations(e){
	// console.log("handle operations: ", e.target.id)
	// when operation button pressed add first value to array and clear it
	if(number){
		valueArray.push(parseFloat(number));
	}
	number = "";//reset number
	switch(e.target.id){//do something depending on operation selected
	case "addButton":
		operation = "+";
		break;
	case "subtractButton":
		operation = "-";
		break;
	case "multiplyButton":
		operation = "*";
		break;
	case "divideButton":
		operation = "/";
		break;
	case "equalsButton":
		console.log("=");
		break;
	case "clearButton"://clear
		// console.log("clear");
		operation = "C";
		break;
	default:
		break;
	}
	handleUtilityPress();
}


function handleUtilityPress(){
	var res;
	if(operation === "C"){
		console.log("clear everything");
		operation=null;
		valueArray = [];
		number="";
		display.val("");
	}else{
		if(valueArray.length >= 2){
			res = handleOperation(operation);
			valueArray = [];
			valueArray.push(res);
		}
	}
	
}


function handleOperation(action){
	var result;
	switch(action){
	case "+":
		result = valueArray[0] + valueArray[1];
		break;
	case "-":
		result = valueArray[0] - valueArray[1];
		break;
	case "*":
		result = valueArray[0] * valueArray[1];
		break;
	case "/":
		result = valueArray[0] / valueArray[1];
		break;
	default:
		break;
		}
	display.val(result);
	return result;
}
