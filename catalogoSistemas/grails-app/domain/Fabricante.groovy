package catalogosistemas

class Fabricante {

    Long id
    Long version
    String nome
    String pessoaContato
    String emailContato
    String valPontoFuncao
    String valUnidadeServico

    def relateToMany = [sistemas : Sistema]
    Set sistemas = new HashSet()

    static constraints = {
        nome(nullable:false, maxSize: 100)
        pessoaContato(nullable:false, maxSize: 100)
        emailContato(nullable:false, maxSize: 100)
        valPontoFuncao(nullable:true)
        valUnidadeServico(nullable:true)
    }

    String toString() { "${this.class.name} : $id" }

}
