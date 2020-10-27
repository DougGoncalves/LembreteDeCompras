package br.com.douggoncalves.lembretedecompras.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.douggoncalves.lembretedecompras.models.RequestState
import br.com.douggoncalves.lembretedecompras.models.Usuario
import br.com.douggoncalves.lembretedecompras.repository.UsuarioRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val usuarioRepository = UsuarioRepository(application)
    val loginState = MutableLiveData<RequestState<String>>()
    fun logar(usuario: Usuario) {
        loginState.value = usuarioRepository.logar(usuario).value
    }

    val usuarioLogadoState = MutableLiveData<RequestState<String>>()

    fun getUsuarioLogado() {
        usuarioLogadoState.value = usuarioRepository.getUsuarioLogado().value
    }

}
