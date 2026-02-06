package br.com.webberchagas.bffagendadortarefas.business;

import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import br.com.webberchagas.bffagendadortarefas.infrastructure.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificacaoClient notificacaoClient;

    public void enviarNotificacaoEmail(TarefaDTOResponse dto) {
        notificacaoClient.enviarEmail(dto);
    }
}
