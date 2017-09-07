package pl.oskarpolak.models;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class ChatSocket {
    private static ChatSocket socket = new ChatSocket();

    public static ChatSocket getSocket() {
        return socket;
    }

    private WebSocketContainer webSocketContainer;
    private Session session;
    private ChatSocket() {
        webSocketContainer  = ContainerProvider.getWebSocketContainer();
    }



    @OnOpen
    public void open(Session session){
        this.session = session;
        System.out.println("Połączno!");
    }

    @OnMessage
    public void message(Session session, String message){
        //ta metoda wykona sie gdy do klienta przyjdzie wiadomosc
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        try {
            webSocketContainer.connectToServer(this, new URI("ws://localhost:8080/chat"));
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
