package catalogosistemas

class Sistema {
    Long id
    Long version
    String sigla
    String nome
    String descricao
    String linguagem
    String areaDemandante
    String gestorSolicitante
    String gestorAprovador
    int anoDesenvolvimento
    Date dataSolicitacao
    Date dataImplantacao
    Fabricante fabricante

    static constraints = {
        sigla(nullable:false, maxSize: 10)
        nome(nullable:false, maxSize: 50)
        descricao(nullable:false, maxSize: 2000)
        linguagem(nullable:false, maxSize:10)
        areaDemandante(nullable:false, maxSize: 100)
        gestorSolicitante(nullable:false, maxSize: 100)
        gestorAprovador(nullable:false, maxSize: 100)
        anoDesenvolvimento(nullable:false, maxSize: 4)
        dataSolicitacao(nullable:false)
        dataImplantacao(nullable:false)
        fabricante(nullable:false)
    }

    String toString() { "${this.class.name} : $id" }
}
