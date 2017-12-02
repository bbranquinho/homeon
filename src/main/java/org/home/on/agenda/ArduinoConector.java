package org.home.on.agenda;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import javax.websocket.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ArduinoConector {

    private static final Logger LOGGER = LogManager.getLogger(ArduinoConector.class);

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
    public class Conector {
        @Transient
        private String hostname;

        @Transient
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
            LOGGER.info("sessao aberta");
            this.sessao = sessao;
        }

        @OnClose
        public void onClose(Session sessao) {
            LOGGER.info("sessao fechada");
            conectores.remove(this);
        }

        @OnError
        public void onError(Throwable erro) {
            LOGGER.error("Erro na sessão com o Arudino: " + erro.getMessage(), erro);
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
                LOGGER.error("Erro ao enviar mensagem para o cliente. " + e.getMessage(), e);
            }
        }

    }

}
