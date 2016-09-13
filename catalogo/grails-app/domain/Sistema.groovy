package domain

class Sistema {

	Long id
	Long version
	
	String nome
	String descricao
	String areaDemandante
	String linguagem
	int anoDesenvolvimento
	String responsavelHomologacao
	Date dataHomologacao
	
	Fabricante fabricante
	
	String toString() {"${this.class.name} : $id"}
}
