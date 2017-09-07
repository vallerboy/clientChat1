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
    private IMessageObserver observer;

    private ChatSocket() {
        webSocketContainer  = ContainerProvider.getWebSocketContainer();
    }




    public IMessageObserver getObserver() {
        return observer;
    }

    public void setObserver(IMessageObserver observer) {
        this.observer = observer;
    }

    @OnOpen
    public void open(Session session){
        this.session = session;
        System.out.println("Połączno!");
    }

    @OnMessage
    public void message(Session session, String message){
       observer.handleMessage(message);
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
