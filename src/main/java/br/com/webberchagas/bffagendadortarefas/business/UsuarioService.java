package br.com.webberchagas.bffagendadortarefas.business;

import br.com.webberchagas.bffagendadortarefas.business.dtos.EnderecoDTO;
import br.com.webberchagas.bffagendadortarefas.business.dtos.TelefoneDTO;
import br.com.webberchagas.bffagendadortarefas.business.dtos.UsuarioDTO;
import br.com.webberchagas.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        return usuarioClient.registerNewUser(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto) {
        return usuarioClient.atualizarUsuarioPorEmail(dto, token);
    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO dto, String token) {
        return usuarioClient.atualizaEnderecoPorId(idEndereco, dto, token);
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO dto, String token) {
       return usuarioClient.atualizaTelefonePorId(idTelefone, dto, token);
    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO dto) {
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto) {
       return  usuarioClient.cadastraTelefone(dto, token);
    }
}
