package br.com.douggoncalves.lembretedecompras.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.douggoncalves.lembretedecompras.dao.LembretedeComprasRoomDatabase
import br.com.douggoncalves.lembretedecompras.models.Produto
import br.com.douggoncalves.lembretedecompras.repository.ProdutoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProdutoRepository
    val produtos: LiveData<List<Produto>>

    init {
        val produtoDao =
            LembretedeComprasRoomDatabase.getDatabase(application).produtoDao()
        repository = ProdutoRepository(produtoDao)
        produtos = repository.produtos
    }

    fun insert(produto: Produto) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(produto)
    }

    fun apagar() = viewModelScope.launch(Dispatchers.IO) {
        repository.apagar()
    }
}