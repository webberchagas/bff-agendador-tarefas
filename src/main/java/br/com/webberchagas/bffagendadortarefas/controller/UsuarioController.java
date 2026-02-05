package br.com.webberchagas.bffagendadortarefas.controller;

import br.com.webberchagas.bffagendadortarefas.business.UsuarioService;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.EnderecoDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.LoginRequestDTO;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TelefoneDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.UsuarioDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.EnderecoDTOResponse;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TelefoneDTOResponse;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.UsuarioDTOResponso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Endpoints relacionados ao cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Registrar novo usuário", description = "Endpoint para criar um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UsuarioDTOResponso> registerNewUser(@RequestBody UsuarioDTORequest usuarioDTO) {
        UsuarioDTOResponso usuarioCreated = usuarioService.salvar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreated);
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Endpoint para logar no sistema e pegar o token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public  ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(usuarioService.loginUsuario(request));
    }

    @GetMapping
    @Operation(summary = "Buscar usuário pelo e-mail", description = "Endpoint para buscar dados do usuário pelo e-mail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UsuarioDTOResponso> buscarUsuarioPorEmail(@RequestParam String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuário por id", description = "Endpoint para excluir um usuário pelo id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "U     suário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletarUsuarioPorEmail(email,token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuário", description = "Endpoint para atualizar os dados de usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UsuarioDTOResponso> atualizarUsuarioPorEmail(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                       @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço do usuário", description = "Endpoint para atualizar um endereço já cadastrado do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EnderecoDTOResponse> atualizaEnderecoPorId(@RequestParam("id") Long id,
                                                                     @RequestBody EnderecoDTORequest enderecoDTO,
                                                                     @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,enderecoDTO,token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone do usuário", description = "Endpoint para atualizar um telefone já cadastrado do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefonePorId(@RequestParam("id") Long id,
                                                                     @RequestBody TelefoneDTORequest telefoneDTO,
                                                                     @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,telefoneDTO,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastrar novo endereço ao usuário", description = "Endpoint para cadastrar um novo endereço para usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastraEndereco(token,enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastrar novo telefone ao usuário", description = "Endpoint para cadastrar um novo telefone para usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Telefone atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastraTelefone(token,telefoneDTO));
    }
}
