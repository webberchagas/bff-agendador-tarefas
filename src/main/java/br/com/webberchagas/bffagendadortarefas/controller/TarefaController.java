package br.com.webberchagas.bffagendadortarefas.controller;

import br.com.webberchagas.bffagendadortarefas.business.TarefaService;
import br.com.webberchagas.bffagendadortarefas.business.dtos.StatusNotificacao;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import br.com.webberchagas.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Endpoints relacionados à gestão de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Registra uma nova Tarefa", description = "Endpoint para criar uma nova tarefa no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest request,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(token, request));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período", description = "Endpoint para busca de tarefas pendentes por período.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefaPorPeriodo(@RequestHeader(name = "Authorization", required = false) String token,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorPeriodo(token, dataInicial, dataFinal));
    }

    @GetMapping
    @Operation(summary = "Busca tarefas pelo e-mail", description = "Endpoint para busca de tarefas por e-mail do usuário no token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefaPorPeriodo(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmailUsuario(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por ID", description = "Endpoint para excluir tarefas por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status da tarefa", description = "Endpoint para alterar Status da tarefa por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas atualizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TarefaDTOResponse> alteraStatusNotificacao(@RequestHeader(name = "Authorization", required = false) String token,
                                                                     @RequestParam("status") StatusNotificacao status,
                                                                     @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status,id,token));
    }

    @PutMapping
    @Operation(summary = "Altera dados da tarefa", description = "Endpoint para alterar os dados da tarefa com o ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TarefaDTOResponse> atualizarTarefa(@RequestHeader("Authorization") String token,
                                                             @RequestBody TarefaDTORequest request,
                                                             @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefas(request,id,token));
    }
}
