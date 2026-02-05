package br.com.webberchagas.bffagendadortarefas.infrastructure.client;

import br.com.webberchagas.bffagendadortarefas.business.dtos.in.EnderecoDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.LoginRequestDTO;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TelefoneDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.UsuarioDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.EnderecoDTOResponse;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TelefoneDTOResponse;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.UsuarioDTOResponso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponso buscarUsuarioPorEmail(@RequestParam String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponso registerNewUser(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDto);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponso atualizarUsuarioPorEmail(@RequestBody UsuarioDTORequest usuarioDTO,
                                                @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEnderecoPorId(@RequestParam("id") Long id,
                                              @RequestBody EnderecoDTORequest enderecoDTO,
                                              @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefonePorId(@RequestParam("id") Long id,
                                              @RequestBody TelefoneDTORequest telefoneDTO,
                                              @RequestHeader("Authorization") String token);

}
