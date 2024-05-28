package br.com.lelis.math;

import br.com.lelis.converters.NumberConverter;
import br.com.lelis.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.RequestMethod;

public class SimpleMath {

    public Double sum(Double numberOne, Double numberTwo){

        return numberOne + numberTwo;
    }


    public Double subtraction(Double numberOne, Double numberTwo){

        return numberOne - numberTwo;
    }


    public Double multiplication(Double numberOne, Double numberTwo){

        return numberOne * numberTwo;
    }


    public Double division(Double numberOne, Double numberTwo){

        return numberOne / numberTwo;
    }


    public Double mean(Double numberOne, Double numberTwo){

        return (numberOne + numberTwo) / 2;
    }


    public Double sqrt(Double number){

        return Math.sqrt(number);
    }


}
