package model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class OmDBHelper {
    private String nomeDoFilme;
    private OmDBHelperListener listener;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(nomeDoFilme != null) {
                String dadosDoFilme = pedirDadosDoFilmeUsandoConnectionManager(nomeDoFilme);
                if(listener != null)
                    listener.chegaramOsDadosDoFilme(dadosDoFilme);
            }
        }
    };

    public OmDBHelper() {
        nomeDoFilme = null;
    }

    public void setListener(OmDBHelperListener listener) {
        this.listener = listener;
    }

    public void requestFilmeByName(String nome) {
        this.nomeDoFilme = nome;
        (new Thread(runnable)).start();
    }

    private String pedirDadosDoFilmeUsandoConnectionManager(String nomeDoFilme) {
        List<String> requisicao = montarRequisicao(nomeDoFilme);
        String response = chamandoOConnectionManager(requisicao);
        return limparCabecalho(response);
    }

    private String limparCabecalho(String response) {
        int posicaoDaChave = response.indexOf('{');
        response = response.substring(posicaoDaChave);
        return response;
    }

    private String chamandoOConnectionManager(List<String> requisicao) {
        String response = null;
        OmDBConnectionManager manager = new OmDBConnectionManager();
        try {
            response = manager.requisitarDados(requisicao);
        } catch (IOException e) {
        }

        return response;
    }

    private List<String> montarRequisicao(String nomeDoFilme) {
        ArrayList<String> requisicao = new ArrayList<>(1);
        nomeDoFilme = nomeDoFilme.replace(' ', '+');
        requisicao.add("GET /?t=" + nomeDoFilme + "&apikey=ab82719c HTTP/1.0");
        requisicao.add("Host: www.omdbapi.com");
        return requisicao;
    }
}
