
import { SafeAreaView, StyleSheet, Text, View } from 'react-native';
import Key from './components/Key';
import { useEffect, useState } from 'react';
import SimpleCalculator from './components/SimpleCalculator';

function App(): JSX.Element {

  // const [currentNumber, setCurrentNumber] = useState(0);
  // const [previousNumber, setPreviousNumber] = useState(0);
  // // const [decimalNumber, setDecimalNumber] = useState(0);
  
  // const [isDecimalMode, setIsDecimalMode] = useState(false);
  // const [lastOperation, setLastOperation] = useState("");

  // const [storedCalculText, setStoredCalculText] = useState("");
  // const [inputText, setInputText] = useState("")

  // useEffect(() => {
  //   console.log("inputText : " + inputText);
  //   setCurrentNumber(parseFloat(inputText));
  //   console.log("previousNumber = " + previousNumber);
  // },[inputText])

  // function reset(){
  //   setPreviousNumber(0);
  //   setCurrentNumber(0);
  //   // setDecimalNumber(0);
  //   setIsDecimalMode(false);
  //   setLastOperation("");
  // }

  // function keyPress(keyPressed: string) {

  //   if (keyPressed === "%"){
  //     console.clear();
  //   }

  //   if (keyPressed === "AC") {
  //     reset();
  //     setInputText("");
  //     setStoredCalculText("");
  //     return;
  //   }

  //   if (keyPressed === "Del"){
  //     // S'il n'y a rien dans l'affichage inférieur, il ne se passe rien
  //     if (inputText !== ""){
  //       // Si c'est un point, il faut désactiver le decimalMode
  //       if (inputText[inputText.length-1] === "."){
  //         setIsDecimalMode(false);
  //         // setDecimalNumber(0);
  //       }
  //       // Sinon, on supprime simplement
  //       else {
  //         inputText.substring(0,inputText.length-2);
  //       }
  //     }
  //     return;
  //   }

  //   // Si l'input est un nombre, on va juste l'afficher
  //   if (!isNaN(parseInt(keyPressed, 10))) {
  //     setInputText(prevText => prevText + keyPressed);
  //   } 
  //   else {
  //     console.log("la touche n'est pas un nombre");
  //     // Si le NaN est un point, on passe en mode décimal
  //     if (keyPressed === ".") {
  //       // Si un petit malin essaie de rajouter un point alors qu'il y en a un déjà un, il ne se passera rien
  //       if (!isDecimalMode){
  //         setIsDecimalMode(true);
  //         setInputText(prevInputText => prevInputText + ".");
  //       }
  //       return;
  //     }
  //     // Si l'input n'est pas un nombre ni un point, alors c'est une opération
  //     // Si une opération avait déjà été entrée, alors on la calcule et on aura le résultat de cette précédente opération dans l'affichage supérieur
  //     switch (lastOperation) {
  //       case "+":
  //         console.log("opération en cours", `${previousNumber} ${lastOperation} ${inputText}`);
  //         const result = previousNumber + currentNumber;
  //         setPreviousNumber(result);
  //         // setPreviousNumber(prevNumber => prevNumber + parseFloat(inputText));
  //         console.log("opération effectuée. result = " + result)
  //         console.log("opération effectuée. previousNumber = " + previousNumber)
  //         break;
  //       case "-":
  //         console.log("opération en cours", `${previousNumber} ${lastOperation} ${inputText}`);
  //         setPreviousNumber(prevNumber => prevNumber - parseFloat(inputText));
  //         console.log("opération effectuée. previousNumber = " + previousNumber)
  //         break;
  //       case "*":
  //         console.log("opération en cours", `${previousNumber} ${lastOperation} ${inputText}`);
  //         setPreviousNumber(prevNumber => prevNumber * parseFloat(inputText));
  //         console.log("opération effectuée. previousNumber = " + previousNumber)
  //         break;
  //       case "/":
  //         console.log("opération en cours", `${previousNumber} ${lastOperation} ${inputText}`);
  //         setPreviousNumber(prevNumber => prevNumber / parseFloat(inputText));
  //         console.log("opération effectuée. previousNumber = " + previousNumber)
  //         break;
  //     }
  //     // Si c'est un égal, alors on affiche le résultat en bas
  //     if (keyPressed === "="){
  //       setInputText(previousNumber.toString());
  //       reset();
  //       return;
  //     }
  //     setLastOperation(keyPressed);
  //     setPreviousNumber(currentNumber);
  //     setStoredCalculText(`${currentNumber} ${keyPressed}`)
  //     setInputText("");
  //   }

  // }

  return (
    <SafeAreaView style={styles.global}>
      <SimpleCalculator/>
      {/* <Text style={[styles.displayInput, styles.storedText]}>{storedCalculText}</Text>
      <Text style={[styles.displayInput, styles.currentInput]}>{inputText}</Text>
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
      </View> */}
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  global: {
    flex: 1,
    justifyContent: "center"
  },
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

export default App;
