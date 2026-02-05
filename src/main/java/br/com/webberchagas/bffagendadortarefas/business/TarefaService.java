package br.com.webberchagas.bffagendadortarefas.business;

import br.com.webberchagas.bffagendadortarefas.business.dtos.StatusNotificacao;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import br.com.webberchagas.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasClient tarefasClient;

    public TarefaDTOResponse salvarTarefa(String token, TarefaDTORequest dto) {
        return tarefasClient.gravarTarefa(dto, token);
    }

    public List<TarefaDTOResponse> buscaTarefasPorPeriodo(String token, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasClient.buscarTarefaPorPeriodo(token, dataInicial, dataFinal);
    }

    public List<TarefaDTOResponse> buscarTarefasPorEmailUsuario(String token) {
        return tarefasClient.buscarTarefaPorPeriodo(token);
    }

    public void deletaTarefaPorId(String id, String token) {;
        tarefasClient.deletaTarefaPorId(id,token);
    }

    public TarefaDTOResponse alteraStatus(StatusNotificacao statusNotificacao, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(statusNotificacao, id, token);
    }

    public TarefaDTOResponse updateTarefas(TarefaDTORequest dto, String id, String token) {
        return tarefasClient.atualizarTarefa(dto, id, token);
    }
}
