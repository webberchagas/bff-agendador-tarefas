package br.com.webberchagas.bffagendadortarefas.infrastructure.client;

import br.com.webberchagas.bffagendadortarefas.business.dtos.EnderecoDTO;
import br.com.webberchagas.bffagendadortarefas.business.dtos.TelefoneDTO;
import br.com.webberchagas.bffagendadortarefas.business.dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam String email,
                                     @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTO registerNewUser(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDTO usuarioDto);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTO atualizarUsuarioPorEmail(@RequestBody UsuarioDTO usuarioDTO,
                                        @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                 @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTO atualizaEnderecoPorId(@RequestParam("id") Long id,
                                      @RequestBody EnderecoDTO enderecoDTO,
                                      @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                 @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizaTelefonePorId(@RequestParam("id") Long id,
                                      @RequestBody TelefoneDTO telefoneDTO,
                                      @RequestHeader("Authorization") String token);

}
