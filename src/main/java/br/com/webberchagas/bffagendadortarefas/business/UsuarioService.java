package br.com.webberchagas.bffagendadortarefas.business;

import br.com.webberchagas.bffagendadortarefas.business.dtos.in.EnderecoDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.LoginRequestDTO;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.TelefoneDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.in.UsuarioDTORequest;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.EnderecoDTOResponse;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.TelefoneDTOResponse;
import br.com.webberchagas.bffagendadortarefas.business.dtos.out.UsuarioDTOResponso;
import br.com.webberchagas.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponso salvar(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.registerNewUser(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO dto) {
        return usuarioClient.login(dto);
    }

    public UsuarioDTOResponso buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponso atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return usuarioClient.atualizarUsuarioPorEmail(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest dto, String token) {
        return usuarioClient.atualizaEnderecoPorId(idEndereco, dto, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token) {
       return usuarioClient.atualizaTelefonePorId(idTelefone, dto, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
       return  usuarioClient.cadastraTelefone(dto, token);
    }
}
