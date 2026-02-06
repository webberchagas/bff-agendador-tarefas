package br.com.webberchagas.bffagendadortarefas.infrastructure.client;

import br.com.webberchagas.bffagendadortarefas.business.dtos.StatusNotificacao;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "ms-agendador-tarefas", url = "${agendador.url}")
public interface TarefasClient {

    @PostMapping
    TarefaDTOResponse gravarTarefa(@RequestBody TarefaDTORequest request,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscarTarefaPorPeriodo(@RequestHeader("Authorization") String token,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    );

    @GetMapping
    List<TarefaDTOResponse> buscarTarefaPorPeriodo(@RequestHeader("Authorization") String token);

    @DeleteMapping
    Void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                              @RequestParam("id") String id,
                                              @RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDTOResponse atualizarTarefa(@RequestBody TarefaDTORequest request,
                                      @RequestParam("id") String id,
                                      @RequestHeader("Authorization") String token);

}
