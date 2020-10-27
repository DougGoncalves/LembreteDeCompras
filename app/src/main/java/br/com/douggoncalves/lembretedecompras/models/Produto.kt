package br.com.douggoncalves.lembretedecompras.models

@Entity(tableName = "tabela_produto")
data class Produto(
    @PrimaryKey
    @ColumnInfo(name = "nome_produto")
    val nome: String
)