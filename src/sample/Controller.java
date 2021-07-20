package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    public Label labelResultado;
    public TextField tfDigito;

    public void analizarDigito(ActionEvent actionEvent) {
        System.out.println("llama metodo \n");
        tfDigito.requestFocus();
        String digito = tfDigito.getText(); //Se asigna el digito a la variable para poder verificarlo
        digito += " ";
        int estado = 0; //Se inicializa el estado en cero ya que es el inicial
        String verificado = ""; //variabe para almacenar la cadena verificada


        for (int i = 0; i < digito.length(); i++) {//Repite el ciclo las veces de la longitud de la cadena
            char letra = digito.charAt(i); //Se le asigna a la variable la letra en la posicion en que se encuentra el ciclo
            switch (estado) {
                case 0: //Vacio
                    if (num(letra)) { //Verifica si entra un digito
                        System.out.println("entero");
                        verificado += letra;
                        estado = 1;
                    } else if (letra == '.') { //Verifica si entra punto decimal
                        System.out.println("punto al inicio");
                        verificado += letra;
                        estado = 4;
                    } else if (letra == ' ') {
                        labelResultado.setText(" Debes ingresar un dígito.");
                        estado = 0; //Envia al estado inicial
                    } else {
                        estado = 2;
                    }
                    break;
                case 1: //Digito parte entera
                    System.out.println("caso 1");
                    if (num(letra)) { //Verifica si entra un digito
                        verificado += letra;
                        estado = 3;
                    } else if (letra == '.') { //Verifica si entra punto decimal
                        System.out.println("punto en la pos 2");
                        verificado += letra;
                        estado = 5;
                    } else if (letra == ' ') {
                        System.out.println("fin");
                        labelResultado.setText(" " + digito + " es de aceptación.");
                        tfDigito.setText("");
                        estado = 0; //Envia al estado inicial
                    } else {
                        estado = 2;
                    }
                    break;
                case 2: //error
                    System.out.println("caso 2");
                    System.out.println("error");
                    labelResultado.setText(" " + digito + " no es de aceptación.");
                    tfDigito.setText("");
                    estado = 2;
                    break;
                case 3: //Punto
                    System.out.println("caso 3");
                    if (letra == '.') { //Verifica si entra punto decimal
                        System.out.println("punto decimal");
                        verificado += letra;
                        estado = 5;
                    } else if (num(letra)) { //Verifica si entra un digito
                        System.out.println("digito");
                        verificado += letra;
                        estado = 1;
                    } else if (letra == ' ') {
                        System.out.println("fin");
                        labelResultado.setText(" " + digito + " es de aceptación.");
                        tfDigito.setText("");
                        estado = 0; //Envia al estado inicial
                    } else {
                        estado = 2; //Se envia al estado de error
                    }
                    break;
                case 4: //E
                    System.out.println("caso 4");
                    if (letra == 'E') { //Verifica si entra punto E
                        System.out.println("E");
                        verificado += letra;
                        estado = 7;
                    } else if (num(letra)) { //Verifica si entra digito
                        verificado += letra;
                        estado = 5;
                    } else if (letra == ' ') {
                        System.out.println("fin");
                        labelResultado.setText(" " + digito + " es de aceptación.");
                        tfDigito.setText("");
                        estado = 0; //Envia al estado inicial
                    } else {
                        estado = 2; //Se envia al estado de error
                    }
                    break;
                case 5: //Digito parte decimal
                    System.out.println("caso 5");
                    if (num(letra)) { //Verifica si entra digito
                        System.out.println("digito despues de punto");
                        verificado += letra;
                        estado = 4;
                    } else if (letra == 'E') { //Verifica si entra punto E
                        System.out.println("E");
                        verificado += letra;
                        estado = 7;
                    } else if (letra == ' ') {
                        System.out.println("fin");
                        labelResultado.setText(" " + digito + " es de aceptación.");
                        tfDigito.setText("");
                        estado = 0; //Envia al estado inicial
                    } else {
                        estado = 2; //Se envia al estado de error
                    }
                    break;
                case 6: //Digito exponente
                    System.out.println("caso 6");
                    if (num(letra)) { //Verifica si entra digito
                        System.out.println("digito exponente");
                        verificado += letra;
                        estado = 6;
                    } else if (letra == ' ') {
                        System.out.println("fin");
                        labelResultado.setText(" " + digito + " es de aceptación.");
                        tfDigito.setText("");
                        estado = 0; //Envia al estado inicial
                    } else {
                        estado = 2; //Se envia al estado de error
                    }
                    break;
                case 7: //Signo exponente
                    System.out.println("caso 7");
                    if (letra == '-') { //Verifica si entra signo negativo
                        System.out.println("signo exponente");
                        verificado += letra;
                        estado = 6;
                    } else if (num(letra)) { //Verifica si entra digito
                        System.out.println("digito exponente");
                        verificado += letra;
                        estado = 6;
                    } else {
                        System.out.println("error");
                        labelResultado.setText(" " + digito + " no es de aceptación.");
                        tfDigito.setText("");
                        estado = 2;
                    }
                    break;
                default:
                    System.out.println("default");
                    labelResultado.setText(" " + digito + " no es de aceptación.");
                    tfDigito.setText("");
            }
        } //Fin for
        System.out.println("Fin ciclo");
        if ((estado == 1) || (estado == 3) || (estado == 5) || (estado == 6)) {
            System.out.println(" " + digito + " es de aceptación.");
        } else {
            System.out.println(" " + digito + " no es de aceptación.");
        }
    }

    public static boolean num(char letra) {
        boolean bandera = false;
        if (letra == '0' || letra == '1' || letra == '2' || letra == '3' || letra == '4' || letra == '5'
                || letra == '6' || letra == '7' || letra == '8' || letra == '9') {
            bandera = true;
        }
        return bandera;
    }


    //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
}
