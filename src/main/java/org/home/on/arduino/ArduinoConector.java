package org.home.on.arduino;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ArduinoConector {

    private Map<String, Conector> conectores = new ConcurrentHashMap();

    public void connectar(String hostname) {
        criarConectorEConectar(hostname);
    }

    public void enviarMensagem(String hostname, String mensagem) {
        Conector conector = conectores.get(hostname);

        if (conector == null) {
            conector = criarConectorEConectar(hostname);
        }

        conector.enviarMensagem(mensagem);
    }

    private Conector criarConectorEConectar(String hostname) {
        Conector conector = new Conector(hostname);
        conectores.put(hostname, conector);
        conector.conectar();
        return conector;
    }

    @ClientEndpoint
    private class Conector {
        private String hostname;

        private Session sessao;

        public Conector(String hostname) {
            this.hostname = hostname;
        }

        public void conectar() {
            try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                container.connectToServer(this, URI.create(hostname));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @OnOpen
        public void onOpen(Session sessao) {
            System.out.println("sessao aberta");
            this.sessao = sessao;
        }

        @OnClose
        public void onClose(Session sessao) {
            System.out.println("sessao fechada");
            conectores.remove(this);
        }

        @OnError
        public void onError(String erro) {
            System.out.println("Erro na sessão com o Arudino: " + erro);
            conectores.remove(this);
        }

        @OnMessage
        public void onMessage(String message) {
            System.out.println(message);
        }

        public void enviarMensagem(String mensagem) {
            try {
                sessao.getBasicRemote().sendText(mensagem);
            } catch (Exception e) {
                System.err.println("Erro ao enviar mensagem para o cliente. " + e.getMessage());
            }
        }

    }

}
