package pl.oskarpolak.controllers;

import javafx.fxml.Initializable;
import pl.oskarpolak.models.ChatSocket;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    private ChatSocket socket;

    public Controller(){
        socket = ChatSocket.getSocket();
    }

    public void initialize(URL location, ResourceBundle resources) {
        socket.connect();
    }
}
