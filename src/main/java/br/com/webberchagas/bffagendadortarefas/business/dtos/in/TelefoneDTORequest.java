package br.com.webberchagas.bffagendadortarefas.business.dtos.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {

    private Long id;
    private String numero;
    private String ddd;

}
