
import { SafeAreaView, StyleSheet, Text, View } from 'react-native';
import React, { useState } from 'react';
import Key from './Key';

interface Calculator {
  inputText: string;
  firstNumber: number;
  firstLineText:string;
  selectedOperation: string;
}

const SimpleCalculator = () => {

  const [calculator, setCalculator] = useState<Calculator>({
    inputText: "",
    firstNumber: 0,
    firstLineText:"",
    selectedOperation: ""
  });


  function keyPress(keyPressed: string) {
    if (keyPressed === "%") {
      console.clear();
    }

    if (keyPressed === "AC") {
      setCalculator({
        inputText: "",
        firstLineText:"",
        selectedOperation: "",
        firstNumber: 0
      });
      return;
    }

    if (keyPressed === "Del") {
      // S'il n'y a rien dans l'affichage inférieur, il ne se passe rien
      if (calculator.inputText !== "") {
        // Si c'est un point, il faut désactiver le decimalMode
        if (calculator.inputText[calculator.inputText.length - 1] === ".") {
          const newCalculator = { ...calculator, isDecimal: false };
          setCalculator(newCalculator);
          // setDecimalNumber(0);
        }
        // Sinon, on supprime simplement
        else {
          const newInputText = calculator.inputText.substring(0, calculator.inputText.length - 2);
          const newCalculator = { ...calculator, inputText: newInputText };
          setCalculator(newCalculator);
        }
      }
      return;
    }

    // Si la touche enfoncée est un nombre, on l'ajoute juste
    if (!isNaN(parseInt(keyPressed, 10))) {
      const newInputText = calculator.inputText + keyPressed;
      const newCalculator = { ...calculator, inputText: newInputText };
      setCalculator(newCalculator);
    } else {
      // Sinon, c'est que l'utilisateur entre une opération
      // S'il y a déjà une opération, on fait le calcul
      if (calculator.selectedOperation !== "") {
        const secondNumber = parseFloat(calculator.inputText);
        let result = 0;
        switch (calculator.selectedOperation) {
          case "+":
            result = calculator.firstNumber + secondNumber;
            break;
          case "-":
            result = calculator.firstNumber - secondNumber;
            break;
          case "X":
            result = calculator.firstNumber * secondNumber;
            break;
          case "/":
            result = calculator.firstNumber / secondNumber;
            break;
        }
        let newCalculator:Calculator;
        // Si l'opération est un "=", alors on affiche juste le résultat
        if (keyPressed === "="){
          newCalculator = {
            firstNumber:0,
            firstLineText: `${calculator.firstLineText} ${secondNumber} =`,
            selectedOperation:"",
            inputText : result.toString()
          };
        } 
        // Sinon, la ligne du haut affichera le calcul et l'opération
        else {
          newCalculator = {
            firstNumber: result,
            firstLineText : result.toString() + " " + keyPressed,
            selectedOperation: keyPressed,
            inputText: ""
          };
        }
        setCalculator(newCalculator);
      }
      // Sinon, on commence par enregistrer notre saisie comme un nombre
      else {
        const firstNumber = parseFloat(calculator.inputText);
        // On détecte notre opération
        let selectedOperation = "";
        switch (keyPressed) {
          case "+":
            selectedOperation = "+";
            break;
          case "-":
            selectedOperation = "-";
            break;
          case "X":
            selectedOperation = "X";
            break;
          case "/":
            selectedOperation = "/";
            break;
        }
        // Puis on met à jour notre calculette en vidant le champ texte et en retenant le premier nombre et l'opération
        const newCalculator: Calculator = {
          inputText: "",
          firstNumber: firstNumber,
          firstLineText : firstNumber.toString() + " " + selectedOperation,
          selectedOperation: selectedOperation
        };
        setCalculator(newCalculator);
      }
    }
  }

  return (
    <SafeAreaView>
      <Text style={[styles.displayInput, styles.storedText]}>{calculator.firstLineText}</Text>
      <Text style={[styles.displayInput, styles.currentInput]}>{calculator.inputText}</Text>
      <View style={styles.keysArea}>
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='AC' isOperation={true} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='^' isOperation={true} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='%' isOperation={true} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='/' isOperation={true} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='7' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='8' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='9' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='X' isOperation={true} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='4' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='5' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='6' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='-' isOperation={true} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='1' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='2' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='3' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='+' isOperation={true} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='.' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='0' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='Del' isOperation={false} />
        <Key onKeyPress={keyPressed => keyPress(keyPressed)} keyName='=' isOperation={true} />
      </View>
    </SafeAreaView>
  )
}

export default SimpleCalculator

const styles = StyleSheet.create({
  displayInput: {
    height: 100,
    textAlign: "right"
  },
  storedText: {
    fontSize: 20,
    verticalAlign: "middle"
  },
  currentInput: {
    fontSize: 60
  },
  keysArea: {
    flexDirection: "row",
    flexWrap: "wrap"
  }
});
