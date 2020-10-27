package br.com.douggoncalves.lembretedecompras.repository

import androidx.lifecycle.LiveData
import br.com.douggoncalves.lembretedecompras.dao.ProdutoDao
import br.com.douggoncalves.lembretedecompras.models.Produto

class ProdutoRepository(private val produtoDao: ProdutoDao) {
    val produtos: LiveData<List<Produto>> = produtoDao.getListaDeProdutos()
    suspend fun insert(produto: Produto) {
        produtoDao.insert(produto)
    }
}