package domain

class Fabricante {
	
	Long id
	Long version
	String nome
	String emailContato
	
	def relatesToMany = [sistemas : Sistema]
	Set sistemas = new HashSet()
	
	String toString() {"${this.class.name} : $id"}
	
}
