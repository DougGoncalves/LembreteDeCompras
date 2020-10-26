package br.com.douggoncalves.lembretedecompras.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.douggoncalves.lembretedecompras.R
import br.com.douggoncalves.lembretedecompras.models.LoginViewModel
import br.com.douggoncalves.lembretedecompras.models.RequestState
import br.com.douggoncalves.lembretedecompras.models.Usuario
import br.com.douggoncalves.lembretedecompras.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var animacaoDoMascote: Animation
    private lateinit var animacaoDoFormulario: Animation

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        iniciarAnimacao()
        esconderTeclado()

        iniciarListener()
        iniciarViewModel()
        iniciarObserver()

        loginViewModel.getUsuarioLogado()
    }

    private fun iniciarAnimacao() {
        animacaoDoMascote = AnimationUtils.loadAnimation(this, R.anim.frombottom2)
        animacaoDoFormulario = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        containerLogin.clearAnimation()
        ivLogin.clearAnimation()
        containerLogin.startAnimation(animacaoDoMascote)
        ivLogin.startAnimation(animacaoDoFormulario)
    }

    private fun esconderTeclado() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    private fun iniciarListener() {
        btLogin.setOnClickListener {
            loginViewModel.logar(Usuario(etEmail.text.toString(),
                    etPassword.text.toString()))
        }

        etPassword.setOnFocusChangeListener { v, hasFocus -> if (hasFocus) {
            ivLogin.speed = 2f
            ivLogin.setMinAndMaxProgress(0.0f, 0.65f) } else {
            ivLogin.speed = 1f
            ivLogin.setMinAndMaxProgress(0.65f, 1.0f) }
            ivLogin.playAnimation()
        }
    }

    private fun iniciarViewModel() {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun iniciarObserver() {
        loginViewModel.loginState.observe(this, Observer {
            when (it) {
                is RequestState.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish ()
                }
                is RequestState.Error -> {
                    val animShake = AnimationUtils.loadAnimation(this, R.anim.shake)
                    containerLogin . startAnimation (animShake)
                    tvPasswordFeedback . text = it . throwable . message
                }
                is RequestState.Loading -> {
                }
            }
        })

        loginViewModel.usuarioLogadoState.observe(this, Observer { when (it) {
            is RequestState.Success -> { etEmail.setText(it.data)
            }
            is RequestState.Error -> {
            }
            is RequestState.Loading -> {

            }
        }
        })
    }
}