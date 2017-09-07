package pl.oskarpolak.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.oskarpolak.models.ChatSocket;
import pl.oskarpolak.models.IMessageObserver;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Controller implements Initializable, IMessageObserver {


    private ChatSocket socket;

    @FXML
    TextArea textMessages;

    @FXML
    TextField textMessage;



    public Controller(){
        socket = ChatSocket.getSocket();
    }

    public void initialize(URL location, ResourceBundle resources) {
        socket.connect();
        textMessage.requestFocus();
        textMessages.setWrapText(true);

        socket.setObserver(this);


        textMessage.setOnKeyPressed(s -> {
            if(s.getCode() == KeyCode.ENTER){
                socket.sendMessage(textMessage.getText());
                textMessage.clear();
            }
        });
    }

    @Override
    public void handleMessage(String s) {
        textMessages.appendText(s + "\n");
    }
}
