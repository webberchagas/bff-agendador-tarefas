package br.com.webberchagas.bffagendadortarefas.infrastructure.client;

import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {


    @PostMapping
    void enviarEmail(@RequestBody TarefaDTOResponse dto);
}
